package com.bottranslator.api.client.ms.service;

public class AuthKeyService
{
	private static Object lock = new Object();

	private static String authKey = null;

	public static String getAuthKey()
	{
		synchronized (lock)
		{
			return authKey;
		}
	}

	public static void setAuthKey(String authKey)
	{
		synchronized (lock)
		{
			AuthKeyService.authKey = authKey;
		}
	}
}
