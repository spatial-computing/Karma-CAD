package parser;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.antlr.runtime.tree.DOTTreeGenerator;
import org.antlr.stringtemplate.StringTemplate;

import gov.nist.msid.ontostep.instance.Part21Lexer;
import gov.nist.msid.ontostep.instance.Part21Parser;

public class StepFileReader {

	static String stepFile1="Circle.stp";
	static String stepFile2="FreeCAD_Circle.step";
	
	public static void main(String[] args) throws IOException{
		     Part21Lexer p21lexer = new Part21Lexer();
		
		     try {
		    	  p21lexer.setCharStream(new ANTLRFileStream(stepFile1));
				 
		    	  CommonTokenStream p21tokens = new CommonTokenStream(p21lexer);											
				  
		    	  Part21Parser p21parser = new Part21Parser(p21tokens);
						
				  CommonTree p21tree = (CommonTree)p21parser.exchange_file().getTree();
						
				  CommonTreeNodeStream p21nodes = new CommonTreeNodeStream(p21tree);
						
				  DOTTreeGenerator generator = new DOTTreeGenerator();
				  StringTemplate st = generator.toDOT(p21tree);

				 //Write Dot Files for Graphviz
				  FileWriter file = new FileWriter("Circle_Graph_Inv");
				  file.write(st.toString());
				  file.close();
		
				  p21nodes.setTokenStream(p21tokens);
				  
				  //TODO: Nodes EXtraction and Comparison
				  Queue<CommonTree> simpleRecords = new ArrayDeque<CommonTree>();
				
				  CommonTree o = (CommonTree)p21nodes.nextElement();
				  while(!p21nodes.isEOF(o) ){
					if(Token.UP!=o.getType() && Token.DOWN!=o.getType()){
						if(o.getText().equalsIgnoreCase("Simple_Record")){
							simpleRecords.offer(o);
						}
					}
					o =(CommonTree)p21nodes.nextElement();
				}
				
				for(CommonTree simple_record: simpleRecords){
					System.out.println(simple_record.getChildren());
					
				}
				
				System.out.println("*****Parsing File2*****");
				Part21Lexer p21lexer2 = new Part21Lexer();
				p21lexer2.setCharStream(new ANTLRFileStream(stepFile2));
				CommonTokenStream p21tokens2 = new CommonTokenStream(p21lexer);											  
				Part21Parser p21parser2 = new Part21Parser(p21tokens2);
				CommonTree p21tree2 = (CommonTree)p21parser2.exchange_file().getTree();
				CommonTreeNodeStream p21nodes2 = new CommonTreeNodeStream(p21tree2);
				DOTTreeGenerator generator2 = new DOTTreeGenerator();
				StringTemplate st2 = generator2.toDOT(p21tree2);
				  file = new FileWriter("Circle_Graph_FreeCAD");
				  file.write(st2.toString());
				  file.close();
				  p21nodes2.setTokenStream(p21tokens2);

					/*	o =(CommonTree) p21nodes2.nextElement();
						while(!p21nodes.isEOF(o)){
							System.out.println(o);
							o =(CommonTree)p21nodes2.nextElement();
						}*/
	
					//	  System.out.println(p21nodes.toTokenTypeString());
						
						
	//					System.out.println(p21tree.getChildren());
		/*     */     }
		/*     */     catch (RecognitionException re)
		/*     */     {
		/* 129 */       System.out.println("The file   could not be parsed"+ re);
		/*     */       
		/* 131 */       return;
		/*     */     }
		/*     */     catch (IOException ioe)
		/*     */     {
		/* 135 */      System.out.println("The file   could not be parsed"+ ioe);
		/*     */       
		/*     */ 
		/* 138 */       return;
		/*     */     }
		/*     */     catch (Exception e)
		/*     */     {
		/* 142 */      System.out.println("The file   could not be parsed"+ e);
		/* 143 */       return;
		/*     */     }
		/*     */     
		/*     */      
		/* 160 */    
	}
	
}
