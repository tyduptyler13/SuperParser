import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;


public class DynamicTree{
	
	public DynamicNode root;
	
	public DynamicTree(){
		
		root = new DynamicNode("Root");
		
	}
	
	public String toString(){
		
		String result = "DynamicTreeRoot : {\n";
		
		for (DynamicNode node : root.getChildren()){
			
			result += "\t"+node.toString()+",\n";
			
		}
		
		return result;
		
	}
	
	public JTree getTree(){
		
		DefaultMutableTreeNode r = new DefaultMutableTreeNode("Root");
		
		for (Data node : root.getChildren()){
			
			try{
				r.add(node.getComponents());
			} catch (Exception e){
				//Do nothing. Null nodes should be skipped.
			}
			
		}
		
		JTree tree = new JTree(r);

		return tree;
		
	}
	
}
