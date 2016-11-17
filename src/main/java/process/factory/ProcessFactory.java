package process.factory;

import model.messenger.UserMessage;
import process.TranslateAndReplyProcess;

/**
 * Created by koki on 11/15/16.
 */
public class ProcessFactory
{
	public static Thread getProcess(UserMessage userMessage){
		return new TranslateAndReplyProcess(userMessage); // no other possible process type now
	}
}
