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
	private ArrayList<String> solutions;
	
	public Solver(int rows, int cols, ArrayList<Constraint> rowConstraint, ArrayList<Constraint> colConstraint,
			Variables vars) {
		this.rows = rows;
		this.cols = cols;
		this.rowConstraint = rowConstraint;
		this.colConstraint = colConstraint;
		this.vars = vars;
		this.solutions = new ArrayList<String>();
	}
	
	
	public void solve() {
		int ind = 0;
		int nextRowC_i = 0;
		recur(ind, null, nextRowC_i);
	}
	
	
	private void recur(int ind, Constraint nextRowC, int nextRowC_i) {
		
		if (ind >= this.cols * this.rows) {
//			System.out.println("-------------------------------");
//			vars.printState();
			
			if (checkRowsCols()) {
				this.solutions.add(vars.getString());
				vars.printState();
			}
			return;
		}

		this.vars.setColor("#", ind);
		recur(ind + 1, null, nextRowC_i + 1);
		this.vars.setColor("_", ind);
		recur(ind + 1, nextRowC, nextRowC_i);
	}
	
	
	private boolean checkRowsCols() {
		// Checks rows
		for (int i = 0; i < this.rows; i++) {
			ArrayList<Constraint> rC = getRowConst(i);
			ArrayList<Variable> rowV = vars.getRow(i);
			
			String rowString = "";
			for (Variable v: rowV) {
				rowString += v.getColor();
			}
			
			String regex = "[_]*";
			for (Constraint c : rC) {
				String color = c.getColor();
				int n = c.getNumber();
				regex += String.format("[%s]{%d}[^%s]+", color, n, color);
			}
			
			regex = regex.substring(0, regex.length() - 5);
			regex += "[_]*";
			
			if (rowString.matches(regex) == false) {
				return false;
			}
			
		}
		
		// Checks cols
		for (int i = 0; i < this.cols; i++) {
			ArrayList<Constraint> colC = getColConst(i);
			ArrayList<Variable> colV = vars.getCol(i);
			
			String colString = "";
			for (Variable v: colV) {
				colString += v.getColor();
			}
			
			String regex = "[_]*";
			for (Constraint c : colC) {
				String color = c.getColor();
				int n = c.getNumber();
				regex += String.format("[%s]{%d}[^%s]+", color, n, color);
			}
			
			regex = regex.substring(0, regex.length() - 5);
			regex += "[_]*";
			
			if (colString.matches(regex) == false) {
				return false;
			}
			
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
	
	private ArrayList<Constraint> getRowConst(int row_i) {
		ArrayList<Constraint> rowConst = new ArrayList<Constraint>();
		for (Constraint row: this.rowConstraint) {
			if (row.getIndex() == row_i) {
				rowConst.add(row);
			}
		}
		return rowConst;
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
			
			
			
			
			
			int col_const_nbr = 0;
			for (Constraint c : col_const) {
				col_const_nbr += c.getNumber();
			}
			// TODO: After other check. It can still fit in the last one
			if (nbrColorGroups >= col_const_nbr) {
				return false;
			}
			
	
			
			
			col_ind++;
		}
		
		return true;
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
