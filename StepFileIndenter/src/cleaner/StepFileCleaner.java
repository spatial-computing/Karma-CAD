package cleaner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class StepFileCleaner {

	public static String clean(String inpFileName) throws IOException{
		FileReader inpFile = new FileReader(inpFileName);
		BufferedReader bufferedReader = new BufferedReader(inpFile);
		String lineFull=null;
		String line = "";
		Pattern patternParent = Pattern.compile("^ #\\d+");

		while((line = bufferedReader.readLine())!=null){
			lineFull+=line;
		}
		bufferedReader.close();
		return lineFull;

	}
	
}
