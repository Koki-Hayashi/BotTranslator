package com.bottranslator;

import java.io.IOException;

import com.bottranslator.api.client.messenger.MessengerApiClient;
import com.bottranslator.api.client.messenger.model.send.CallToAction;
import com.bottranslator.api.client.messenger.model.send.Greeting;
import com.bottranslator.api.client.messenger.model.send.ThreadSetting;
import com.bottranslator.constant.Payload;
import com.bottranslator.service.ArgsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;

public class SetThreadSettingMain
{
	private static final int ERROR_EXIT = 1;

	MessengerApiClient messengerApiClient = new MessengerApiClient();


	public void registerGreeting() throws JsonProcessingException
	{
		String welcomeText = "Hi! I'm a Translator Bot! "
			+ "You can send me words you would like me to translate. I will reply translations to you! "
			+ "Default translation setting is from English to German. You can check/change settings from main menu anytime.";

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
		CallToAction callToActions = new CallToAction();
		callToActions.setPayload(Payload.GET_STARTED_PAYLOAD);
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

	public void registerPersistentMenu() throws JsonProcessingException
	{
		ThreadSetting persistentMenu = new ThreadSetting();
		persistentMenu.setSettingType("call_to_actions");
		persistentMenu.setThreadState("existing_thread");

		CallToAction currentSettings = new CallToAction();
		currentSettings.setType("postback");
		currentSettings.setTitle("Show current setting");
		currentSettings.setPayload(Payload.PERSISTENT_MENU_SHOW_CURRENT_SETTING);

		CallToAction languageFrom = new CallToAction();
		languageFrom.setType("postback");
		languageFrom.setTitle("Change input language");
		languageFrom.setPayload(Payload.PERSISTENT_MENU_CHANGE_INPUT_LANGUAGE);

		CallToAction languageTo = new CallToAction();
		languageTo.setType("postback");
		languageTo.setTitle("Change output language");
		languageTo.setPayload(Payload.PERSISTENT_MENU_CHANGE_OUTPUT_LANGUAGE);

		persistentMenu.setCallToActions(new CallToAction[]{ currentSettings, languageFrom, languageTo});

		try
		{
			messengerApiClient.sendThreadSetting(persistentMenu);
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

		SetThreadSettingMain main = new SetThreadSettingMain();

		main.registerGreeting();
		main.registerGetStartedBtn();
		main.registerPersistentMenu();
	}

}
