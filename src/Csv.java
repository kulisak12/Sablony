import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// loads variables from the command line
public class Csv {
	public static void main(String[] args) throws IOException {
		Settings settings = new Settings(args);
		Scanner varCsv; // a csv file holding var values
		if (settings.csvFilename == null) {
			varCsv = new Scanner(System.in);
		}
		else {
			varCsv = new Scanner(new File(settings.csvFilename));
		}
		
		String[] varNames = getNextLineTokens(varCsv);
		Map<String, String> vars = new HashMap<>();
		
		// create all template variants
		int templateNumber = 1;
		while (varCsv.hasNextLine()) {
			Scanner template = new Scanner(new File(settings.templateFilename));
			String[] varValues = getNextLineTokens(varCsv);
			for (int i = 0; i < varValues.length; i++) {
				vars.put(varNames[i], varValues[i]);
			}
			
			String filename = String.format(settings.outFilename, templateNumber);
			templateNumber++;
			FileWriter outputFile = new FileWriter(new File(filename));
			outputFile.write(Templates.performReplacement(template, vars));
			outputFile.close();
			template.close();
		}
		
		varCsv.close();
	}

	// read next line from Scanner and split it by commas
	public static String[] getNextLineTokens(Scanner csv) {
		String line = csv.nextLine();
		return line.split(",");
	}
}