package com.bottranslator.service;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class ArgsService
{

	private static CommandLine commandLine;
	private CommandLineParser parser = new DefaultParser();
	private Options options = new Options();

	private static ArgsService argsService = new ArgsService();

	private ArgsService()
	{
	}

	public static ArgsService getInstance(){
		return argsService;
	}

	public ArgsService requireSubscriptionKey()
	{
		Option subscriptionKey = Option.builder("s").argName("subscriptionKey").required().hasArg().build();
		options.addOption(subscriptionKey);

		return this;
	}

	public ArgsService  requireVerifyToken()
	{
		Option verifyToken = Option.builder("v").argName("verifyToken").required().hasArg().build();
		options.addOption(verifyToken);

		return this;
	}

	public ArgsService requirePageAccessToken()
	{
		Option pageAccessToken = Option.builder("p").argName("pageAccessToken").required().hasArg().build();
		options.addOption(pageAccessToken);

		return this;
	}

	public boolean validate(String[] args)
	{
		boolean result = true;
		try
		{
			commandLine = parser.parse(options, args);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			result = false;
		}

		return result;
	}

	private static String getOptionValue(String option) {
		return commandLine.getOptionValue(option);
	}

	public static String getSubscriptionKey(){
		return getOptionValue("s");
	}
	public static String getVerifyToken(){
		return getOptionValue("v");
	}
	public static String getPageAccessToken(){
		return getOptionValue("p");
	}

}
