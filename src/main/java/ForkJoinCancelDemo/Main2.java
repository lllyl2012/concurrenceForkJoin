package ForkJoinCancelDemo;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
/**
 * 		本例子演示了如果取消forkjoinpoll中的任务，forkjointask类提供了
 * cancel()方法来取消任务，但是只能取消还没有执行的任务；
 *			 如果任务已经开始执行，那么调用cancel()会接受一个名为mayInterruptIfRunning
 * 的boolean参数。顾名思义，如果传递true，用了这个方法后，即使
 * 已经在运行的任务也将取消，但是，api指出，forkJoinTask类的默认
 * 实现，这个属性并没有起作用。
 * 		为了克服这种局限性，我们创建了TaskManager类，它储存线程池中的所有任务，
 * 用一个方法取消所有任务，如果任务已经被执行，则cancel()返回false,因此
 * 可以尝试取消所有的任务而不用担心可能带来的影响。
 * 		这个范例实现在数字组中寻找一个数字。根据框架的推荐，
 * 我们将问题拆解为几个小问题。我们仅关心它的第一次出现，它出现后，
 * 我们就取消其他所有任务。
 * @author soft01
 *
 */
public class Main2 {

	public static void main(String[] args) {
		int[] arr = new int[1000];
		for(int a=0;a<1000;a++) {
			arr[a] = a;
			
		}
		TaskManager2 manager = new TaskManager2();
		Task2 t = new Task2(arr,0,1000,5,manager);
		ForkJoinPool pool = new ForkJoinPool(); 
		pool.execute(t);
		pool.shutdown();
		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("运行结束");
	}

}
