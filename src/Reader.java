import java.io.FileNotFoundException;

public interface Reader{
	
	public abstract Reader readIn() throws FileNotFoundException;
	
	public abstract String getOutput();
	
	public abstract DynamicNode getData();

}
