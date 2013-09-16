import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class FileSystem{

	private DynamicTree tree;
	private DynamicNode root;
	public FileFilter fileFilter;
	
	public static final Filter defaultNodeFilter = new Filter(){

		@Override
		public boolean override(Data node) {
			if (node instanceof UnknownData){
				return true;
			} else{
				return false;
			}
			
		}

		@Override
		public String getOutput(Data node) {
			return skip();
		}
		
	};

	private FileSystem(){

		fileFilter = getFilter();
		tree = new DynamicTree();
		root = tree.root;
	}

	public FileSystem(File directory){

		this();
		getFiles(directory, root);

	}

	public FileSystem(File[] files){

		this();
		addFiles(files, root);

	}

	private void addFiles(File[] files, DynamicNode node){

		for (File file : files){

			addFile(file, node);

		}

	}

	private void addFile(File file, DynamicNode node){

		try {
			node.addChild(ReaderFactory.createReader(file));
		} catch (FileNotFoundException e) {
			Console.warn("Something may have gone wrong. The file doesn't seem to exist.");
		}

	}

	private void getFiles(File directory, DynamicNode node){

		for (File file : directory.listFiles()){

			if (file.isFile()){
				addFile(file, node);
			} else if (file.isDirectory()) {
				getFiles(file, new FileNode(file));
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
				return (f.getName().endsWith(".hst") || f.getName().endsWith(".sts") || f.getName().endsWith(".sp"));
			}

			@Override
			public String getDescription() {
				return "Custom Filter. (hst|sts|sp)";
			}

		};

	}
	
	public Component getComponents(final JTextArea output){
		return getComponents(output, defaultNodeFilter);
	}

	public Component getComponents(final JTextArea output, final Filter f){
		
		final JTree t = tree.getTree();
		JScrollPane pane = new JScrollPane(t, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		t.addTreeSelectionListener(new TreeSelectionListener(){

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				TreePath nodes[] = t.getSelectionPaths();
				
				String s = "";
				
				for (TreePath path : nodes){
					Object o = path.getLastPathComponent();
					if (o instanceof TreeNode){//We can only get output from these nodes.
						s += ((TreeNode)o).getNode().getOutput(f);
					}
				}
				
				output.setText(s);
				
			}
			
		});

		return pane;
		
	}

	public String getOutput(){

		return root.getOutput();

	}

	public int getFileCount(){
		
		return getFileCount(root);
		
	}
	
	private int getFileCount(DynamicNode node){
		
		int count = 0;
		
		for (Data child : node.children){
			
			if (child instanceof Reader){
				count++;
			} else {
				count += getFileCount(child);
			}
			
		}
		
		return count;
		
	}


}

