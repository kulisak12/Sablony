import java.util.HashMap;
import java.util.Map;

// maps var names to their values
public class Variables {
	Map<String, String> vars = new HashMap<>();
	
	public Variables(String[] args) {
		String varPrefix = "--var=";
		for (String arg : args) {
			if (arg.startsWith(varPrefix)) {
				// add to map
				arg = arg.substring(varPrefix.length());
				String[] parts = arg.split("=");
				vars.put(parts[0], parts[1]);
			}
		}
	}
}
