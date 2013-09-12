import java.util.LinkedList;

public class DynamicNode{

	public DynamicNode parent = null;
	protected LinkedList<Data> children = new LinkedList<Data>();
	protected String type;
	
	public DynamicNode(String type){
		this.type = type;
	}

	public void addChild(Data node){
		children.add(node);
		node.parent = this;
	}

	public LinkedList<Data> getChildren(){
		return children;
	}

	public String toString(){

		return type;

	}
	
	/**
	 * Recursively retrieves data from tree and outputs it to a usable excel format.
	 * @return
	 */
	public String getOutput(){
		//TODO
		return "";
	}

}

