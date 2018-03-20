package forkJoin;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class Task extends RecursiveAction{
	private List<Product> list;
	private int firstNum;
	private int lastNum;
	private double increment;
	public Task(List<Product> list,int f,int l,double i) {
		this.list = list;
		this.firstNum = f;
		this.lastNum = l;
		this.increment = i;
	}
	@Override
	protected void compute() {
		if(lastNum-firstNum<10) {
			updatePrices();
		}else {
			int middle = (lastNum+firstNum)/2;
			Task task1 = new Task(list,firstNum,middle,increment);
			Task task2 = new Task(list,middle,lastNum,increment);
			invokeAll(task1,task2);
		}
	}
	
	private void updatePrices() {
		for(int a=firstNum;a<lastNum;a++) {
			Product p = list.get(a);
			p.setValue(p.getValue()*(1+increment));
		}
	}

}
