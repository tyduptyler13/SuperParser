import java.util.LinkedList;

public class DynamicNode{

	public DynamicNode parent = null;
	protected LinkedList<Data> children;
	protected String type;
	
	public DynamicNode(String type){
		this.type = type;
	}

	public void addChild(Data node){
		if (children == null) children = new LinkedList<Data>();
		children.add(node);
		node.parent = this;
	}

	public LinkedList<Data> getChildren(){
		if (children == null) children = new LinkedList<Data>();
		return children;
	}

	public String toString(){

		return type;

	}

}

