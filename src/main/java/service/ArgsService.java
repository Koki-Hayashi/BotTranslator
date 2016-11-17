package service;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class ArgsService {

	public static CommandLine commandLine;

	public static boolean validateAndRegister(String[] args){
		CommandLineParser parser = new DefaultParser();

		Options options = new Options();
		Option subscriptionKey = Option.builder("s")
			.argName("subscriptionKey")
			.required()
			.hasArg()
			.build();
		options.addOption(subscriptionKey);

		Option pageAccessToken = Option.builder("p")
			.argName("pageAccessToken")
			.required()
			.hasArg()
			.build();
		options.addOption(pageAccessToken);


		boolean result = true;
		try {
 			commandLine = parser.parse(options, args);
		}
		catch (ParseException e) {
			e.printStackTrace();
			result = false;
		}

		return result;
	}

	public static CommandLine getCommandLine()
	{
		return commandLine;
	}
}
