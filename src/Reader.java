import java.io.File;
import java.io.FileNotFoundException;


public abstract class Reader{
	
	protected File file;
	
	public Reader(File file){
		
		this.file = file;
		
	}
	
	public abstract Reader readIn() throws FileNotFoundException;
	
	public abstract String getOutput();
	
	public abstract DynamicNode getData();
	
	
}
