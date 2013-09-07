import java.awt.Component;
import java.io.File;

public class FileNode extends Data{
	
	public File file;
	
	public FileNode(File file){
		super(NodeType.File);
		this.file = file;
	}
	
	public String getOutput(){
		return file.getName();
	}

	@Override
	public Component getComponent() {
		//TODO
		return null;
	}
	
}
