

public class Section extends Data{
	
	public Section(String name){
		super(NodeType.Section);
		this.name = name;
	}
	
	public String name;

	@Override
	public String getOutput() {
		return name;
	}
	
}

