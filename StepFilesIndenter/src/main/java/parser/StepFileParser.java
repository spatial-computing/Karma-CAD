package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StepFileParser {
	public static Map<String,String> parse(String fileName) throws IOException{
		
		FileReader inpFile = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(inpFile);
		Map<String,String> parts = new HashMap<String,String>();
		String line="";
		Pattern patternParent = Pattern.compile("^#\\d+");
		
		Pattern pattern = Pattern.compile("#\\d+");
		
		while((line = bufferedReader.readLine())!=null){
			Matcher matcherParent = patternParent.matcher(line);
			
			if(matcherParent.find()){
				Matcher matcher = pattern.matcher(line);
				while(matcher.find(1)){
					System.out.println(matcher.group());
				}
				String partNumber = line.substring(0, line.indexOf("="));
				partNumber = partNumber.trim();
				parts.put(partNumber, line);
			}
		}
		bufferedReader.close();
		return parts;
	}
}
