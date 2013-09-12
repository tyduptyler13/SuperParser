import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.filechooser.FileFilter;

public class FileSystem{

	private DynamicTree tree;
	private DynamicNode root;
	public FileFilter filter;

	private FileSystem(){

		filter = getFilter();
		tree = new DynamicTree();
		root = tree.root;
	}

	public FileSystem(File directory){

		this();
		getFiles(directory, root);

	}

	public FileSystem(File[] files){

		this();
		addFiles(files, root);

	}

	private void addFiles(File[] files, DynamicNode node){

		for (File file : files){

			addFile(file, node);

		}

	}

	private void addFile(File file, DynamicNode node){

		try {
			node.addChild(ReaderFactory.createReader(file));
		} catch (FileNotFoundException e) {
			Console.warn("Something may have gone wrong. The file doesn't seem to exist.");
		}

	}

	private void getFiles(File directory, DynamicNode node){

		for (File file : directory.listFiles()){

			if (file.isFile()){
				addFile(file, node);
			} else if (file.isDirectory()) {
				getFiles(file, new FileNode(file));
			}

		}

	}

	public static final FileFilter getFilter(){

		return new FileFilter(){

			/**
			 * Directories, .hst, .sts, .sp
			 */
			@Override
			public boolean accept(File f) {
				if (f.isDirectory()) return true;
				return (f.getName().endsWith(".hst") || f.getName().endsWith(".sts") || f.getName().endsWith(".sp"));
			}

			@Override
			public String getDescription() {
				return "Custom Filter. (hst|sts|sp)";
			}

		};

	}

	public Component getComponents(){
		
		JTree t = tree.getTree();
		JScrollPane pane = new JScrollPane(t, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		return pane;
		
	}

	public String getOutput(){

		return root.getOutput();

	}

	public int getFileCount(){
		
		return getFileCount(root);
		
	}
	
	private int getFileCount(DynamicNode node){
		
		int count = 0;
		
		for (Data child : node.children){
			
			if (child instanceof Reader){
				count++;
			} else {
				count += getFileCount(child);
			}
			
		}
		
		return count;
		
	}


}

