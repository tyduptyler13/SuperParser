import java.awt.Component;

import javax.swing.JTextArea;



public class UnknownData extends Data{
	
	public String data;
	
	public UnknownData(String data){
		super(NodeType.BasicEntry);
		this.data = data;
	}
	
	

	@Override
	public String getOutput() {
		
		return "Unknown Data Entry : \"" + data + "\"";
	}



	@Override
	public Component getComponent() {
		return new JTextArea(data);
	}
	
	
	
}
