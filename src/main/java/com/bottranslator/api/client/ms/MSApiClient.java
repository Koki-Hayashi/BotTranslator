package com.bottranslator.api.client.ms;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import com.bottranslator.api.client.ms.service.AuthKeyService;

public class MSApiClient
{

	private Logger log = LoggerFactory.getLogger(MSApiClient.class);

	private static final String MS_TRANSLATE_API_URL = "https://api.microsofttranslator.com/v2/http.svc/Translate";
	private static final String APPID_PREFIX = "Bearer ";

	public String getTranslation(String text, LANGUAGE from, LANGUAGE to)
		throws UnirestException, ParserConfigurationException, IOException, SAXException
	{
		log.debug("Getting translation...");
		HttpResponse<String> response = Unirest.get(MS_TRANSLATE_API_URL)
			.header("Accept", "application/xml")
			.queryString("appId", APPID_PREFIX + AuthKeyService.getAuthKey())
			.queryString("text", text)
			.queryString("from", from.getCountryCode())
			.queryString("to", to.getCountryCode()).asString();

		InputSource inputSource = new InputSource(new StringReader(response.getBody()));
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputSource);
		Element e = doc.getDocumentElement();
		String translation = e.getFirstChild().getNodeValue(); // only one tag expected.
		log.debug("translation : " + translation);

		return translation;
	}

}
