import javax.swing.tree.DefaultMutableTreeNode;


/**
 * My custom tree node for data node tracking.
 * @author Tyler
 *
 */
public class TreeNode extends DefaultMutableTreeNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8678579315929128975L;
	
	public TreeNode(String name){
		super(name);
	}
	
	public String getOutput(){
		String s = "";
		
		for (int i = 0; i<getChildCount(); ++i){
			TreeNode node = (TreeNode) getChildAt(i);
			
			s += node.getOutput() + "\n";
			
		}
		
		return s;
	}
	
}
