package com.bottranslator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bottranslator.process.factory.ProcessFactory;
import com.bottranslator.service.ArgsService;
import com.bottranslator.status.ResponseStatus;
import com.bottranslator.task.TaskExecutor;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.bottranslator.api.client.messenger.model.receive.UserMessage;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

public class BotMain
{
	private Logger log = LoggerFactory.getLogger(BotMain.class);

	private static final String REPLY_FOR_INVALID_ACCESS = "wrong token";
	private static final int ERROR_EXIT = 1;

	private void runBotProcess()
	{
		port(Integer.valueOf(System.getenv("PORT")));

		get("/webhook", (req, res) -> {
			if (ArgsService.getVerifyToken().equals(req.queryParams("hub.verify_token")))
			{
				return req.queryParams("hub.challenge");
			}
			return REPLY_FOR_INVALID_ACCESS;
		});

		post("/webhook", (req, res) -> { // assuming receiving only normal message
			try
			{
				ObjectMapper mapper = new ObjectMapper();
				log.info("--Request body--\n" + req.body());
				UserMessage userMessage = mapper.readValue(req.body(), UserMessage.class);
				log.info("--User message--\n" + userMessage);

				if ("page".equals(userMessage.getObject()))
				{
					Thread thread = ProcessFactory.getProcess(userMessage);
					thread.start();
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				res.status(ResponseStatus.OK.getStatusCode()); // anyway, set OK 200
				return res;
			}
		});
	}

	public static void main(String[] args)
	{

		ArgsService argsService = ArgsService.getInstance()
			.requireSubscriptionKey()
			.requirePageAccessToken()
			.requireVerifyToken();

		if (!argsService.validate(args))
		{
			System.exit(ERROR_EXIT);
		}

		TaskExecutor.start(); // start background processes

		BotMain main = new BotMain();
		main.runBotProcess();
	}
}
