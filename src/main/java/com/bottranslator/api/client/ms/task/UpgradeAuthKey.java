package com.bottranslator.api.client.ms.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import com.bottranslator.api.client.ms.service.AuthKeyService;
import com.bottranslator.service.ArgsService;

public class UpgradeAuthKey extends Thread
{

	private static final int INTERVAL_MSEC = 8 * 60 * 1000; // every 8 min since the token is valid only 10 min
	private static final String ISSUE_TOKEN_API = "https://api.cognitive.microsoft.com/sts/v1.0/issueToken";

	private Logger log = LoggerFactory.getLogger(UpgradeAuthKey.class);

	@Override
	public void run()
	{
		while (true)
		{
			try
			{
				log.debug("Getting authkey for MS com.bottranslator.service api....");
				HttpResponse<String> jsonResponse = Unirest.post(ISSUE_TOKEN_API).header("Content-Type", "application/json")
					.header("Accept", "application/jwt").header("Ocp-Apim-Subscription-Key", ArgsService.getSubscriptionKey()).asString();
				String authKey = jsonResponse.getBody().toString();
				AuthKeyService.setAuthKey(authKey);

				Thread.sleep(INTERVAL_MSEC);
			}
			catch (UnirestException | InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
