import java.io.File;
import java.io.FileNotFoundException;


public class ReaderFactory{

	public static Data createReader(File file) throws FileNotFoundException{

		if (file.getName().endsWith(".hst")){
			return new HSTReader(file).readIn();
		} else if (file.getName().endsWith(".sts")){
			return new STSReader(file).readIn();
		} else if (file.getName().endsWith(".sp")){
			return new SuperReader(file).getData();
		} else {
			return new UnknownData(file.getName());
		}

	}

}

