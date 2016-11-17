package process;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import api.client.LANGUAGE;
import api.client.MSApiClient;
import constant.Const;
import model.botranslator.Message;
import model.botranslator.Recipient;
import model.botranslator.ReplyMessage;
import model.messenger.UserMessage;
import service.ArgsService;

public class TranslateAndReplyProcess extends Thread {
	UserMessage userMessage;

	MSApiClient msApiClient = new MSApiClient();

	public TranslateAndReplyProcess(UserMessage userMessage)
	{
		this.userMessage = userMessage;
	}

	@Override
	public void run() {
		userMessage.getEntryList().stream().forEachOrdered(entry -> {
			entry.getMessagingList().stream().forEachOrdered(messaging -> {
				try
				{
					String replyText = null;
					replyText = getTranslation(messaging.getMessage().getText(), LANGUAGE.ENGLISH, LANGUAGE.GERMAN);

					replyMessage(messaging.getSender().getId(), replyText);
				}
				catch (UnirestException | ParserConfigurationException | SAXException | IOException e) {
					e.printStackTrace();
				}
			});
		});
	}

	private String getTranslation(String text, LANGUAGE from, LANGUAGE to)
		throws UnirestException, ParserConfigurationException, SAXException, IOException
	{
		return msApiClient.getTranslation(text, from , to);
	}

	private void replyMessage(String senderId, String text) throws UnirestException, JsonProcessingException {
		Recipient recipient = new Recipient(senderId);
		Message message = new Message(text);
		ReplyMessage replyMessage = new ReplyMessage(recipient, message);

		ObjectMapper mapper = new ObjectMapper();

		System.out.println("----jsonRequestBody----");
		System.out.println(mapper.writeValueAsString(replyMessage));

		HttpResponse<JsonNode> jsonResponse = Unirest.post(Const.MESSENGER_API_URL)
			.header("Content-Type", "application/json")
			.queryString("access_token", ArgsService.getCommandLine().getOptionValue("p"))
			.body(mapper.writeValueAsString(replyMessage)).asJson();

		System.out.println("----jsonResponse----");
		System.out.println(jsonResponse.getStatus());
		System.out.println(jsonResponse.getStatusText());
		System.out.println(jsonResponse.getHeaders());
		System.out.println(jsonResponse.getBody());
		System.out.println(jsonResponse.getRawBody());

	}

}
