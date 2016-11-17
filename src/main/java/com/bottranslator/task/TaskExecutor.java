package com.bottranslator.task;

import java.util.Arrays;
import java.util.List;

import com.bottranslator.api.client.ms.task.UpgradeAuthKey;

public class TaskExecutor
{
	private static List<Thread> taskList = Arrays.asList(new UpgradeAuthKey());

	public static void start()
	{
		taskList.forEach(task -> {
			task.start();
		});
	}
}
