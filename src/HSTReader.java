import java.io.File;


public class HSTReader extends FileNode implements Reader{


	/**
	 * 
	 */
	private static final long serialVersionUID = -3157882334393070531L;

	public HSTReader(File file) {
		super(file, "HSTReader");
		this.file = file;
	}

	@Override
	public HSTReader readIn() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void getOutput() {
		// TODO Auto-generated method stub
	}

}
