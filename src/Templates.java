import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Templates {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Variables vars = new Variables(args);
		
		String output = performReplacement(input, vars);
		System.out.println(output);
	}
	
	// replace vars with values
	public static String performReplacement(Scanner input, Variables vars) {
		StringBuilder result = new StringBuilder();
		Pattern varDelimiter = Pattern.compile("\\{\\{ [\\w ]+ \\}\\}"); // {{ var }}
		
		while (input.hasNextLine()) {
			String line = input.nextLine();
			Matcher matcher = varDelimiter.matcher(line);
			
			// replace next var
			while (matcher.find()) {
				String varName = extractVarName(matcher.group(0));
				String varValue = vars.vars.get(varName);
				
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
