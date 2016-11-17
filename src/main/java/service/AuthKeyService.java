package service;

public class AuthKeyService {

	private static String authKey = null;

	public static String getAuthKey()
	{
		return authKey;
	}

	public static void setAuthKey(String authKey)
	{
		AuthKeyService.authKey = authKey;
	}
}
