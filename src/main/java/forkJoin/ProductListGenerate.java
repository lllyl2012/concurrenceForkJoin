package forkJoin;

import java.util.ArrayList;
import java.util.List;

public class ProductListGenerate {
	public List<Product> getList(){
		List<Product> ret = new ArrayList<Product>();
		for(int a=0;a<100;a++) {
			Product p = new Product();
			p.setName("product:"+a);
			p.setValue(10);
			ret.add(p);
		}
		return ret;
	}
}
