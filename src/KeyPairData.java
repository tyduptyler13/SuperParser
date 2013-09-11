import java.util.HashMap;
import java.util.Map;

import javax.swing.tree.MutableTreeNode;

public class KeyPairData extends Data{

	public KeyPairData(){
		super("KeyPairData");
	}

	protected KeyPairData(String type){
		super(type);
	}

	protected HashMap<String, String> data = new HashMap<String, String>();

	@Override
	public String getOutput() {
		String s = type;

		for (Map.Entry<String, String> e: data.entrySet()){
			s += e.getKey() + " :\t" + e.getValue() + "\n";
		}

		return s;
	}

	public void addEntry(String key, String value){
		data.put(key, value);
	}

	/**
	 * This will attempt to retrieve a key by name from the header.
	 * If the key is missing it will return null.
	 * @param key
	 * @return value of key
	 */
	public String requestEntry(String key){
		return data.get(key);
	}

	@Override
	public String toString() {
		return getOutput();
	}

	@Override
	public MutableTreeNode getComponents() {
		
		return new TreeNode(type, this);

	}

}
