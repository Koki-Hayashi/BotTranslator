package com.bottranslator.process;

import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.StringUtils;
import org.xml.sax.SAXException;

import com.bottranslator.api.client.messenger.MessengerApiClient;
import com.bottranslator.api.client.messenger.model.receive.UserMessage;
import com.bottranslator.api.client.messenger.model.send.Message;
import com.bottranslator.api.client.messenger.model.send.QuickReplies;
import com.bottranslator.api.client.messenger.model.send.Recipient;
import com.bottranslator.api.client.messenger.model.send.ReplyMessage;
import com.bottranslator.api.client.ms.LANGUAGE;
import com.bottranslator.api.client.ms.MSApiClient;
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

	private static final int ASSUME_NOT_TALKING_TO_BOT = 10;

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

						String senderId = messaging.getSender().getId();
						String text = messaging.getMessage().getText();

						if (isUserTalkingToBot(text)) {
							sendLanguageSettingMenu(senderId);
						} else
						{
							replyTranslation(senderId, text);
						}

					}
					catch (UnirestException | ParserConfigurationException | SAXException | IOException e)
					{
						e.printStackTrace();
					}
				});
		});
	}

	private boolean isUserTalkingToBot(String text)
	{
		return text !=null && StringUtils.containsIgnoreCase(text, "hi") && StringUtils.containsIgnoreCase(text, "bot") && text.length() < ASSUME_NOT_TALKING_TO_BOT;
	}

	private void sendLanguageSettingMenu(String senderId) throws JsonProcessingException, UnirestException
	{
		String text = "From which language?";
		replyQuickRepliesMessage(senderId, text, LANGUAGE.getNameArray());
	}

	private void replyQuickRepliesMessage(String senderId, String text, String[] titleArray) throws UnirestException, JsonProcessingException
	{
		QuickReplies[] quickRepliesArray = new QuickReplies[titleArray.length];
		for (int i = 0; i < quickRepliesArray.length; i++)
		{
			QuickReplies quickReplies = new QuickReplies();
			quickReplies.setContentType("text");
			quickReplies.setTitle(titleArray[i]);
			quickRepliesArray[i] = quickReplies;
		}

		Message message = new Message();
		message.setText(text);
		message.setQuickReplies(quickRepliesArray);
		Recipient recipient = new Recipient(senderId);

		ReplyMessage replyMessage = new ReplyMessage(recipient, message);

		messengerApiClient.replyMessage(replyMessage);
	}

	private void replyTranslation(String senderId, String userText) throws ParserConfigurationException, UnirestException, SAXException, IOException
	{
		LanguageSetting setting = cache.get(senderId);

		LANGUAGE from = setting == null ? LANGUAGE.ENGLISH : setting.getFrom();
		LANGUAGE to = setting == null ? LANGUAGE.GERMAN : setting.getTo();

		String replyText = getTranslation(userText, from, to);
		replyMessage(senderId, replyText);
	}

	private String getTranslation(String text, LANGUAGE from, LANGUAGE to)
		throws UnirestException, ParserConfigurationException, SAXException, IOException
	{
		return msApiClient.getTranslation(text, from, to);
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
