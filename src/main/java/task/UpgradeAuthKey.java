package task;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import service.ArgsService;
import service.AuthKeyService;

public class UpgradeAuthKey extends Thread
{

	private static final int INTERVAL_MSEC = 8 * 60 * 1000; // every 8 min since the token is valid only 10 min
	private static final String ISSUE_TOKEN_API = "https://api.cognitive.microsoft.com/sts/v1.0/issueToken";

	@Override
	public void run() {
		while (true) {
			try {
				HttpResponse<String> jsonResponse = Unirest.post(ISSUE_TOKEN_API).header("Content-Type", "application/json")
					.header("Accept", "application/jwt").header("Ocp-Apim-Subscription-Key", ArgsService.commandLine.getOptionValue("s")).asString();
				String authKey = jsonResponse.getBody().toString();
				AuthKeyService.setAuthKey(authKey);
				System.out.println("----authKey----");
				System.out.println(authKey);

				Thread.sleep(INTERVAL_MSEC);
			}
			catch (UnirestException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
