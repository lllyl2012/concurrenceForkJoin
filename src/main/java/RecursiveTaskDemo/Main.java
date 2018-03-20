package RecursiveTaskDemo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {

		DocumentMock mock = new DocumentMock();
		String[][] document = mock.generateDocument(100, 1000, "all");
		DocumentTask task = new DocumentTask(document,0,100,"all");
		ForkJoinPool pool = new ForkJoinPool();
		pool.execute(task);
		do {
			System.out.println("parallelism:"+pool.getParallelism());
			System.out.println("active:"+pool.getActiveThreadCount());
			System.out.println("task Count:"+pool.getQueuedTaskCount());
			System.out.println("steal Count:"+pool.getStealCount());
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}while(!task.isDone());
		
		pool.shutdown();
		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println("all总共有"+task.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
