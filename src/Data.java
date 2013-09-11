import javax.swing.tree.MutableTreeNode;

public abstract class Data extends DynamicNode{
	
	public Data(String type) {
		super(type);
	}

	/**
	 * Prints out the data in a string format.
	 * @return
	 */
	public abstract String getOutput();
	
	@Override
	abstract public String toString();
	
	/**
	 * Recursively retrieves all awt/swing components for display.
	 * @return AWT Container with all components in the data.
	 */
	public abstract MutableTreeNode getComponents();
	
}
