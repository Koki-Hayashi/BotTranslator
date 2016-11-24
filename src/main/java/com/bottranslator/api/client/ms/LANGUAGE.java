package com.bottranslator.api.client.ms;

/**
 * supported languages
 */
public enum LANGUAGE
{
	ENGLISH("English", "en"),
	GERMAN("German", "de"),
	JAPANESE("Japanese", "ja"),
	RUSSIAN("Russian", "ru"),
	SPANISH("Spanish", "es"),
	ITALIAN("Italian", "it"),
	HUNGARIAN("Hungarian", "hu"),
	NORWEGIAN("Norwegian", "no"),
	SERBIAN("Serbian", "sr-Cyrl"),
	ARABIC("Arabic", "ar");

	private String name;
	private String languageCode;

	LANGUAGE(String name, String languageCode)
	{
		this.name = name;
		this.languageCode = languageCode;
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

	public String getLanguageCode()
	{
		return languageCode;
	}

	public static LANGUAGE getFromName(String name) {
		for (LANGUAGE language : values() ) {
			if(language.getName().toLowerCase().equals(name.toLowerCase())) return language;
		}

		return null;
	}
}
