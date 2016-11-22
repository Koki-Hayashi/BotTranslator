package com.bottranslator.api.client.messenger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bottranslator.api.client.messenger.model.send.ReplyMessage;
import com.bottranslator.api.client.messenger.model.send.ThreadSetting;
import com.bottranslator.service.ArgsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class MessengerApiClient
{
	private Logger log = LoggerFactory.getLogger(MessengerApiClient.class);

	private static final String THREAD_SETTING_API = "https://graph.facebook.com/v2.6/me/thread_settings";
	private static final String SEND_MSG = "https://graph.facebook.com/v2.6/me/messages";

	private ObjectMapper mapper = new ObjectMapper();

	public void sendThreadSetting(ThreadSetting threadSetting) throws JsonProcessingException, UnirestException
	{
		log.info("Registering get started button : " + threadSetting);
		HttpResponse<JsonNode> jsonResponse = Unirest.post(THREAD_SETTING_API)
			.header("Content-Type", "application/json")
			.queryString("access_token", ArgsService.getPageAccessToken())
			.body(mapper.writeValueAsString(threadSetting))
			.asJson();

		printLog(jsonResponse);
	}

	public void replyMessage(ReplyMessage replyMessage) throws UnirestException, JsonProcessingException
	{
		HttpResponse<JsonNode> jsonResponse = Unirest.post(SEND_MSG)
			.header("Content-Type", "application/json")
			.queryString("access_token", ArgsService.getPageAccessToken())
			.body(mapper.writeValueAsString(replyMessage))
			.asJson();

		printLog(jsonResponse);
	}

	private void printLog(HttpResponse response)
	{
		log.info("--HttpResponse--");
		log.info(String.valueOf(response.getStatus()));
		log.info(response.getStatusText());
		log.info(String.valueOf(response.getHeaders()));
		log.info(String.valueOf(response.getRawBody()));
	}

}
