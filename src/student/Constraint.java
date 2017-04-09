package student;

public class Constraint {
	
	private String color;
	private int number;
	private int index;
	// TODO: Possibly order attribute to keep the order in the input file

	public Constraint(String color, int number, int index) { // TODO: Change class name? InputConst?
		this.color = color;
		this.number = number;
		this.index = index;
	}
	
	public String toString() { 
	    return "Index is " + index + " Color is " + this.color + " Number is " + this.number;
	} 
	
	public String getColor() {
		return this.color;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public int getIndex() {
		return this.index;
	}

}
