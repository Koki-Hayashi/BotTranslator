package com.bottranslator.api.client.ms;

/**
 * supported languages
 */
public enum LANGUAGE
{
	ENGLISH("English", "en"),
	GERMAN("German", "de"),
	JAPANESE("JAPANESE", "en"),
	RUSSIAN("RUSSIAN", "en");

	private String name;
	private String countryCode;

	LANGUAGE(String name, String countryCode)
	{
		this.name = name;
		this.countryCode = countryCode;
	}

	private static final String[] nameArray = new String[LANGUAGE.values().length];

	static
	{
		LANGUAGE[] languages = LANGUAGE.values();
		for (int i = 0; i < languages.length; i++)
		{
			nameArray[i] = languages[i].name;
		}
	}

	public static String[] getNameArray()
	{
		return nameArray;
	}

	public String getName()
	{
		return name;
	}

	public String getCountryCode()
	{
		return countryCode;
	}
}
