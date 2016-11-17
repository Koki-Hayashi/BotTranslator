package api.client;

public enum LANGUAGE {
	ENGLISH("en"),
	GERMAN("de");

	private String countryCode;

	LANGUAGE(String countryCode)
	{
		this.countryCode = countryCode;
	}

	public String getCountryCode()
	{
		return countryCode;
	}
}
