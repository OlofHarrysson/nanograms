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
	private int nbrHash = 0; //TODO REMOVE

	public Solver(int rows, int cols, ArrayList<Constraint> rowConstraint, ArrayList<Constraint> colConstraint,
			Variables vars) {
		this.rows = rows;
		this.cols = cols;
		this.rowConstraint = rowConstraint;
		this.colConstraint = colConstraint;
		this.vars = vars;
		
		for (Constraint c : rowConstraint) {
			this.nbrHash += c.getNumber();
		}
		
	}
	
	
	public void solve() {
		int ind = 0;
		int nextRowC_i = 0;
		recur(ind, null, nextRowC_i);
	}
	
	
	private void recur(int ind, Constraint nextRowC, int nextRowC_i) {
		
		
	
//		if (
//				vars.getVar(3).getColor() != null && vars.getVar(3).getColor().equals("#") &&
//				vars.getVar(4).getColor() != null && vars.getVar(4).getColor().equals("#") &&
//				vars.getVar(10).getColor() != null && vars.getVar(10).getColor().equals("#") &&
//				vars.getVar(12).getColor() != null && vars.getVar(12).getColor().equals("#") &&
//				vars.getVar(18).getColor() != null && vars.getVar(18).getColor().equals("#") &&
//				vars.getVar(20).getColor() != null && vars.getVar(20).getColor().equals("#") &&
//				vars.getVar(26).getColor() != null && vars.getVar(26).getColor().equals("#") &&
//				vars.getVar(27).getColor() != null && vars.getVar(27).getColor().equals("#") &&
//				vars.getVar(28).getColor() != null && vars.getVar(28).getColor().equals("#") &&
//				vars.getVar(29).getColor() != null && vars.getVar(29).getColor().equals("#") &&
//				vars.getVar(31).getColor() != null && vars.getVar(31).getColor().equals("#")
//				
//				) {
//			System.out.println("-------------------------------");
//			vars.printState();
//			
//		}
		
		
		if (ind >= this.cols * this.rows) {
//			System.out.println("-------------------------------");
//			vars.printState();
			
			
			
			return;
		}
		
//		if (nextRowC_i > 11) {
//			System.out.println("Ind is " + ind);
//			vars.printState();
//		}
		
//		System.out.println("Ind is " + ind);
//		vars.printState();
//		waitForEnter();
//		
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
				this.vars.setColor(null, ind + i);
			}
		}
		
		this.vars.setColor(this.defaultColor, ind);
		recur(ind + 1, nextRowC, nextRowC_i);
		this.vars.setColor(null, ind);
		
		
	}
	
	
	private boolean checkConstraints(Constraint nextRowC, int ind) {
		int row_i = this.getRow(ind);
		int col_i = this.getCol(ind);
		int col_ind = col_i;
		
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
		
		
		
		for (int j = 0; j < nextRowC.getNumber(); j++) {
			
			// Creates string of the col vars
//			ArrayList<Variable> col_vars = this.vars.getCol(col_ind);
//			String colString = "";
//			for (Variable colVar : col_vars) {
//				if (colVar.getColor() != null) {
//					colString += colVar.getColor();
//				}
//			}
//			
//			String currColor = nextRowC.getColor();
			
//			ArrayList<Constraint> col_const = getColConst(col_ind);
//			int nbr_currColor = 0;
//			for (Constraint col : col_const) {
//				if (col.getColor().equals(currColor)) {
//					nbr_currColor += col.getNumber();
//				}
//			}
//			
//			// Checks if the board already has enough of current color in current column
//			if ((colString.length() - colString.replace(currColor, "").length()) >= nbr_currColor) {
//				return false;
//			}
//			
//			String[] out = colString.split("(?<=(.))(?!\\1)");
//			
//			if (out[out.length - 1].contains(currColor)) {
//				
//				int const_i = -1; // Constraint index
//				for (int i = 0; i < out.length; i++) {
//					if ( !out[i].contains("_") ) {
//						const_i++;
//					}
//				}
//				
//				this.vars.printState();
//				System.out.println(col_const);
//				Constraint col_c = col_const.get(const_i);
//				if (col_c.getNumber() <= out[out.length-1].length()) {
//					return false;
//				}
//			}
			
			
			
			
			
			
			
			
			
			// Creates string of the col vars
			ArrayList<Variable> col_vars = this.vars.getCol(col_ind);
			String colString = "";
			for (Variable colVar : col_vars) {
				if (colVar.getColor() != null) {
					colString += colVar.getColor();
				}
			}
			
			
			
			// Check number of color groups
			String[] out = colString.split("(?<=(.))(?!\\1)");
			int nbrColorGroups = 0; 
			for (String color : out) {
				if ( !color.contains("_") ) {
					nbrColorGroups++;
				}
			}
			
			// Check if current colorgroup can add another symbol. Has to check before other TODO
			ArrayList<Constraint> col_const = getColConst(col_ind);
			String currColor = nextRowC.getColor();
			if (out[out.length - 1].contains(currColor)) {
				vars.printState();
				Constraint col_c = col_const.get(nbrColorGroups - 1);
				
				if (col_c.getNumber() <= out[out.length-1].length()) {
					return false;
				}
			}
			
			
			if (
					vars.getVar(3).getColor() != null && vars.getVar(3).getColor().equals("#") &&
					vars.getVar(4).getColor() != null && vars.getVar(4).getColor().equals("#") &&
					vars.getVar(10).getColor() != null && vars.getVar(10).getColor().equals("#") &&
					vars.getVar(12).getColor() != null && vars.getVar(12).getColor().equals("#") &&
					vars.getVar(18).getColor() != null && vars.getVar(18).getColor().equals("#") &&
					vars.getVar(20).getColor() != null && vars.getVar(20).getColor().equals("#") &&
					vars.getVar(26).getColor() != null && vars.getVar(26).getColor().equals("#") &&
					vars.getVar(27).getColor() != null && vars.getVar(27).getColor().equals("#") &&
					vars.getVar(28).getColor() != null && vars.getVar(28).getColor().equals("#") &&
					vars.getVar(29).getColor() != null && vars.getVar(29).getColor().equals("#") &&
					vars.getVar(31).getColor() != null && vars.getVar(31).getColor().equals("#") &&
					ind == 35
					
					) {
				System.out.println("-------------------------------");
				vars.printState();
				
			}
			
			
			int col_const_nbr = 0;
			for (Constraint c : col_const) {
				col_const_nbr += c.getNumber();
			}
			// TODO: After other check. It can still fit in the last one
			if (nbrColorGroups >= col_const_nbr) {
				return false;
			}
			
			
			
			
			
			if (
					vars.getVar(3).getColor() != null && vars.getVar(3).getColor().equals("#") &&
					vars.getVar(4).getColor() != null && vars.getVar(4).getColor().equals("#") &&
					vars.getVar(10).getColor() != null && vars.getVar(10).getColor().equals("#") &&
					vars.getVar(12).getColor() != null && vars.getVar(12).getColor().equals("#") &&
					vars.getVar(18).getColor() != null && vars.getVar(18).getColor().equals("#") &&
					vars.getVar(20).getColor() != null && vars.getVar(20).getColor().equals("#") &&
					vars.getVar(26).getColor() != null && vars.getVar(26).getColor().equals("#") &&
					vars.getVar(27).getColor() != null && vars.getVar(27).getColor().equals("#") &&
					vars.getVar(28).getColor() != null && vars.getVar(28).getColor().equals("#") &&
					vars.getVar(29).getColor() != null && vars.getVar(29).getColor().equals("#") &&
					vars.getVar(31).getColor() != null && vars.getVar(31).getColor().equals("#") &&
					ind == 35
					
					) {
				System.out.println("-------------------------------");
				vars.printState();
				
			}
			
			
			col_ind++;
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
	
	
	
	
	
	
	

}
