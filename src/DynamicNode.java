import java.util.LinkedList;

public class DynamicNode{

	public DynamicNode parent = null;
	protected LinkedList<DynamicNode> children;
	public NodeType type = null;

	/**
	 * A node for the Dynamic tree.
	 * @param parent Null if top otherwise the node above itself.
	 * @param type What is this node representing?
	 * @param data The data that exists at this point. Should only be needed if its the endpoint.
	 */
	public DynamicNode(NodeType type){

		this.type = type;

	}
	
	public void addChild(DynamicNode node){
		if (children == null) children = new LinkedList<DynamicNode>();
		children.add(node);
		node.parent = this;
	}
	
	public LinkedList<DynamicNode> getChildren(){
		if (children == null) children = new LinkedList<DynamicNode>();
		return children;
	}

	public String toString(){

		String result = type.toString() +" : {\n";

		for (DynamicNode node : children){

			result += "\t"+node.toString()+",";

		}

		return result;

	}
	
	/**
	 * Returns only this objects string variation.
	 * @return
	 */
	public String getOutput(){
		return type.toString();
	}

}

