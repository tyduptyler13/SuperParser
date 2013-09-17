import java.io.FileNotFoundException;

public interface Reader{
	
	public Reader readIn() throws FileNotFoundException;
	
	public String getOutput();

}
