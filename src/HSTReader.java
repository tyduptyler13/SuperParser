import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class HSTReader extends FileNode implements Reader{


	/**
	 * 
	 */
	private static final long serialVersionUID = -3157882334393070531L;

	private ArrayList<Data> data = new ArrayList<Data>();
	private int participant;
	private String map;
	private String condition;

	public HSTReader(File file) {//External prep
		super(file, "HSTReader");
		
		String name = file.getName();//We can get stuff from the filename.
		participant = Integer.parseInt(name.split(",")[0].substring(12));
		
		//TODO get condition and map
	}

	@Override
	public HSTReader readIn() {//File content reading.
		Console.print("Reading file: ("+file.getName()+")");
		FileReader fr;
		BufferedReader br;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			Scanner s = new Scanner(br);

			while(s.hasNextLine()){
				String line = s.nextLine();
				if (line.contains("Fire Truck")){
					try {
						data.add(new Data(map, condition, participant, line));
					} catch (Exception e) {
						Console.error("An error occured reading a line from the file! Please check that it is formatted correctly.");
					}
				}
			}

			s.close();
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			Console.warn("The file specified was not found.");
		} catch (IOException e) {
			Console.warn("Sorry but the files seem to be giving the program some trouble. The files cannot be read.");
		}
		
		sort();//Sort on initialization.

		return this;
	}

	@Override
	public void getOutput() {
		
		GUI.HSTOutput += getLastString();//Already sorted.
		
	}

	private HSTReader sort(){
		Console.print("Sorting data.");
		Collections.sort(data);
		return this;
	}

	/**
	 * Collects the sorted data and returns the last bit of info from each section.
	 * 
	 * <p>WARNING!! - <br>
	 * Only run this on a sorted Reader. If it is not sorted this data will not be correct.
	 * </p>
	 * @return List of the last of the list sorted by appliance then number.
	 */
	private ArrayList<Data> getLast(){
		ArrayList<Data> result = new ArrayList<Data>();
		for (int x = 1; x<=10; ++x){
			Data max = null;
			for (Data d : data){
				if (d.getAppliance()==x){
					max = d;
				} else if (d.getAppliance()>x){//No longer will find data if it is sorted.
					break;
				}
			}
			if (max != null) //Skip any null entries.
				result.add(max);
		}
		return result;

	}

	/**
	 * Same as other get last except it returns the results in a single string.
	 * @return
	 */
	private String getLastString(){
		String result = "";
		for (Data d : getLast()){
			result += d.toString() + "\n";
		}
		return result;
	}

	public String toString(){
		return "HSTReader";
	}

	//Nested classes

	private class Data implements Comparable<Data>{

		int participant;
		String map;
		String condition;
		int number;
		int generation;
		double score;
		String action;
		int appliance;
		String location;
		String locationType;
		String finalLocation;
		String finalLocationType;
		String extra;

		public Data(String map, String condition, int participant, String in) throws Exception{
			this.participant = participant;
			this.map = map;
			this.condition = condition;
			load(in);
		}

		public int getAppliance(){
			return appliance;
		}

		public void load(String in) throws Exception{
			String[] parts = in.split("\t");
			if (parts.length != 10){
				throw new Exception("Invalid string. Cell data mismatch.");
			} else {
				number = Integer.parseInt(parts[0]);
				generation = Integer.parseInt(parts[1]);
				score = Double.parseDouble(parts[2]);
				action = parts[3];
				appliance = Integer.parseInt(parts[4].split(" ")[0]);
				location = parts[5];
				locationType = parts[6];
				finalLocation = parts[7];
				finalLocationType = parts[8];
				extra = parts[9];
			}
		}

		public String toString(){
			String result = map + "\t" + condition + "\t" + participant + "\t" + map + "\t" + number + "\t" + generation + "\t";
			result += score + "\t" + action + "\t" + appliance + " (Fire Truck)\t" + location + "\t" + locationType + "\t";
			result += finalLocation + "\t" + finalLocationType + "\t" + extra + "\t";
			return result;
		}

		/* This will use the appliance number times 1000 and no added together.
		 * The result is the difference between this and the other object.
		 * (non-Javadoc)
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		public int compareTo(Data data) {
			int app = this.appliance - data.appliance;
			if (app == 0){
				return this.number - data.number;
			} else {
				return app;
			}
		}

	}

}
