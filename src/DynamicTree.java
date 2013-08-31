
public class DynamicTree{
	
	public DynamicNode root;
	
	public DynamicTree(){
		
		root = new DynamicNode(null, NodeType.root, null);
		
	}
	
	public String toString(){
		
		String result = "DynamicTreeRoot : {\n";
		
		for (DynamicNode node : root.children){
			
			result += "\t"+node.toString()+",";
			
		}
		
		return result;
		
	}
	
}
