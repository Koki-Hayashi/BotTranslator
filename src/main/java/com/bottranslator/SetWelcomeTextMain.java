package com.bottranslator;

import java.io.IOException;

import com.mashape.unirest.http.exceptions.UnirestException;

import com.bottranslator.api.client.messenger.MessengerApiClient;
import com.bottranslator.api.client.messenger.model.send.Greeting;
import com.bottranslator.api.client.messenger.model.send.GreetingRegister;
import com.bottranslator.service.ArgsService;

public class SetWelcomeTextMain
{
	private static final int ERROR_EXIT = 1;

	public static void main(String[] args) throws IOException
	{
		ArgsService argsService = ArgsService.getInstance()
			.requirePageAccessToken();

		if (!argsService.validate(args))
		{
			System.exit(ERROR_EXIT);
		}

		MessengerApiClient messengerApiClient = new MessengerApiClient();

		String welcomeText = "Hi! I'm a Translator Bot!" +
			"You can send me words you want me to translate, and I will reply translations." +
			"Default setting is translating from English to German." +
			"When you would like to change the setting, say \"Hi bot\". Then the bot will ask you which setting you prefer." +
			"Supported languages : English, Japanese, German, Russian";

		Greeting greeting = new Greeting(welcomeText);
		GreetingRegister greetingRegister = new GreetingRegister(greeting);

		try
		{
			messengerApiClient.registerWelcomeText(greetingRegister);
		}
		catch (UnirestException e)
		{
			e.printStackTrace();
		}
	}

}
