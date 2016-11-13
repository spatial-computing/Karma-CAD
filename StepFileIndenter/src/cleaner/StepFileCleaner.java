package cleaner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StepFileCleaner {

	public static String clean(String inpFileName) throws IOException{
		FileReader inpFile = new FileReader(inpFileName);
		BufferedReader bufferedReader = new BufferedReader(inpFile);
		String lineFull=null;
		String line = "";
		String formattedContent = "";
		Pattern patternParent = Pattern.compile("^#\\d+=.*;$",Pattern.MULTILINE+Pattern.DOTALL);
		int c;
		while(-1 !=(c = bufferedReader.read())){

			if(c=='#'){
				formattedContent+=(char)c;
				while(';' !=(c = bufferedReader.read())){
					//c = (char)bufferedReader.read();
					if('\n'==(char)c || ('\r'==(char)c)){
						continue ;
					}
					formattedContent+=(char)c;
				}
				formattedContent+=(char)c;
			}
		}
		
		bufferedReader.close();
		return formattedContent;

	}
	
}
