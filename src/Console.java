
/**
 * Primary way to print to console. This takes care of labeling and formating.
 * @author Tyler
 *
 */
public class Console{

	public static void print(String ... string){

		for(String s : string){
			System.out.println("[SuperParser] " + s);
		}

	}

	public static void warn(String ... string){

		for(String s : string){
			System.out.println("[SuperParser][warn] " + s);
		}

	}

	public static void log(String ... string){

		for(String s : string){
			System.out.println("[SuperParser][log] " + s);
		}

	}

	public static void error(String ... string){

		for(String s : string){
			System.out.println("[SuperParser][ERROR] " + s);
		}

	}

}
