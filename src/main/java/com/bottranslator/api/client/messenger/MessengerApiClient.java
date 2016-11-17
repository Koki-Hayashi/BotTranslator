package com.bottranslator.api.client.messenger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import com.bottranslator.api.client.messenger.model.send.GreetingRegister;
import com.bottranslator.api.client.messenger.model.send.ReplyMessage;
import com.bottranslator.service.ArgsService;

public class MessengerApiClient
{
	private Logger log = LoggerFactory.getLogger(MessengerApiClient.class);

	private static final String REGISTER_WELCOME_MSG = "https://graph.facebook.com/v2.6/me/thread_settings";
	private static final String SEND_MSG = "https://graph.facebook.com/v2.6/me/messages";

	private ObjectMapper mapper = new ObjectMapper();

	public void registerWelcomeText(GreetingRegister greetingRegister) throws JsonProcessingException, UnirestException
	{
		log.debug("Registering welcome text : " + greetingRegister);
		HttpResponse<JsonNode> jsonResponse = Unirest.post(REGISTER_WELCOME_MSG)
			.header("Content-Type", "application/json")
			.queryString("access_token", ArgsService.getPageAccessToken())
			.body(mapper.writeValueAsString(greetingRegister))
			.asJson();

		printDebugLog(jsonResponse);
	}

	public void replyMessage(ReplyMessage replyMessage) throws UnirestException, JsonProcessingException
	{
		HttpResponse<JsonNode> jsonResponse = Unirest.post(SEND_MSG)
			.header("Content-Type", "application/json")
			.queryString("access_token", ArgsService.getPageAccessToken())
			.body(mapper.writeValueAsString(replyMessage))
			.asJson();

		printDebugLog(jsonResponse);
	}

	private void printDebugLog(HttpResponse response)
	{
		log.debug("--HttpResponse--");
		log.debug(String.valueOf(response.getStatus()));
		log.debug(response.getStatusText());
		log.debug(String.valueOf(response.getHeaders()));
		log.debug(String.valueOf(response.getRawBody()));
	}

}
