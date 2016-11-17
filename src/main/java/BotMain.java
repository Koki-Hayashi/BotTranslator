import com.fasterxml.jackson.databind.ObjectMapper;

import model.messenger.UserMessage;
import process.factory.ProcessFactory;
import service.ArgsService;
import status.ResponseStatus;
import task.TaskExecutor;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

public class BotMain {
	private static final String ACCESS_TOKEN = "translator-token";
	private static final String REPLY_FOR_INVALID_ACCESS = "wrong token";

	private static final int ERROR_EXIT = 1;

	public static void main(String[] args) {
		if(!ArgsService.validateAndRegister(args)) System.exit(ERROR_EXIT);

		TaskExecutor.start(); // run back ground process

		port(Integer.valueOf(System.getenv("PORT")));
		staticFileLocation("/public");

		get("/webhook", (req, res) -> {
			if (ACCESS_TOKEN.equals(req.queryParams("hub.verify_token")))
			{
				return req.queryParams("hub.challenge");
			}
			return REPLY_FOR_INVALID_ACCESS;
		});

		post("/webhook", (req, res) -> { // assuming receiving only normal message
			ObjectMapper mapper = new ObjectMapper();
			UserMessage userMessage = mapper.readValue(req.body(), UserMessage.class);

			if("page".equals(userMessage.getObject())) {
				System.out.println("----userMessage----");
				System.out.println(userMessage);
				Thread thread = ProcessFactory.getProcess(userMessage);
				thread.start();
			}

			res.status(ResponseStatus.OK.getStatusCode()); // anyway, set OK 200
			return res;
		});

	}
}
