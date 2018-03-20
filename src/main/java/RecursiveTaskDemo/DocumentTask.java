package RecursiveTaskDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

public class DocumentTask extends RecursiveTask<Integer>{
	private String[][] document;
	private int first,last;
	private String word;
	public DocumentTask(String[][] d,int first,int last,String word) {
		this.document = d;
		this.first = first;
		this.last = last;
		this.word = word;
	}
	
	@Override
	protected Integer compute() {
		int result = 0;
		if(last-first<10) {
			result = processLines(document,first,last,word);
		}else {
			int middle = (last+first)/2;
			DocumentTask t = new DocumentTask(document,first,middle,word);
			DocumentTask t1 = new DocumentTask(document,middle,last,word);
			invokeAll(t,t1);
			
			try {
				result = groupResults(t.get(),t1.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	private int groupResults(int a,int b) {
		return a + b; 
	}
	private int processLines(String[][] document,int first,int last,String word) {
		List<LineTask> tasks = new ArrayList<LineTask>();
		for(int a=first;a<last;a++) {
			LineTask task = new LineTask(document[a],0,document[a].length,word);
			tasks.add(task);
		}
		invokeAll(tasks);
		int result = 0 ;
		for(int a=0;a<tasks.size();a++) {
			LineTask t = tasks.get(a);
			try {
				result += t.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		return result;
	}
}
