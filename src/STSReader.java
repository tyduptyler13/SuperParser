import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public final class STSReader extends Reader{

	private FileNode node;
	private Header head;

	public STSReader(File file) {
		super(file);
		node = new FileNode(file);
	}

	@Override
	public Reader readIn() throws FileNotFoundException {
		//Prep data tree.
		head = new Header();
		node.addChild(head);

		//Read into tree.
		Scanner s = new Scanner(new FileReader(file));

		while (s.hasNextLine()){

			String line = s.nextLine();

			if (isHeader(line)){

				String[] parts = line.split(" :\t");
				head.addEntry(parts[0], parts[1]);

			}

			if (isSection(line)){

				if (line.contains("Overall Statistics")){

					Section section = new Section("Overall Statistics");
					node.addChild(section);

					readSection(section, s);

				} else if (line.contains("Landscape Count")){

					Section section = new Section("Landscape Count");
					node.addChild(section);

					readSection(section, s);

				} else if (line.contains("Appliance Statistics")) {

					Section section = new Section("Appliance Statistics");
					node.addChild(section);

					readTableSection(section, s);//Time Spent doing action
					
					readTableSection(section, s);//Time Spent in mode
					
					if (s.nextLine().equals("Resources Used")){
						
						Section ru = new Section("Resources Used");
						
						section.addChild(ru);
						
						readSection(ru, s);
						
					}


				} else {
					
					node.addChild(new UnknownData(line));
					
				}

			}

		}

		s.close();

		return this;
	}

	@Override
	public String getOutput(){
		return node.getOutput();
	}

	@Override
	public DynamicNode getData() {
		return node;
	}

	private boolean isHeader(String s){
		return s.matches("[A-Za-z ]+ :\t[A-Za-z0-9\\.\\\\:-_ ]+");
	}

	private boolean isSection(String s){
		return s.matches("\\*[A-Za-z ]+\\*");
	}

	private void readTableSection(Section section, Scanner s){

		String line;
		while (!(line = s.nextLine()).startsWith("Time Spent")){
			section.addChild(new UnknownData(line));
		}
		
		String title = line;
		
		TableData table = new TableData(title);
		section.addChild(table);
		
		line = s.nextLine();

		String[] headers = line.split("\t");

		for (String header : headers){

			table.addHeader(header);

		}

		while (s.hasNextInt()){
			
			line = s.nextLine();

			String[] cells = line.split("\t");
			table.addRow();

			for (String cell : cells){

				table.pushCell(cell);

			}

		}

	}

	private void readSection(Section section, Scanner s){
		String line;
		KeyPairData node = new KeyPairData();

		while(!(line = s.nextLine()).equals("")){
			String[] parts = line.split(" :\t");
			node.addEntry(parts[0], parts[1]);
		}

		section.addChild(node);
	}

}

