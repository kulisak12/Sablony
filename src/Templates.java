import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Templates {
	
	// replace vars with values
	public static String performReplacement(Scanner input, Map<String, String> vars) {
		StringBuilder result = new StringBuilder();
		Pattern varDelimiter = Pattern.compile("\\{\\{ [\\w ]+ \\}\\}"); // {{ var }}
		
		while (input.hasNextLine()) {
			String line = input.nextLine();
			Matcher matcher = varDelimiter.matcher(line);
			
			// replace next var
			while (matcher.find()) {
				String varName = extractVarName(matcher.group(0));
				String varValue = vars.get(varName);
				
				line = matcher.replaceFirst(varValue);
				matcher = varDelimiter.matcher(line); // repeat
			}
			
			result.append(line);
			result.append("\n");
		}
		
		result.setLength(result.length() - 1);; // removes last newline
		return result.toString();
	}
	
	// trims braces around var
	private static String extractVarName(String var) {
		return var.substring(3, var.length() - 3);
	}

}
