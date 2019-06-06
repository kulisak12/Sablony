
public class Settings {
	public String csvFilename = null;
	public String templateFilename;
	public String outFilename;
	
	public Settings(String[] args) {
		for (String arg : args) {
			String value = arg.split("=")[1];
			if (arg.startsWith("--csv=")) {
				csvFilename = value;
			}
			else if (arg.startsWith("--template=")) {
				templateFilename = value;
			}
			else if (arg.startsWith("--out=")) {
				outFilename = value;
			}
		}
	}
}
