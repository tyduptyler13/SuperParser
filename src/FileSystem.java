import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.filechooser.FileFilter;

public class FileSystem{
	
	private ArrayList<File> files = new ArrayList<File>();
	private ArrayList<Reader> readers;
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
		this.files.add(directory);
		getFiles(root);
		
	}
	
	public FileSystem(File[] files){
		
		this();
		for (File file : files){
			this.files.add(file);
		}
		getFiles(root);
		
	}
	
	private FileSystem getFiles(DynamicNode node){
		
		ArrayList<File> files = new ArrayList<File>();
		
		for (File file : this.files){
			
			if (file.isDirectory()){
				DynamicNode folder = new FileNode(file);
				node.addChild(folder);
				files.addAll(getFiles(file, folder));
			} else if (file.isFile()){
				DynamicNode fileNode = new FileNode(file);
				node.addChild(fileNode);
				files.add(file);
			}
			
		}
		
		this.files = files;
		
		return this;
	}

	private ArrayList<File> getFiles(File directory, DynamicNode node){
		
		ArrayList<File> files = new ArrayList<File>();
		
		for (File file : directory.listFiles()){
			
			if (file.isDirectory()){
				DynamicNode folder = new FileNode(file);
				node.addChild(folder);
				files.addAll(getFiles(file, folder));
			} else if (filter.accept(file)){
				DynamicNode fileNode = new FileNode(file);
				node.addChild(fileNode);
				files.add(file);
			}
			
		}
		
		return files;
		
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
	
	public FileSystem parse(){
		
		readers = new ArrayList<Reader>(files.size());
		
		for (File file : files){
			
			if (file.isFile()){
				try {
					readers.add(ReaderFactory.createReader(file).readIn());
				} catch (FileNotFoundException e) {
					Console.print("File ("+file.getName()+") was not found. Skipping.");
				} catch (Exception e) {
					Console.warn(e.getMessage() + " (Skipping)");
				}
				
			}
			
		}
		
		return this;
		
	}
	
	public String getOutput(){
		
		String result = "";
		
		for (Reader reader : readers){
			result += reader.getOutput();
		}
		
		return result;
		
	}
	
	public int getFileCount(){
		return files.size();
	}
	
	
}

