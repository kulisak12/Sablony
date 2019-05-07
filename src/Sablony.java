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
		String result = new String();
		Pattern varDelimiter = Pattern.compile("\\{\\{ \\w+ \\}\\}");
		
		while (input.hasNextLine()) {
			String line = input.nextLine();
			Matcher matcher = varDelimiter.matcher(line);
			
			while (matcher.find()) {
				String varName = matcher.group(0);
				varName = extractVarName(varName);
				String varValue = vars.vars.get(varName);
				
				line = matcher.replaceFirst(varValue);
				matcher = varDelimiter.matcher(line);
			}
			result += line + "\n";
		}
		
		return result;
	}
	
	private static String extractVarName(String var) {
		return var.substring(3, var.length() - 3);
	}

}
