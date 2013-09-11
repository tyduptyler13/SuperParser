import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

public class Section extends Data{

	public Section(String name){
		super("Section");
		this.name = name;
	}

	public String name;

	@Override
	public String getOutput() {
		return this.name;
	}
	
	@Override
	public String toString(){
		return type + " : " + name;
	}

	@Override
	public MutableTreeNode getComponents() {
		
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(this.name);
		
		for (Data child : children){
			
			node.add(child.getComponents());
			
		}
		
		return node;
	}

}

