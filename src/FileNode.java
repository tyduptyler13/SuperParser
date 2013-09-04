import java.io.File;

public class FileNode extends DynamicNode{
	
	public File file;
	
	public FileNode(File file){
		super(NodeType.File);
		this.file = file;
	}
	
	public String getOutput(){
		return file.getName();
	}
	
}
