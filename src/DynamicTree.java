
public class DynamicTree{
	
	public DynamicNode root;
	
	public DynamicTree(){
		
		root = new DynamicNode(NodeType.Root);
		
	}
	
	public String toString(){
		
		String result = "DynamicTreeRoot : {\n";
		
		for (DynamicNode node : root.getChildren()){
			
			result += "\t"+node.toString()+",\n";
			
		}
		
		return result;
		
	}
	
}
