import java.io.File;


public class ReaderFactory{

	public static Reader createReader(File file) throws Exception{

		if (file.getName().endsWith(".hst")){
			return new HSTReader(file);
		} else if (file.getName().endsWith(".sts")){
			return new NFCReader(file);
		} else {
			throw new Exception("A reader does not exist for this file type.");
		}

	}

}

