package ForkJoinCancelDemo;
/**
 * 由于forkjoinpool和forkJoinTask的局限
 * 性，将taskManager类来取消ForkJoinPool类中
 * 所有的任务.
 * @author soft01
 *
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;

public class TaskManager2 {
	private List<ForkJoinTask<Integer>> tasks;
	public TaskManager2() {
		tasks = new ArrayList<ForkJoinTask<Integer>>();
	}
	public void addTask(ForkJoinTask<Integer> task) {
		tasks.add(task);
		System.out.println(tasks);
	}
	public void cancelTasks(ForkJoinTask<Integer> cancelTask) {
		for(ForkJoinTask<Integer> task:tasks) {
			if(task!=cancelTask) {
				task.cancel(true);
				((Task2)task).writeCancelMessage();
			}
		}
	}
}
                                