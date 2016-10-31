package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import indenter.TabIndenter;
import vo.Part;

public class StepFileIndenter {
	public static String SolidWorks_Cylinder="Cylinder.step";
	public static	 void main(String[] args){
	
		Map<String, String> partsFileMap = new HashMap<String,String>();
		Map<String,Part> partMap = new HashMap<String,Part>();
		try {		
			FileReader inpFile = new FileReader(SolidWorks_Cylinder);
			BufferedReader bufferedReader = new BufferedReader(inpFile);
			String line="";
			Pattern patternParent = Pattern.compile("^#\\d+");
			Pattern pattern = Pattern.compile("#\\d+");
			
			while((line = bufferedReader.readLine())!=null){
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
							}else{
								child = new Part();
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
			bufferedReader.close();
			
			//Create graph
			
			
			List<Part> exploredList = new ArrayList<Part>() ;
			LinkedList<Part> openList = new LinkedList<Part>();
			
		/*for(Map.Entry<String,Part> part: partMap.entrySet()){
			Part p = part.getValue();
			System.out.println(p);
		}*/
		
		for(Map.Entry<String, Part> partEntry: partMap.entrySet()){
			Part partEntryItem = (Part)partEntry.getValue();
			partEntryItem.setLevel(0);
			if(partEntryItem.getChildren()!=null &&  partEntryItem.getChildren().size()>0){
				openList.add(partMap.get(partEntryItem.getName()));
				while(!openList.isEmpty()){
					Part part = openList.poll();
					exploredList.add(part);
					System.out.println(TabIndenter.indent(part.getLevel())+" "+partsFileMap.get(part.getName()));
					if(part.getChildren()!=null){
						for(int i=0;i<part.getChildren().size();i++){
							part.getChildren().get(i).setLevel(part.getLevel()+1);
							openList.addFirst(part.getChildren().get(i));
						}
					}
				}
			}
			System.out.println("");
		}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}
	
	}
