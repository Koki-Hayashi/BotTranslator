import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.messenger.UserMessage;

/**
 * Created by koki on 11/15/16.
 */
public class ParserTestMain
{
	public static void main(String[] args) throws IOException
	{
		String json = "{\"entry\":[{\"id\":\"1520683617946388\",\"time\":1479210929762,\"messaging\":[{\"sender\":{\"id\":\"1073269239448265\"},\"recipient\":{\"id\":\"1520683617946388\"},\"message\":{\"mid\":\"mid.1479210929746:593e40c849\",\"text\":\"sss\",\"seq\":328},\"timestamp\":1479210929746}]}],\"object\":\"page\"}";
		ObjectMapper mapper = new ObjectMapper();
		UserMessage userMessageBlock = mapper.readValue(json, UserMessage.class);
		System.out.println(userMessageBlock.toString());
	}

}
