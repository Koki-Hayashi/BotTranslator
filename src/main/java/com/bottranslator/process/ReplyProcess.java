package com.bottranslator.process;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;

import com.bottranslator.api.client.messenger.MessengerApiClient;
import com.bottranslator.api.client.messenger.model.receive.UserMessage;
import com.bottranslator.api.client.messenger.model.send.Message;
import com.bottranslator.api.client.messenger.model.send.QuickReplies;
import com.bottranslator.api.client.messenger.model.send.Recipient;
import com.bottranslator.api.client.messenger.model.send.ReplyMessage;
import com.bottranslator.api.client.ms.LANGUAGE;
import com.bottranslator.api.client.ms.MSApiClient;

public class ReplyProcess extends Thread
{

	private MSApiClient msApiClient = new MSApiClient();
	private MessengerApiClient messengerApiClient = new MessengerApiClient();

	private UserMessage userMessage;

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
						String userText = messaging.getMessage().getText();

						LANGUAGE from = LANGUAGE.ENGLISH;
						LANGUAGE to = LANGUAGE.GERMAN;

						String replyText = getTranslation(userText, from, to);
						replyMessage(senderId, replyText);

					}
					catch (UnirestException | ParserConfigurationException | SAXException | IOException e)
					{
						e.printStackTrace();
					}
				});
		});
	}

	private void sendLanguageSetting(String senderId) throws JsonProcessingException, UnirestException
	{
		String text = "From which language?";
		replyQuickRepliesMessage(senderId, text, LANGUAGE.getNameArray());
	}

	private String getTranslation(String text, LANGUAGE from, LANGUAGE to)
		throws UnirestException, ParserConfigurationException, SAXException, IOException
	{
		return msApiClient.getTranslation(text, from, to);
	}

	private void replyQuickRepliesMessage(String senderId, String text, String[] titleArray) throws UnirestException, JsonProcessingException
	{
		Recipient recipient = new Recipient(senderId);
		QuickReplies[] quickRepliesArray = new QuickReplies[titleArray.length];
		for (int i = 0; i < quickRepliesArray.length; i++)
		{
			QuickReplies quickReplies = quickRepliesArray[i];
			quickReplies.setContentType("text");
			quickReplies.setTitle(titleArray[i]);
		}

		Message message = new Message();
		message.setText(text);
		message.setQuickReplies(quickRepliesArray);
		ReplyMessage replyMessage = new ReplyMessage(recipient, message);

		messengerApiClient.replyMessage(replyMessage);
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
