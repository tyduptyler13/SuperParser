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

	/**
	 * Recursively retrieve all of the data within the tree.
	 */
	public void getOutput(){

		for (int i = 0; i<getChildCount(); ++i){
			TreeNode node = (TreeNode) getChildAt(i);

			node.getOutput();

		}

	}

}
