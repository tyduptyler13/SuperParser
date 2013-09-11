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
	
	public Data node;
	
	public TreeNode(String s, Data node){
		super(s);
		this.node = node;
	}
	
	public Data getNode(){
		return node;
	}
	
}
