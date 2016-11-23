package com.bottranslator.process.factory;

import com.bottranslator.api.client.messenger.model.receive.UserMessage;
import com.bottranslator.process.ReplyProcess;

public class ProcessFactory
{
	public static Thread getProcess(UserMessage userMessage)
	{

		return new ReplyProcess(userMessage); // no other possible com.bottranslator.process type now
	}
}
