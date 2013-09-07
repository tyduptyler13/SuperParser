import java.awt.Component;



public class Section extends Data{

	public Section(String name){
		super(NodeType.Section);
		this.name = name;
	}

	public String name;

	@Override
	public String getOutput() {
		return this.name;
	}

	@Override
	public Component getComponent() {
		// TODO Auto-generated method stub
		return null;
	}

}

