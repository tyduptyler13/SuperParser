import java.util.LinkedList;

public class DynamicNode{

	public DynamicNode parent = null;
	public LinkedList<DynamicNode> children;
	public NodeType type = null;
	/**
	 * This usually is null unless the node is an endpoint. Only endpoints will have actual data.
	 */
	public Data data;

	/**
	 * A node for the Dynamic tree.
	 * @param parent Null if top otherwise the node above itself.
	 * @param type What is this node representing?
	 * @param data The data that exists at this point. Should only be needed if its the endpoint.
	 */
	public DynamicNode(DynamicNode parent,NodeType type, Data data){

		this.parent = parent;
		this.type = type;
		this.data = data;

	}

	public String toString(){

		String result = type.toString() +" : {\n";

		for (DynamicNode node : children){

			result += "\t"+node.toString()+",";

		}

		return result;

	}

}

