package RecursiveTaskDemo;

import java.util.Random;

public class DocumentMock {
	private String[] words = {"every","time","you","say","more","then","i","want","to","do","for","all","of","us"};
	public String[][] generateDocument(int row,int col,String word){
		String[][] document = new String[row][col];
		int count=0;
		Random rand = new Random();
		
		for(int a=0;a<row;a++) {
			for(int b=0;b<col;b++) {
				document[a][b] = words[rand.nextInt(words.length-1)];
				if(document[a][b]==word) {
					count++;
				}
			}
		}
		System.out.println(word+"总共有"+count);
		return document;
	}
//	public static void main(String[] args) {
//		DocumentMock m = new DocumentMock();
//		m.generateDocument(3, 30, "all");
//	}
}
