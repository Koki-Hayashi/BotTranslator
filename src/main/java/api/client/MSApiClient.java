package api.client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import constant.Const;
import service.AuthKeyService;

public class MSApiClient {

	private static final String APPID_PREFIX = "Bearer ";

	public MSApiClient() {

	}

	public String getTranslation(String text, LANGUAGE from, LANGUAGE to)
		throws UnirestException, ParserConfigurationException, IOException, SAXException
	{
		HttpResponse<String> response = Unirest.get(Const.MS_TRANSLATE_API_URL)
			.header("Accept", "application/xml")
			.queryString("appId", APPID_PREFIX + AuthKeyService.getAuthKey())
			.queryString("text", text)
			.queryString("from", from.getCountryCode())
			.queryString("to", to.getCountryCode()).asString();

		InputSource inputSource = new InputSource(new StringReader(response.getBody()));
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputSource);

		System.out.println("response.getBody()");
		System.out.println(document);
		System.out.println(document.getFirstChild());
		System.out.println(document.getElementsByTagName("string").toString());

		return document.getElementsByTagName("string").toString();
	}

}
