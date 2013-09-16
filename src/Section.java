import javax.swing.tree.MutableTreeNode;

public class Section extends Data{

	public Section(String name){
		super("Section");
		this.name = name;
	}

	public String name;

	@Override
	public String getOutput() {
		String s = this.name + "\n\n";
		
		for (Data node : children){
			s += node.getOutput() + "\n";
		}
		
		return s;
	}
	
	@Override
	public String toString(){
		return type + " : " + name;
	}

	@Override
	public MutableTreeNode getComponents() {
		
		TreeNode node = new TreeNode(this.name, this);
		
		for (Data child : children){
			
			node.add(child.getComponents());
			
		}
		
		return node;
	}

}

