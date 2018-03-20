package forkjoinException;

import java.util.concurrent.RecursiveTask;

public class Task extends RecursiveTask<Integer>{
	private int[] array;
	private int start,end;
	public Task(int[] arr,int start,int end) {
		this.array = arr;
		this.start = start;
		this.end = end;
	}
	@Override
	protected Integer compute() {
		System.out.println("start from:"+start+"-"+end);
		if(end - start < 10) {
			if((3>start)&&(3<end)) {
//				throw new RuntimeException("this task throws exception:"+start+":"+end);
				Exception e = new Exception("this task throws an Exception"+
						start+"to" +end);
				completeExceptionally(e);//使用该方法也能抛出异常
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			int middle = (start+end)/2;
			Task t = new Task(array,start,middle);
			Task t2 = new Task(array,middle,end);
			invokeAll(t,t2);
		}
		System.out.println("end from"+start+"to"+end);
		return 0;
	}

}
