import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class driver {
public static String AutoCAD_Cylinder_Ind="Cylinder.stp.ind";
public static String AutoCAD_Cylinder="Cylinder.stp";
public static String AutoCAD_Circle_Ind="Circle.stp.ind";
public static String AutoCAD_Circle="Circle.stp";
public static String FreeCAD_Cylinder_Ind="Cylinder_Circle_Style_Freecad.step.ind";
public static String FreeCAD_Cylinder="Cylinder_Circle_Style_Freecad.step";
public static String FreeCAD_Circle_Ind="FreeCAD_Circle.step.ind";
public static String FreeCAD_Circle="FreeCAD_Circle.step";
public static String SolidWorks_Cylinder_Ind="Cylinder.step.ind";
public static String SolidWorks_Cylinder="Cylinder.step";
public static String SolidWorks_AutoCAD_Cylinder_Ind="Cylinder_SolidWork_AutoCAD.stp.ind";
public static String SolidWorks_AutoCAD_Cylinder="Cylinder_SolidWork_AutoCAD.stp";
public static String SolidWorks_AutoCAD_SolidWorks_Cylinder_Ind="Cylinder_SolidWork_AutoCAD_SolidWork.STEP.ind";
public static String SolidWorks_AutoCAD_SolidWorks_Cylinder="Cylinder_SolidWork_AutoCAD_SolidWork.STEP";


	public static void main(String args[]) throws IOException{

		HashSet<String> parts2 = new HashSet<String>();

			FileReader inpFile2 = new FileReader(SolidWorks_Cylinder_Ind);
			BufferedReader bufferedReader2 = new BufferedReader(inpFile2);
			String line2;
			while((line2 = bufferedReader2.readLine())!=null){
				if(line2.contains("=")){
					String part = line2.substring(0, line2.indexOf("="));
					part = part.trim();
					parts2.add(part);
			
					}
			}
		System.out.println(parts2.size());
	 	FileReader inpFile = new FileReader(SolidWorks_Cylinder);
	 	BufferedReader bufferedReader = new BufferedReader(inpFile);
	 	HashSet<String> parts = new HashSet<String>();
	 	String line;
	 	while((line = bufferedReader.readLine())!=null){
	 		if(line.contains("=")){
	 			String part = line.substring(0, line.indexOf("="));
	 			part = part.trim();
	 			parts.add(part);
	 			if(!parts2.contains(part)){
	 				System.out.println(part);
	 			}
	 		}
	 	}

System.out.println(parts.size());
	bufferedReader.close();
	bufferedReader2.close();
}
	
/*public static String decrypt(String encrypted_message) {
    final  String sig = "The quick brown fox jumps over the lazy dog";
    int positionOfSig=encrypted_message.length()-sig.length();
        String key="";
        int sigPointer=0;
    //Possible key
    for(int i=positionOfSig;i<encrypted_message.length();i++){
       
        char c = encrypted_message.charAt(i);
        if((c>='a' && c<='z')||(c>='A' &&c<='Z')){
        	System.out.println(key);
        	key +=rotate(c,sig.charAt(sigPointer))+"";
        }
        sigPointer++;
    }
    
   int lastIndex = maxPrefSuf(key);
   int keyIndex =0;
   key = key.substring(0, lastIndex);
   System.out.println("Key "+key);
   String decryptedMessage="";
    for(int i=0;i<encrypted_message.length();i++){
    	 char c = encrypted_message.charAt(i);
    
    	  if((c>='a' && c<='z')||(c>='A' &&c<='Z')){
    		  decryptedMessage +=rotateRight(encrypted_message.charAt(i),Integer.valueOf(key.charAt(keyIndex)+""));
    		  keyIndex+=1;
    		  if(keyIndex==key.length()){
    	    		keyIndex=0;
    	    	}
    	  	}
    	
    }
    return decryptedMessage;
}


static int maxPrefSuf(String s){
    int n = s.length();

    for(int i=n-1; i>0; i--)
    {
        if( s.indexOf(s.substring(i)) == 0 ){
              int x = maxPrefSuf( s.substring(0,i) );
              return x == -1? i : x;
        }
    }
  
  	return -1;
}

public static char rotateRight (char inp, int key){

for(int i=0;i<key;i++){
   if(inp=='a'){
       inp='z';
   }else
       if(inp=='A'){
       inp='Z';
   }else{
   inp--;
   }
}
   return inp;
}

public static int rotate (char inp, char expected){
int iteration =0;
while(inp!=expected){
   if(inp=='a'){
       inp='z';
   }else
       if(inp=='A'){
       inp='Z';
   }else{
   inp--;
   }
   iteration++;
}
return iteration;
}
*/
}
