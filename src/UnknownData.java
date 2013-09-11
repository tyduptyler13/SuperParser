import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;



public class UnknownData extends Data{
	
	public String data;
	
	public UnknownData(String data){
		super("UnknownData");
		this.data = data;
	}
	
	

	@Override
	public String getOutput() {
		
		return "Unknown Data Entry : \"" + data + "\"";
	}



	@Override
	public MutableTreeNode getComponents() {
		
		DefaultMutableTreeNode t = new DefaultMutableTreeNode(getOutput());
		
		return t;

	}



	@Override
	public String toString() {
		return getOutput();
	}
	
	
	
}
