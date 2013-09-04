import java.util.HashMap;


public abstract class Data extends DynamicNode{
	
	public Data(NodeType type) {
		super(type);
	}

	public HashMap<String, String> data;
	
	/**
	 * Prints out the data in a string format.
	 * @return
	 */
	public abstract String getOutput();
	
	@Override
	public String toString(){
		return type.toString() + "(" + getOutput() + ")";
	}
	
}
