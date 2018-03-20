package forkJoin;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {

	public static void main(String[] args) {
		ProductListGenerate generate = new ProductListGenerate();
		List<Product> list = generate.getList();
		System.out.println("long:"+list.size());
		Task t = new Task(list, 0, list.size(), 0.2);
		ForkJoinPool pool = new ForkJoinPool();
		pool.execute(t);
		do {
			System.out.println("Thread Count:" + pool.getActiveThreadCount());
			System.out.println("Thread Steal:" + pool.getStealCount());
			System.out.println("parallelism" + pool.getParallelism());
		} while (!t.isDone());
		pool.shutdown();
		if (t.isCompletedAbnormally()) {
			System.out.println("正常结束");
		}
		for (int a = 0; a < list.size(); a++) {
			Product p = list.get(a);
			double value = p.getValue();
			if (value == 12) {

				System.out.println(p.getName() + ":" + p.getValue());
			}else {
				System.out.println(p.getName() + ":" + p.getValue()+"没有正常加价");
			}
		}
		System.out.println("end");
	}

}
