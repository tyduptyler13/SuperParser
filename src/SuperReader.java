import java.io.File;

/**
 * This allows the program to read in saved parses.
 * @author Tyler
 *
 */
public class SuperReader implements Reader{

	private File file;
	
	public SuperReader(File file) {
		
		this.file = file;
		
	}

	@Override
	public Reader readIn() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public String getOutput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Data getData() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

