package student;

import java.util.ArrayList;

public class Variables {
	private ArrayList<Variable> vars = null;
	private int nbr_rows;
	private int nbr_cols;

	public Variables(int row, int col) {
		this.nbr_rows = row;
		this.nbr_cols = col;
		vars = new ArrayList<Variable>();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				vars.add(new Variable(i, j));
			}
		}
	}
	
	public ArrayList<Variable> getVars() {
		return this.vars;
	}

	public void setColor(String color, int row_i, int col_i) {
		Variable var = this.getVar(row_i, col_i);
		var.setColor(color);
	}
	
	public Variable getVar(int row_i, int col_i) {
		return this.vars.get(this.nbr_cols * row_i + col_i);
	}
	
	public void setColor(String color, int ind) {
		Variable var = this.getVar(ind);
		var.setColor(color);
	}
	
	public Variable getVar(int ind) {
		return this.vars.get(ind);
	}
	
	public ArrayList<Variable> getRow(int row_i) {
		ArrayList<Variable> row_vars = new ArrayList<Variable>();
		for (Variable v : vars) {
			if (v.getRow() == row_i) {
				row_vars.add(v);
			}
		}
		return row_vars;
	}
	
	public ArrayList<Variable> getCol(int col_i) {
		ArrayList<Variable> col_vars = new ArrayList<Variable>();
		for (Variable v : vars) {
			if (v.getCol() == col_i) {
				col_vars.add(v);
			}
		}
		return col_vars;
	}
	
	

	public void printState() {
		for (int i = 0; i < nbr_rows; i++) {
			for (int j = 0; j < nbr_cols; j++) {
				String color = getVar(i, j).getColor();
				if (color != null) {
					System.out.print(color);
				}
			}
			System.out.println("");
		}
	}
	
	public String getString() {
		String s = "";
		for (int i = 0; i < nbr_rows; i++) {
			for (int j = 0; j < nbr_cols; j++) {
				String color = getVar(i, j).getColor();
				if (color != null) {
					s += color;
				}
			}
			System.out.println("");
		}
		return s;
	}

}
