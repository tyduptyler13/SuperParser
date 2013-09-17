import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class STSReader extends FileNode implements Reader{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4490646622952273589L;

	private Participant p = new Participant();

	public STSReader(File file) {
		super(file, "STSReader");
	}

	@Override
	public STSReader readIn() throws FileNotFoundException {

		//Read into tree.
		Scanner s = new Scanner(new FileReader(file));

		while (s.hasNextLine()){

			String line = s.nextLine();

			try{

				if (isHeader(line)){

					String[] parts = breakLine(line);

					if (parts[0].contains("Parameter File")){
						String tmp[] = parts[1].split("\\\\");
						p.parameter = tmp[tmp.length-1];//Get the end of the path.
					} else if (parts[0].contains("Participant Id")){
						p.id = parts[1];
					} else if (parts[0].contains("Trial")){
						p.trial = parts[1];
					}

				}

				if (isSection(line)){

					if (line.contains("Overall Statistics")){

						while (!(line = s.nextLine()).equals("")){

							String[] parts = breakLine(line);

							if (parts[0].contains("Overall Performance Score")){
								p.performance = parts[1];//Should get overwritten by the second occurance.
							} else if (parts[0].contains("Generations")){
								p.generations = parts[1];
							}

						}

					} else if (line.contains("Consumable Landscape Count")){

						while (!(line = s.nextLine()).equals("")){

							String[] parts = breakLine(line);

							if (parts[0].contains("Forest")){
								p.forest = parts[1];
							} else if (parts[0].contains("Pasture")){
								p.pasture = parts[1];
							} else if (parts[0].contains("Clearing")){
								p.clearing = parts[1];
							} else if (parts[0].contains("House")){
								p.house = parts[1];
							} else if (parts[0].contains("Total Consumable")){
								p.totalConsumableLandscape = parts[1];
							}

						}

					} else if (line.contains("Station Statistics")){

						while (!(line = s.nextLine()).equals("")){

							String[] parts = breakLine(line);

							if (parts[0].contains("Commands Made")){
								p.commandsMade = parts[1];
							}

						}

					} else if (line.contains("Appliance Statistics")){

						readInTables(s);

					}

				}

			} catch (IndexOutOfBoundsException e){

				Console.warn("Index out of bounds: ");
				e.printStackTrace();

			}


		}

		s.close();

		return this;
	}

	public String getOutput(){
		return p.toString();
	}

	private boolean isHeader(String s){
		return s.matches("[A-Za-z ]+ :\t[A-Za-z0-9\\.\\\\:\\-_/()% ]+");
	}

	private boolean isSection(String s){
		return s.matches("\\*[A-Za-z ]+\\*");
	}

	private void readInTables(Scanner s){

		String line;

		while (!(line = s.nextLine()).equals("")){

			if (line.startsWith("Number\tIdle")){

				while ((line = s.nextLine()).matches("[0-9]+.*")){

					String match = getMatch(line, "([0-9]+\t)+");
					match = match.substring(0, match.length()-1);//Trim last character off.
					p.vehicles.add(match);

				}

			}

			if (line.startsWith("Number\tManual")){

				while ((line = s.nextLine()).matches("[0-9]+.*")){

					String[] parts = getMatches(line, "([0-9]+\t)");

					p.manual.add(trim(parts[1]));
					p.auto.add(trim(parts[2]));

				}

			}

			if (line.contains("Used")){

				while (!(line = s.nextLine()).isEmpty()){

					String[] parts = line.split("\t");
					p.resources.add(parts[3]);

				}

			}

		}

	}

	private String[] breakLine(String s){
		return s.split("\t", 2);
	}

	private String getMatch(String target, String regex){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(target);

		if (m.find()){
			return m.group();
		} else {
			return "";
		}

	}

	private String[] getMatches(String target, String regex){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(target);

		ArrayList<String> list = new ArrayList<String>();

		while(m.find()){
			list.add(m.group());
		}

		String[] s = new String[list.size()];
		return list.toArray(s);
	}

	private String trim(String s){
		return s.replaceAll("\\s+$", "");
	}

}

