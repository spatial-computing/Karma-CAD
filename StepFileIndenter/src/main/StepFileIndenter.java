package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cleaner.StepFileCleaner;
import indenter.TabIndenter;
import validator.Validate;
import vo.Part;

public class StepFileIndenter {
	public static String SolidWorks_Cylinder="Cylinder.step";
	public static String AUtoCad_Cylinder="Cylinder.stp";
	public static	 void main(String[] args){
	String  inpFileName = SolidWorks_Cylinder;
		Map<String, String> partsFileMap = new HashMap<String,String>();
		Map<String,Part> partMap = new HashMap<String,Part>();
		try {		
			FileReader inpFile = new FileReader(inpFileName);
			StepFileCleaner.clean(inpFile);
			
		//	BufferedReader bufferedReader = new BufferedReader(inpFile);
			String lineFull="";
			Pattern patternParent = Pattern.compile("^ #\\d+");
			Pattern pattern = Pattern.compile("#\\d+");
			
			//Single line whole file
			
			lineFull = StepFileCleaner.clean(inpFile);
			String[] lineArray = lineFull.split(";");		
		//	while((line = bufferedReader.readLine())!=null){
			for(int lines=0;lines<lineArray.length;lines++){
				String line = lineArray[lines];
				Matcher matcherParent = patternParent.matcher(line);
				
					if(matcherParent.find()){
						String partNumber = matcherParent.group();
					partNumber = partNumber.trim();
					partsFileMap.put(partNumber, line);
					Part part = null;

					if(partMap.get(partNumber)!=null){
						part = partMap.get(partNumber);
					}else{
					part = new Part();
					part.setName(partNumber);
					part.setParent(new ArrayList<Part>());
					}
					Matcher matcher = pattern.matcher(line);
					boolean skipFirstMatch = true;
					List<Part> children = new ArrayList<Part>();
						while(matcher.find()){
							if(skipFirstMatch){
								skipFirstMatch=false;
								continue;
							}
							String childName = matcher.group();
							Part child = null;
							if(partMap.get(childName)!=null){
								child = partMap.get(childName);
								child.addParent(part);

							}else{
								child = new Part();
								child.setParent(new ArrayList<Part>());
								child.addParent(part);
								child.setName(childName);
								partMap.put(childName,child);
							}
							children.add(child);
						}
						if(children.size()>0)
							part.setChildren(children);
					partMap.put(partNumber,part);
				}
			}
			
			
			//Create graph
			List<Part> exploredList = new ArrayList<Part>() ;
			LinkedList<Part> openList = new LinkedList<Part>();
			
		/*for(Map.Entry<String,Part> part: partMap.entrySet()){
			Part p = part.getValue();
			System.out.println(p);
		}*/
			File file = new File(inpFileName+".ind");
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

		for(Map.Entry<String, Part> partEntry: partMap.entrySet()){
			Part partEntryItem = (Part)partEntry.getValue();
			partEntryItem.setLevel(0);
			if(partEntryItem.getChildren()!=null &&  partEntryItem.getChildren().size()>0 && (partEntryItem.getParent()==null || partEntryItem.getParent().size()==0) && !exploredList.contains(partEntryItem)){
				openList.add(partMap.get(partEntryItem.getName()));
				while(!openList.isEmpty()){
					Part part = openList.poll();
					exploredList.add(part);
					System.out.println(TabIndenter.indent(part.getLevel())+" "+partsFileMap.get(part.getName()));
					bw.write(TabIndenter.indent(part.getLevel())+partsFileMap.get(part.getName()));
					if(part.getChildren()!=null){
						List<Part> tempList =  new ArrayList<Part>();;
						for(int i=0;i<part.getChildren().size();i++){
							
							part.getChildren().get(i).setLevel(part.getLevel()+1);
							tempList.add(part.getChildren().get(i));
							//openList.addFirst(part.getChildren().get(i));
						}
						openList.addAll(0, tempList);
					}
				}
				bw.write("\n");
			}
			System.out.println("");
			
		}
			
			bw.close();
			fw.close();
			
			Validate.validate(inpFileName);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}
	
	}
