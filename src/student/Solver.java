package student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.io.Console;

public class Solver {
	private int rows;
	private int cols;
	private ArrayList<Constraint> rowConstraint;
	private ArrayList<Constraint> colConstraint;
	private Variables vars;
	private String defaultColor = "_";

	public Solver(int rows, int cols, ArrayList<Constraint> rowConstraint, ArrayList<Constraint> colConstraint,
			Variables vars) {
		this.rows = rows;
		this.cols = cols;
		this.rowConstraint = rowConstraint;
		this.colConstraint = colConstraint;
		this.vars = vars;
	}
	
	
	
	public void solve() {
		int ind = 0;
		int nextRowC_i = 0;
		recur(ind, null, nextRowC_i);
	}
	
	
	private void recur(int ind, Constraint nextRowC, int nextRowC_i) {
		
		if (ind >= this.cols * this.rows) {
			System.out.println("-------------------------------");
			vars.printState();
			return;
		}
		
//		System.out.println("Ind is " + ind);
//		vars.printState();
//		waitForEnter();
		
		if (nextRowC == null) {
			nextRowC = this.rowConstraint.get(nextRowC_i);
		}
		
		//TODO: Jump to beginning of next row if rowCon is not this row?
		
		if (nextRowC != null && this.checkConstraints(nextRowC, ind)) {
			for (int i = 0; i < nextRowC.getNumber(); i++) {
				this.vars.setColor(nextRowC.getColor(), ind + i);
			}
			recur(ind + nextRowC.getNumber(), null, nextRowC_i + 1);
			
			// Sets the color to Null again.
			for (int i = 0; i < nextRowC.getNumber(); i++) {
				this.vars.setColor(this.defaultColor, ind + i);
			}
		}
		
		recur(ind + 1, nextRowC, nextRowC_i);
		
		
	}
	
	
	private boolean checkConstraints(Constraint nextRowC, int ind) {
		int row_i = this.getRow(ind);
		int col_i = this.getCol(ind);
		
		
		// Creates string of the col vars
		ArrayList<Variable> col_vars = this.vars.getCol(col_i);
		String colString = "";
		for (Variable colVar : col_vars) {
			if (colVar.getColor() != null) {
				colString += colVar.getColor();
			}
		}
		
		String currColor = nextRowC.getColor();
		
		ArrayList<Constraint> col_const = getColConst(col_i);
		int nbr_currColor = 0;
		for (Constraint col : col_const) {
			if (col.getColor().equals(currColor)) {
				nbr_currColor += col.getNumber();
			}
		}
		
		// Checks if the board already has enough of current color in current column
		if ((colString.length() - colString.replace(currColor, "").length()) >= nbr_currColor) {
			return false;
		}
		
		String[] out = colString.split("(?<=(.))(?!\\1)");

		if (out[out.length - 1].contains(currColor)) {
			
			int const_i = -1; // Constraint index
			for (int i = 0; i < out.length; i++) {
				if ( !out[i].contains("_") ) {
					const_i++;
				}
			}
			
			Constraint col_c = col_const.get(const_i);
			if (col_c.getNumber() <= out[out.length-1].length()) {
				return false;
			}
		}
		
		
		// Checks if the curr row is right
		if (row_i != nextRowC.getIndex()) {
			return false;
		}
			
		// Checks if the whole constraint fits in the row
		if (col_i + nextRowC.getNumber() > this.cols) {
			return false;
		}
		
		// Checks if the square to the left of nextRowC is different color
		if (col_i != 0 && this.vars.getVar(row_i, col_i - 1).getColor().equals(nextRowC.getColor()) ) {
			return false;
		}
		
		return true;
	}



	private ArrayList<Constraint> getColConst(int col_i) {
		ArrayList<Constraint> colConst = new ArrayList<Constraint>();
		for (Constraint col: this.colConstraint) {
			if (col.getIndex() == col_i) {
				colConst.add(col);
			}
		}
		return colConst;
	}

	private int getRow(int ind) {
		return ind / this.cols;
	}
	
	private int getCol(int ind) {
		return ind % this.cols;
	}



	
	
	public static void waitForEnter() {
		try {
	        System.in.read();
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}
	
	
	
	
	
	
	private boolean cspback(Variables vars, int row_i, int col_i, ArrayList<String> colors) {
//		if (assignment is complete) {
//			return assignment;
//		}
//		if assignment breaks any rules, return
		
		Iterator<String> itr = colors.iterator();
		while (itr.hasNext()) {
			String color = itr.next();
			
			if (this.checkConstraints(vars, color, row_i, col_i))
			
			vars.setColor(color, row_i, col_i);
			
			if (row_i == this.rows - 1) {
				row_i = 0;
				col_i++;
			}
			
			if (col_i == this.cols) {
				System.out.println("out of range cols");
				return false; //TODO: out of range
			}
		}
		
//		set current var to next color
//		call rec with next index square
		
//		set current var to empty color
//		call rec with next index square
		
		return false;
	}



	private boolean checkConstraints(Variables vars, String color, int row_i, int col_i) {
		
		if (vars.get)
		
		
		
		
		return true;
	}

}
