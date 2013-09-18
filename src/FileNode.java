import java.io.File;

public class FileNode extends TreeNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2966491518943704310L;

	protected File file;

	public FileNode(File file){
		super("File: " + file.getName());
		this.file = file;
	}

	public FileNode(File file, String type){
		super(type);
		this.file = file;
	}

	public File getFile(){
		return file;
	}

	//Use default getOutput from TreeNode

	@Override
	public String toString() {
		if (file.isDirectory()){
			return "Directory: " + file.getName();
		} else {
			return "File: " + file.getName();
		}
	}

}
