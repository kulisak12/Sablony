import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sablony {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Variables vars = new Variables(args);
		
		String output = performReplacement(input, vars);
		System.out.println(output);
	}
	
	public static String performReplacement(Scanner input, Variables vars) {
		StringBuilder result = new StringBuilder();
		Pattern varDelimiter = Pattern.compile("\\{\\{ \\w+ \\}\\}");
		
		while (input.hasNextLine()) {
			String line = input.nextLine();
			Matcher matcher = varDelimiter.matcher(line);
			
			while (matcher.find()) {
				String varName = extractVarName(matcher.group(0));
				String varValue = vars.vars.get(varName);
				
				line = matcher.replaceFirst(varValue);
				matcher = varDelimiter.matcher(line);
			}
			
			result.append(line);
			result.append("\n");
		}
		
		return result.toString();
	}
	
	private static String extractVarName(String var) {
		return var.substring(3, var.length() - 3);
	}

}
