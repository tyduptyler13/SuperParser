import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class GUI extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7632908392074124134L;

	private JButton openFiles, openDirectory, parse;
	private JTextArea console;
	private JPanel dataArea;
	private FileSystem fs;

	private GUI(){

		JPanel root = new JPanel();
		root.setLayout(new BorderLayout());

		JPanel buttonbar = new JPanel();
		buttonbar.setLayout(new FlowLayout());
		
		JPanel dataArea = new JPanel();
		dataArea.setVisible(false);

		openFiles = new JButton("OpenFiles");
		openFiles.setVerticalTextPosition(SwingConstants.CENTER);
		openFiles.setHorizontalTextPosition(SwingConstants.LEADING);
		openFiles.setActionCommand("openFiles");
		openFiles.addActionListener(this);

		openDirectory = new JButton("Open Directory");
		openDirectory.setVerticalTextPosition(SwingConstants.CENTER);
		openDirectory.setHorizontalTextPosition(SwingConstants.LEADING);
		openDirectory.setActionCommand("openDirectory");
		openDirectory.addActionListener(this);

		parse = new JButton("Parse");
		parse.setVerticalTextPosition(SwingConstants.CENTER);
		parse.setHorizontalTextPosition(SwingConstants.LEADING);
		parse.setActionCommand("parse");
		parse.setEnabled(false);
		parse.addActionListener(this);

		console = new JTextArea();
		console.setEditable(false);
		console.setWrapStyleWord(true);
		console.setLineWrap(true);
		console.setPreferredSize(new Dimension(450,200));
		JScrollPane cscroll = new JScrollPane(console);
		cscroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);


		buttonbar.add(openFiles);
		buttonbar.add(openDirectory);
		buttonbar.add(parse);

		root.add(buttonbar, BorderLayout.NORTH);
		root.add(dataArea, BorderLayout.CENTER);
		root.add(console, BorderLayout.SOUTH);

		add(root);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("openFiles")){

			JFileChooser in = new JFileChooser("Open");
			in.setCurrentDirectory(new File("."));
			in.addChoosableFileFilter(FileSystem.getFilter());
			in.setFileFilter(in.getChoosableFileFilters()[1]);
			in.setAcceptAllFileFilterUsed(false);
			in.setMultiSelectionEnabled(true);
			if (in.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
				fs = new FileSystem(in.getSelectedFiles());
				print(fs.getFileCount() + " files selected.");
				parse.setEnabled(true);
			} else {
				print("No folder selected.");
			}

		} else if (e.getActionCommand().equals("openDirectory")) {

			JFileChooser in = new JFileChooser("Open");
			in.setCurrentDirectory(new File("."));
			in.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if (in.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
				fs = new FileSystem(in.getCurrentDirectory());
				print("Directory Selected.");
				print(fs.getFileCount() + " files found.");
				parse.setEnabled(true);
			} else {
				print("No folder selected.");
			}

		} else if (e.getActionCommand().equals("parse")){
			print("Parsing files. This can take some time.");
			fs.parse();

			dataArea.add(fs.getComponents());
			dataArea.setVisible(true);
			
			//Clipboard code.
			StringSelection ss = new StringSelection(fs.getOutput());
			Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
			cb.setContents(ss, null);
			print("Results are ready. The results have been copied to your clipboard. Paste them to your master coding sheet.");
		}
	}

	private void print(String s){
		console.append("[SuperParser] "+s+"\r\n");
	}

	public static void createAndShowGUI() {
		JFrame frame = new JFrame("SuperParser");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			Console.print("The UI could not find the default system look and feel!");
		} catch (InstantiationException e) {
			Console.print("The UI could not create the look and feel.");
		} catch (IllegalAccessException e) {
			Console.print("The UI could not access the look and feel.");
		} catch (UnsupportedLookAndFeelException e) {
			Console.print("The UI look and feel is unsupported.");
		}finally{
			Console.print("The UI will use the default look and feel.");
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GUI gui = new GUI();
		gui.setOpaque(true);
		frame.setContentPane(gui);
		frame.setSize(500, 300);

		frame.pack();
		frame.setVisible(true);

		gui.print("Parser is ready. Please choose the files or directories you would like to parse.");
	}



}

