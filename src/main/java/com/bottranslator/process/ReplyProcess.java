package com.bottranslator.process;

import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.bottranslator.api.client.messenger.MessengerApiClient;
import com.bottranslator.api.client.messenger.model.receive.Messaging;
import com.bottranslator.api.client.messenger.model.receive.UserMessage;
import com.bottranslator.api.client.messenger.model.send.Message;
import com.bottranslator.api.client.messenger.model.send.QuickReply;
import com.bottranslator.api.client.messenger.model.send.Recipient;
import com.bottranslator.api.client.messenger.model.send.ReplyMessage;
import com.bottranslator.api.client.ms.LANGUAGE;
import com.bottranslator.api.client.ms.MSApiClient;
import com.bottranslator.constant.Payload;
import com.bottranslator.data.cache.LanguageSettingCache;
import com.bottranslator.data.cache.model.LanguageSetting;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ReplyProcess extends Thread
{

	private MSApiClient msApiClient = new MSApiClient();
	private MessengerApiClient messengerApiClient = new MessengerApiClient();

	private UserMessage userMessage;
	private Map<String, LanguageSetting> cache = LanguageSettingCache.getCache();

	private Logger log = LoggerFactory.getLogger(ReplyProcess.class);

	public ReplyProcess(UserMessage userMessage)
	{
		this.userMessage = userMessage;
	}

	@Override
	public void run()
	{
		userMessage.getEntryList().stream().forEachOrdered(entry -> {
			entry.getMessagingList().stream().
				forEachOrdered(messaging -> {
					try
					{
						if (messaging == null)
						{
							return; // not supported
						}

						if (messaging.getPostback() != null) {
							handlePostback(messaging);
							return;
						}

						com.bottranslator.api.client.messenger.model.receive.Message message = messaging.getMessage();
						if (message == null) return; // not supported
						if(message.getQuickReply() != null){
							updateLanguageSetting(messaging);
							replyCurrentSetting(messaging);
							return;
						}

						replyTranslation(messaging);

					}
					catch (UnirestException | ParserConfigurationException | SAXException | IOException e)
					{
						e.printStackTrace();
					}
				});
		});
	}

	private void updateLanguageSetting(Messaging messaging)
	{
		String senderId = messaging.getSender().getId();
		LanguageSetting setting = cache.get(senderId);
		if(setting == null) {
			setting = createDefaultSetting();
		}
		String payload = messaging.getMessage().getQuickReply().getPayload();
		String text = messaging.getMessage().getText();
		LANGUAGE language = LANGUAGE.getFromName(text);
		if (Payload.QUICK_REPLY_INPUT_LANGUAGE.equals(payload)) {
			setting.setFrom(language);
		} else if (Payload.QUICK_REPLY_OUTPUT_LANGUAGE.equals(payload)) {
			setting.setTo(language);
		}

		log.info("Setting updated. senderId : " + senderId + ", setting : " + setting);
		cache.put(senderId, setting);
	}

	private void handlePostback(Messaging messaging) throws JsonProcessingException, UnirestException
	{
		String payload = messaging.getPostback().getPayload();
		if (Payload.PERSISTENT_MENU_SHOW_CURRENT_SETTING.equals(payload)) {
			replyCurrentSetting(messaging);
		}
		else if (Payload.PERSISTENT_MENU_CHANGE_INPUT_LANGUAGE.equals(payload))
		{
			sendLanguageSetting(messaging, Payload.QUICK_REPLY_INPUT_LANGUAGE);
		}
		else if (Payload.PERSISTENT_MENU_CHANGE_OUTPUT_LANGUAGE.equals(payload))
		{
			sendLanguageSetting(messaging, Payload.QUICK_REPLY_OUTPUT_LANGUAGE);
		}
	}

	private LanguageSetting createDefaultSetting()
	{
		return new LanguageSetting(LANGUAGE.ENGLISH, LANGUAGE.GERMAN);
	}

	private void replyCurrentSetting(Messaging messaging) throws JsonProcessingException, UnirestException
	{
		String senderId = messaging.getSender().getId();
		LanguageSetting setting = cache.get(senderId);
		if (setting == null) {
			setting = createDefaultSetting();
		}

		replyMessage(senderId, "Translate from " + setting.getFrom().getName() + " to " + setting.getTo().getName());
	}

	private void sendLanguageSetting(Messaging messaging, String payload) throws JsonProcessingException, UnirestException
	{
		replyQuickRepliesMessage(messaging.getSender().getId(), LANGUAGE.getNameArray(), payload);
	}

	private void replyQuickRepliesMessage(String senderId, String[] titleArray, String payload) throws UnirestException, JsonProcessingException
	{
		QuickReply[] quickReplyArray = new QuickReply[titleArray.length];
		for (int i = 0; i < quickReplyArray.length; i++)
		{
			QuickReply quickReply = new QuickReply();
			quickReply.setContentType("text");
			quickReply.setTitle(titleArray[i]);
			quickReply.setPayload(payload);
			quickReplyArray[i] = quickReply;
		}

		Message message = new Message();
		message.setText("Please select language ");
		message.setQuickReplies(quickReplyArray);
		Recipient recipient = new Recipient(senderId);

		ReplyMessage replyMessage = new ReplyMessage(recipient, message);

		messengerApiClient.replyMessage(replyMessage);
	}

	private void replyTranslation(Messaging messaging) throws ParserConfigurationException, UnirestException, SAXException, IOException
	{
		String senderId = messaging.getSender().getId();
		LanguageSetting setting = cache.get(senderId);
		if (setting == null) {
			setting = createDefaultSetting();
			cache.put(senderId, setting);
		}

		log.info("Current translation setting : " + setting );

		String userText = messaging.getMessage().getText();
		String replyText = getTranslation(userText, setting.getFrom(), setting.getTo());
		replyMessage(senderId, replyText);
	}

	private String getTranslation(String text, LANGUAGE from, LANGUAGE to)
		throws UnirestException, ParserConfigurationException, SAXException, IOException
	{
		return msApiClient.getTranslation(text, from, to);
	}

	private void replyMessage(Messaging messaging, String text) throws UnirestException, JsonProcessingException
	{
		replyMessage(messaging.getSender().getId(), text);
	}

	private void replyMessage(String senderId, String text) throws UnirestException, JsonProcessingException
	{
		Recipient recipient = new Recipient(senderId);
		Message message = new Message();
		message.setText(text);
		ReplyMessage replyMessage = new ReplyMessage(recipient, message);

		messengerApiClient.replyMessage(replyMessage);
	}

}
