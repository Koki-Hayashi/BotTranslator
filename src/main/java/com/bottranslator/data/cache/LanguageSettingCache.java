package com.bottranslator.data.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.bottranslator.data.cache.model.LanguageSetting;

public class LanguageSettingCache
{
	private Object lock = new Object();

	private static Map<String, LanguageSetting> cache = new ConcurrentHashMap<>();

	public static Map<String, LanguageSetting> getCache()
	{
		return cache;
	}

	public static void upsertToCache(String userId, LanguageSetting setting){
			cache.put(userId, setting);
	}

	public static void deleteFromCache(String userId){
		cache.remove(userId);
	}
}
