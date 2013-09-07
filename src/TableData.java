import java.awt.Component;
import java.util.ArrayList;



public class TableData extends Data{

	public TableData(String title){
		super(NodeType.Table);
		this.title = title;
	}

	private String title;
	private ArrayList<String> headers = new ArrayList<String>();
	private ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();

	@Override
	public String getOutput() {
		String s = title + " {\n";

		for (String header : headers){
			s += header + "\t";
		}

		s += "\n";

		for (ArrayList<String> row : table){

			for (String cell : row){


				s += cell + "\t";

			}

			s += "\n";

		}

		s+= "}\n";

		return s;
	}

	public void addHeader(String s){
		headers.add(s);
	}

	public String getHeader(int index){
		return headers.get(index);
	}

	public void addRow(){
		table.add(new ArrayList<String>());
	}

	public void pushCell(String value){
		table.get(table.size()-1).add(value);
	}

	public void setCell(int row, int column, String value){
		ArrayList<String> _row = table.get(row);
		if (_row==null){
			_row = new ArrayList<String>();
			table.set(row, _row);
		}

		_row.set(column, value);
	}

	@Override
	public Component getComponent() {
		// TODO Auto-generated method stub
		return null;
	}

}

