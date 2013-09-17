import java.io.File;
import java.io.FileNotFoundException;


public class ReaderFactory{

	public static TreeNode createReader(File file) throws FileNotFoundException, FileNotSupported{

		if (file.getName().endsWith(".hst")){
			return new HSTReader(file).readIn();
		} else if (file.getName().endsWith(".sts")){
			return new STSReader(file).readIn();
		} else {
			throw new FileNotSupported();
		}

	}

	public static class FileNotSupported extends Exception{

		/**
		 * 
		 */
		private static final long serialVersionUID = 8555816964005035413L;

		public FileNotSupported(){
			super("This file format is not supported by this parser.");
		}

	}

}

