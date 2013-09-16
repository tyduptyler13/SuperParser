
public abstract class Filter{
	
	/**
	 * Checks to see if the input node is one to override.
	 * @param node
	 * @return
	 */
	public abstract boolean override(Data node);
	
	/**
	 * Gets custom output for the node.
	 * @param node
	 * @return
	 */
	public abstract String getOutput(Data node);
	
	/**
	 * Provides a default behavior for skipping data.
	 * @return {@link String}
	 */
	protected String skip(){
		return "";
	}
	
}
