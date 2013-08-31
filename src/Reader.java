import java.io.File;


public abstract class Reader{
	
	private File file;
	
	public Reader(File file){
		
		this.file = file;
		
	}
	
	public abstract Reader readIn();
	
	public abstract String getOutput();
	
	
	
	
}
