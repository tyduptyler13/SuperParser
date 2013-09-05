
public abstract class Data extends DynamicNode{
	
	public Data(NodeType type) {
		super(type);
	}
	
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
