import java.util.ArrayList;


public class Participant{

	String map = "?";
	String condition = "?";
	String id;
	String trial;
	String performance;
	String generations;
	String forest;
	String pasture;
	String clearing;
	String house;
	String totalConsumableLandscape;
	String commandsMade;

	ArrayList<String> vehicles = new ArrayList<String>();
	ArrayList<String> manual = new ArrayList<String>();
	ArrayList<String> auto = new ArrayList<String>();
	ArrayList<String> resources = new ArrayList<String>();

	@Override
	public String toString(){

		String result = "";

		for (int i = 0; i < vehicles.size(); ++i){
			result += map + "\t" + condition + "\t" + id + "\t" + trial + "\t" + performance + "\t" + 
					generations + "\t" + forest + "\t" + pasture + "\t" + clearing + "\t" +
					house + "\t" + totalConsumableLandscape + "\t" + commandsMade + "\t" +
					vehicles.get(i) + "\t" + manual.get(i) + "\t" + auto.get(i) + "\t" + 
					resources.get(i) + "\r\n";
		}

		return result;
	}

}
