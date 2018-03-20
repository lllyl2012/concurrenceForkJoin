package RecursiveTaskDemo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

public class LineTask extends RecursiveTask<Integer>{
	private String[] line;
	private int first,last;
	private String word;
	public LineTask(String[] line,int first,int last,String word) {
		this.line = line;
		this.first = first;
		this.last = last;
		this.word = word;
	}
	public int groupResults(int a,int b) {
		return a+b;
	}
	protected Integer compute() {
		int result = 0;
		if(last - first<100) {
			result = count(line,first,last,word);
		}else {
			int middle = (first+last)/2;
			LineTask t = new LineTask(line,first,middle,word);
			LineTask t2 = new LineTask(line,middle,last,word);
			invokeAll(t,t2);
			
			try {
				result = groupResults(t.get(),t2.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return Integer.valueOf(result);
	}
	
	private int count(String[] line, int first,int last,String word) {
		int result = 0;
		for(int a=first;a<last;a++) {
			if(word.equals(line[a])) {
				result++;
			}
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
