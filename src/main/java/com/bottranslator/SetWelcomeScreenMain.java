package com.bottranslator;

import java.io.IOException;

import com.bottranslator.api.client.messenger.MessengerApiClient;
import com.bottranslator.api.client.messenger.model.send.CallToAction;
import com.bottranslator.api.client.messenger.model.send.Greeting;
import com.bottranslator.api.client.messenger.model.send.ThreadSetting;
import com.bottranslator.service.ArgsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;

public class SetWelcomeScreenMain
{
	private static final int ERROR_EXIT = 1;

	MessengerApiClient messengerApiClient = new MessengerApiClient();


	public void registerGreeting() throws JsonProcessingException
	{
		String welcomeText = "Hi! I'm a Flight Search Bot!!";

		Greeting greeting = new Greeting(welcomeText);
		ThreadSetting greetingRegister = new ThreadSetting();
		greetingRegister.setSettingType("greeting");
		greetingRegister.setGreeting(greeting);

		try
		{
			messengerApiClient.sendThreadSetting(greetingRegister);
		}
		catch (UnirestException e)
		{
			e.printStackTrace();
		}
	}

	public void registerGetStartedBtn() throws JsonProcessingException
	{
		ThreadSetting getStartedBtnRegister = new ThreadSetting();
		getStartedBtnRegister.setSettingType("call_to_actions");
		getStartedBtnRegister.setThreadState("new_thread");
		CallToAction callToActions = new CallToAction("get started");
		getStartedBtnRegister.setCallToActions(new CallToAction[]{ callToActions});

		try
		{
			messengerApiClient.sendThreadSetting(getStartedBtnRegister);
		}
		catch (UnirestException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException
	{
		ArgsService argsService = ArgsService.getInstance()
			.requirePageAccessToken();

		if (!argsService.validate(args))
		{
			System.exit(ERROR_EXIT);
		}

		SetWelcomeScreenMain main = new SetWelcomeScreenMain();

		main.registerGreeting();
		main.registerGetStartedBtn();
	}

}
