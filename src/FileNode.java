import java.io.File;

import javax.swing.tree.MutableTreeNode;

public class FileNode extends Data{

	protected File file;

	public FileNode(File file){
		super("File");
		this.file = file;
	}

	public FileNode(File file, String type){
		super(type);
		this.file = file;
	}

	public File getFile(){
		return file;
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

		TreeNode node = new TreeNode(file.getName(), this);

		for (Data child : children){

			node.add(child.getComponents());

		}

		return node;
	}

}
