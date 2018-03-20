package asynchronization;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {

	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool();
		FolderProcessor eclipse = new FolderProcessor("/home/soft01/eclipse-workspace",".html");
		FolderProcessor apache = new FolderProcessor("/home/soft01/apache-tomcat-8.5.20",".html");
		FolderProcessor doc = new FolderProcessor("/home/soft01/doc",".html");
		
		pool.execute(eclipse);
		pool.execute(apache);
		pool.execute(doc);
		
		do {
			System.out.println("parallelism:"+pool.getParallelism());
			System.out.println("active:"+pool.getActiveThreadCount());
			System.out.println("task count:"+pool.getQueuedTaskCount());
			System.out.println("steal count:"+pool.getStealCount());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while((!eclipse.isDone())||(!apache.isDone())||(!doc.isDone()));
		pool.shutdown();
		List<String> results;
		results = eclipse.join();
		System.out.println("eclipse:"+results.size());
		results = apache.join();
		System.out.println("apache:"+results.size());
		results = doc.join();
		System.out.println("doc:"+results.size());
	}

}
