package vo;

import java.util.List;

public class Part {

	String name;
	List<Part> children;
	
	boolean parsed;
	int level;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Part> getChildren() {
		return children;
	}

	public void setChildren(List<Part> children) {
		this.children = children;
	}

	public boolean isParsed() {
		return parsed;
	}

	public void setParsed(boolean parsed) {
		this.parsed = parsed;
	}
	
	
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public String toString(){
		return this.name +" -> "+getChildren();
	}
	
	
	@Override
	public boolean equals(Object o){
		if(Part.class.isInstance(o)){
			Part otherPart = (Part) o;
			if(this.name.equals(otherPart.getName())){
				return true;
			}
		}
		return false;
	}
	
}
