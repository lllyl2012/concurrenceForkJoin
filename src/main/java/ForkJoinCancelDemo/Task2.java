package ForkJoinCancelDemo;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;


public class Task2 extends RecursiveTask<Integer> {
	TaskManager2 manager ;
	int[] arr;
	private final static int NOT_FOUND = -1;
	private int start, end;
	private int number;
	public Task2(int[] arr , int start, int end, int number,TaskManager2 manager) {
		this.manager = manager;
		this.arr = arr;
		this.start = start;
		this.end = end;
		this.number = number;
	}

	@Override
	protected Integer compute() {
//		Task2  t2 = new Task2(arr,1,2,3,manager);
//		System.out.println(1);
//		manager.addTask(t2);
//		System.out.println(2);
//		return 1;
		System.out.println("SearchNumberTask: task:" + start + "to" + end);
		int ret;
		if (end - start > 10) {
			ret = launchTasks();
		} else {
			ret = lookForNumber();
		}
		System.out.println("ret:"+ret);
		return ret;
			
	}

		private int lookForNumber() {
			for (int i = start; i < end; i++) {
				if (arr[i] == number) {
					System.out.println("Task:number found in position"  + i);
					manager.cancelTasks(this);
					return i;

				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return NOT_FOUND;
		}

		private int launchTasks() {
			int middle = (start + end) / 2;
			System.out.println("middle:"+middle);
			Task2 t12 = new Task2(arr, start, middle, number, manager);
			Task2 t22 = new Task2(arr, middle, end, number, manager);
			manager.addTask(t12);
			manager.addTask(t22);
			t12.fork();
			
			t22.fork();
			
			int returnValue;
			returnValue = t12.join();

			if (returnValue != -1) {
				return returnValue;
			}
			returnValue = t22.join();

			return returnValue;

		}
		public void writeCancelMessage() {
			System.out.println("task:cancel form" + start + "to" + end);
		}
//	public static void main(String[] args) {
//		TaskManager2 manager = new TaskManager2();
//		Task2 t = new Task2(manager);
//		ForkJoinPool pool = new ForkJoinPool(); 
//		pool.execute(t);
//		pool.shutdown();
//		try {
//			pool.awaitTermination(1, TimeUnit.DAYS);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
