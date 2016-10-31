package indenter;

public class TabIndenter {

	public static String indent(int level){
		String tab="";
		for(int i=0;i<level;i++){
			tab+="\t";
		}
		return tab;
	}
}
