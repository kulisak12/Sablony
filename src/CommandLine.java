import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

// loads variables from the command line
public class CommandLine {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Map<String, String> vars = createVarMap(args);
		
		String output = Templates.performReplacement(input, vars);
		System.out.println(output);
	}

	// maps var names to their values
	public static Map<String, String> createVarMap(String[] args) {
		Map<String, String> vars = new HashMap<>();
		String varPrefix = "--var=";
		
		for (String arg : args) {
			if (arg.startsWith(varPrefix)) {
				// add to map
				arg = arg.substring(varPrefix.length());
				String[] parts = arg.split("=");
				vars.put(parts[0], parts[1]);
			}
		}
		return vars;
	}
}