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
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;


public class GUI extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7632908392074124134L;

	private JButton openFiles, openDirectory, getSTS, getHST, getStats;
	private JTextArea console, data;
	private JPanel root;
	private JComponent tree;
	private static JFrame frame;

	public static String STSOutput = "";
	public static String HSTOutput = "";
	public static String StatsOutput = "";

	private Console c;
	
	private File lastDir = new File(".");

	private GUI(){

		root = new JPanel();
		root.setLayout(new BorderLayout());

		JPanel buttonbar = new JPanel();
		buttonbar.setLayout(new FlowLayout());

		root.add(buttonbar, BorderLayout.NORTH);

		data = new JTextArea();
		data.setEditable(false);
		JScrollPane dataScroll = new JScrollPane(data);
		dataScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		dataScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		dataScroll.setPreferredSize(new Dimension(300, 400));
		root.add(dataScroll, BorderLayout.EAST);

		openFiles = new JButton("Open Files");
		openFiles.setVerticalTextPosition(SwingConstants.CENTER);
		openFiles.setHorizontalTextPosition(SwingConstants.LEADING);
		openFiles.setActionCommand("openFiles");
		openFiles.addActionListener(this);

		openDirectory = new JButton("Open Directory");
		openDirectory.setVerticalTextPosition(SwingConstants.CENTER);
		openDirectory.setHorizontalTextPosition(SwingConstants.LEADING);
		openDirectory.setActionCommand("openDirectory");
		openDirectory.addActionListener(this);

		getSTS = new JButton("Get STS Results");
		getSTS.setVerticalTextPosition(SwingConstants.CENTER);
		getSTS.setHorizontalAlignment(SwingConstants.LEADING);
		getSTS.setActionCommand("getSTS");
		getSTS.addActionListener(this);
		getSTS.setEnabled(false);

		getHST = new JButton("Get HST Results");
		getHST.setVerticalTextPosition(SwingConstants.CENTER);
		getHST.setHorizontalAlignment(SwingConstants.LEADING);
		getHST.setActionCommand("getHST");
		getHST.addActionListener(this);
		getHST.setEnabled(false);
		
		getStats = new JButton("Get Stats");
		getStats.setVerticalTextPosition(SwingConstants.CENTER);
		getStats.setHorizontalAlignment(SwingConstants.LEADING);
		getStats.setActionCommand("getStats");
		getStats.addActionListener(this);
		getStats.setEnabled(false);

		console = new JTextArea();
		console.setEditable(false);
		console.setWrapStyleWord(true);
		console.setLineWrap(true);
		JScrollPane cscroll = new JScrollPane(console);
		cscroll.setPreferredSize(new Dimension(450,200));
		cscroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);


		buttonbar.add(openFiles);
		buttonbar.add(openDirectory);
		buttonbar.add(getSTS);
		buttonbar.add(getHST);
		buttonbar.add(getStats);

		root.add(cscroll, BorderLayout.SOUTH);

		add(root);

		//Setup universal console.
		c = new Console(){

			@Override
			public void print(String s) {
				console.append(s+"\r\n");
				Document d = console.getDocument();
				console.select(d.getLength(), d.getLength());
			}

		};
		Console.addPrinter(c);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("openFiles")){

			final JFileChooser in = new JFileChooser("Open");
			in.setCurrentDirectory(lastDir);
			in.addChoosableFileFilter(FileSystem.getFilter());
			in.setFileFilter(in.getChoosableFileFilters()[1]);
			in.setAcceptAllFileFilterUsed(false);
			in.setMultiSelectionEnabled(true);
			if (in.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
				SwingWorker<Void, Void> w = new SwingWorker<Void, Void>(){

					FileSystem fs;

					@Override
					protected Void doInBackground() throws Exception {
						lastDir = in.getCurrentDirectory();
						fs = new FileSystem(in.getSelectedFiles());
						return null;
					}

					protected void done(){
						Console.log(fs.getFileCount() + " files parsed.");
						showData(fs);
					}

				};

				w.execute();


			} else {
				Console.log("No folder selected.");
			}

		} else if (e.getActionCommand().equals("openDirectory")) {

			final JFileChooser in = new JFileChooser("Open");
			in.setCurrentDirectory(lastDir);
			in.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if (in.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){

				SwingWorker<Void, Void> w = new SwingWorker<Void, Void>(){

					FileSystem fs;

					protected Void doInBackground() throws Exception {
						lastDir = in.getCurrentDirectory();
						fs = new FileSystem(in.getSelectedFile());
						Console.log("Directory Selected.");
						return null;
					}

					protected void done(){

						Console.log(fs.getFileCount() + " nodes parsed.");
						showData(fs);

					}


				};

				w.execute();


			} else {
				Console.log("No folder selected.");
			}

		} else if (e.getActionCommand().equals("getSTS")){
			getOutput(STSOutput);
		} else if (e.getActionCommand().equals("getHST")){
			getOutput(HSTOutput);
		} else if (e.getActionCommand().equals("getStats")){
			getOutput(StatsOutput);
		}

		frame.repaint();

	}

	private synchronized void showData(FileSystem fs){
		if (tree == null){
			tree = fs.getComponents(data);
			root.add(tree, BorderLayout.WEST);
			getSTS.setEnabled(true);
			getHST.setEnabled(true);
			getStats.setEnabled(true);
			tree.setVisible(true);
			frame.setSize(650, 700);
		} else {
			tree.invalidate();
			root.remove(tree);
			tree = fs.getComponents(data);
			root.add(tree, BorderLayout.WEST);
			root.validate();
		}
	}

	private void getOutput(String s){
		StringSelection ss = new StringSelection(s.replaceAll("[\\(\\)]", ""));//Filter out "(" and ")"
		Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
		cb.setContents(ss, null);
		Console.log("The results have been copied to your clipboard. They may be pasted into excel or a text document.");
	}

	public static void createAndShowGUI() {
		frame = new JFrame("SuperParser");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			Console.log("The UI could not find the default system look and feel!");
		} catch (InstantiationException e) {
			Console.log("The UI could not create the look and feel.");
		} catch (IllegalAccessException e) {
			Console.log("The UI could not access the look and feel.");
		} catch (UnsupportedLookAndFeelException e) {
			Console.log("The UI look and feel is unsupported.");
		}finally{
			Console.log("The UI will use the default look and feel.");
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GUI gui = new GUI();
		gui.setOpaque(true);
		frame.setContentPane(gui);
		frame.setSize(500, 300);

		frame.pack();
		frame.setVisible(true);

		Console.log("Parser is ready. Please choose the files or directories you would like to parse.");
	}

	public static void resetOutput(){
		GUI.STSOutput = "";
		GUI.HSTOutput = "";
		GUI.StatsOutput = "";
	}

}
