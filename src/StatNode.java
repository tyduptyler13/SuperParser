
public class StatNode extends TreeNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = -239630796708960750L;

	private String data;

	public StatNode() {
		super("File Statistics");
	}

	public String getData(){
		return data;
	}

	public StatNode setData(String s){
		data = s;
		return this;
	}

	public StatNode appendData(String s){
		if (data != null){
			data += s;
		} else {
			return setData(s);
		}
		return this;
	}

}

