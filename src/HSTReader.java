import java.io.File;

import javax.swing.tree.MutableTreeNode;


public class HSTReader extends FileNode implements Reader{

	
	public HSTReader(File file) {
		super(file);
		this.file = file;
	}

	@Override
	public HSTReader readIn() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public String getOutput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
