package task;

import java.util.Arrays;
import java.util.List;

public class TaskExecutor {

	private static List<Thread> taskList = Arrays.asList(new UpgradeAuthKey());

	public static void start() {
		taskList.forEach(task -> {
			task.start();
		});
	}
}
