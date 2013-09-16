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
		
		TreeNode t = new TreeNode(getOutput(), this);
		
		return t;

	}



	@Override
	public String toString() {
		return getOutput();
	}
	
	
	
}
