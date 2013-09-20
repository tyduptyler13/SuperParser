import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.tree.TreePath;

public class FileSystem{

	public FileFilter fileFilter;
	private TreeNode root;
	private JTree tree;
	private int fileCount = 0;

	private FileSystem(){

		fileFilter = getFilter();

	}

	public FileSystem(File directory){

		this();
		root = new FileNode(directory);
		tree = new JTree(root);
		getFiles(directory, root);

	}

	public FileSystem(File[] files){

		this();
		root = new TreeNode("Selected Files");
		tree = new JTree(root);
		addFiles(files, root);

	}

	private void addFiles(File[] files, TreeNode node){

		for (File file : files){

			if (file.isDirectory())
				getFiles(file, root);
			else
				addFile(file, node);

		}

	}

	private void addFile(File file, TreeNode node){

		try {
			node.add(ReaderFactory.createReader(file));
			fileCount++;
		} catch (FileNotFoundException e) {
			Console.warn("Something may have gone wrong. The file doesn't seem to exist. (" + file.getPath() + ")");
		} catch (ReaderFactory.FileNotSupported e) {
			Console.warn("This file type is not supported! Skipping... (" + file.getName() + ")");
		}

	}

	private void getFiles(File directory, TreeNode node){

		for (File file : directory.listFiles()){

			if (file.isFile()){
				addFile(file, node);
			} else if (file.isDirectory()) {
				FileNode n = new FileNode(file);
				node.add(n);
				getFiles(file, n);
			}

		}

	}

	public static final FileFilter getFilter(){

		return new FileFilter(){

			/**
			 * Directories, .hst, .sts, .sp
			 */
			@Override
			public boolean accept(File f) {
				if (f.isDirectory()) return true;
				return (f.getName().endsWith(".hst") || f.getName().endsWith(".sts"));
			}

			@Override
			public String getDescription() {
				return "Custom Filter. (hst|sts)";
			}

		};

	}

	public JComponent getComponents(final JTextArea output){

		JScrollPane pane = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		tree.addTreeSelectionListener(new TreeSelectionListener(){

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				TreePath nodes[] = tree.getSelectionPaths();

				String s = "";
				GUI.resetOutput();

				for (TreePath path : nodes){
					Object o = path.getLastPathComponent();
					
					//Special case where only one node will be output.
					if (o instanceof StatNode){	
						output.setText(((StatNode)o).getData());
						return;
					}
					
					if (o instanceof TreeNode){//We can only get output from these nodes.
						((TreeNode)o).getOutput();
					}
				}

				s += "HST Output: \n" + GUI.HSTOutput + "\n";
				s += "STS Output: \n" + GUI.STSOutput + "\n";

				output.setText(s);

			}

		});

		Dimension d = new Dimension(300, 400);
		tree.setMinimumSize(d);
		pane.setPreferredSize(d);

		return pane;

	}

	public int getFileCount(){

		return fileCount;

	}

}

