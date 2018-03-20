package forkjoinException;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
/**
 * 如何在fork/join框架中抛出一个异常：
 * 不能在ForkJoinTask类的compute()方法中抛出任务非
 * 运行时异常。因为该方法的实现没有包含throws声明。
 * 我们可以利用forkJoinTask类的getException()方法
 * 抛出异常。或者completeExceptionally(Exception e)方法
 * @author soft01
 *
 */
public class Main {
	public static void main(String[] args) {
		int[] array = new int[100];
		Task task = new Task(array,0,100);
		
		ForkJoinPool pool = new ForkJoinPool();
		pool.execute(task);
		pool.shutdown();
		
		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		if(task.isCompletedAbnormally()) {
//			System.out.println("main:"+task.getException());
//		
//		}
		System.out.println("result:"+task.join());
	}
}
