package student;

public class Variable {
	private int row;
	private int col;
	private String color = null;

	public Variable(int row, int col) { // TODO: value?
		this.row = row;
		this.col = col;
		this.color = null;
	}
	
	public String toString() { 
	    return "Row is " + this.row + " Col is " + this.col + " Color is " + this.color;
	}

	public void setColor(String color) {
		this.color = color;
	} 
	
	public String getColor() {
		return this.color;
	}

}
