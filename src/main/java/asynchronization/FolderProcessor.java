package asynchronization;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderProcessor extends RecursiveTask<List<String>> {
	private String path;
	private String extension;

	public FolderProcessor(String path, String extension) {
		this.path = path;
		this.extension = extension;
	}

	@Override
	protected List<String> compute() {
		List<String> list = new ArrayList<String>();
		List<FolderProcessor> tasks = new ArrayList<FolderProcessor>();

		File file = new File(path);
		File[] content = file.listFiles();

		if (content != null) {
			for (int a = 0; a < content.length; a++) {
				if (content[a].isDirectory()) {
					FolderProcessor task = new FolderProcessor(content[a].getAbsolutePath(), extension);
					task.fork();
					tasks.add(task);
				} else {
					if (checkFile(content[a].getName())) {
						list.add(content[a].getAbsolutePath());
					}
				}
			}
			
			
		} 
		if(tasks.size()>50) {
			System.out.println(file.getAbsolutePath()+"该路径下元素已经达到了:"+tasks.size());
		}
		addResultFromTasks(list,tasks);
		return list;
	}

	private void addResultFromTasks(List<String> list,List<FolderProcessor> tasks) {
		for(FolderProcessor f:tasks) {
			list.addAll(f.join());
		}
	}
	public boolean checkFile(String name) {
		return name.endsWith(extension);
	}
}
