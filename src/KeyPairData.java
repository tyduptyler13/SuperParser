import java.util.HashMap;
import java.util.Map;

public class KeyPairData extends Data{

	public KeyPairData(){
		super(NodeType.BasicEntry);
	}

	protected KeyPairData(NodeType type){
		super(type);
	}

	protected HashMap<String, String> data = new HashMap<String, String>();

	@Override
	public String getOutput() {
		String s = "";

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

}
