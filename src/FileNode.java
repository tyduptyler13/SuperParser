import java.io.File;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

public class FileNode extends Data{
	
	public File file;
	
	public FileNode(File file){
		super("File");
		this.file = file;
	}
	
	public String getOutput(){
		return file.getName();
	}

	@Override
	public String toString() {
		return "File: " + file.getName();
	}

	@Override
	public MutableTreeNode getComponents() {
		
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(file.getName());
		
		for (Data child : children){
			
			node.add(child.getComponents());
			
		}
		
		return node;
	}
	
}
