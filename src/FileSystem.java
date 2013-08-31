import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class FileSystem{
	
	private ArrayList<File> files = new ArrayList<File>();
	private ArrayList<Reader> readers;
	
	public FileSystem(File directory){
		
		this.files.add(directory);
		
	}
	
	public FileSystem(File[] files){
		
		for (File file : files){
			this.files.add(file);
		}
		
	}
	
	public FileSystem getFiles(){
		
		ArrayList<File> files = new ArrayList<File>();
		
		for (File file : this.files){
			
			if (file.isDirectory()){
				files.addAll(getFiles(file));
			} else if (file.isFile()){
				files.add(file);
			}
			
		}
		
		this.files = files;
		
		return this;
	}
	
	/**
	 * Recursively finds all sts files.
	 * @param directory Top Directory.
	 * @return All sub files with .sts extension.
	 */
	private ArrayList<File> getFiles(File directory){
		
		ArrayList<File> files = new ArrayList<File>();
		
		for (File file : directory.listFiles()){
			
			if (file.isDirectory()){
				files.addAll(getFiles(file));
			} else if (file.getName().endsWith(".sts")){
				files.add(file);
			}
			
		}
		
		return files;
		
	}
	
	public static FileFilter getFilter(){
		
		return new FileFilter(){

			/**
			 * Directories, .hst, .sts
			 */
			@Override
			public boolean accept(File f) {
				if (f.isDirectory()) return true;
				return (f.getName().endsWith(".hst") || f.getName().endsWith(".sts"));
			}

			@Override
			public String getDescription() {
				return "Files this program is capable of handling. (hst|sts)";
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

