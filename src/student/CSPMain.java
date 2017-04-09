package student;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class CSPMain {

	public static void main(String[] args) {
		Scanner in = null;
		try {
			in = new Scanner(new File("data/input.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
			System.exit(1);
		}
		in.useDelimiter(",|\n");
		
		int rows = in.nextInt();
		int cols = in.nextInt();
		in.nextLine(); // Remove newline character
		
		ArrayList<Constraint> rowConstraint = new ArrayList<Constraint>();
		ArrayList<Constraint> colConstraint = new ArrayList<Constraint>();
		
		
		for (int i = 0; i < rows; i++) {
			String line = in.nextLine();
			String[] constData = line.split(",");
			for (int j = 0; j < constData.length; j += 2) {
				Constraint con = new Constraint(constData[j], Integer.parseInt((constData[j+1])), i);
				rowConstraint.add(con);
			}
		}
		
		for (int i = 0; i < cols; i++) {
			String line = in.nextLine();
			String[] constData = line.split(",");
			for (int j = 0; j < constData.length; j += 2) {
				Constraint con = new Constraint(constData[j], Integer.parseInt((constData[j+1])), i);
				colConstraint.add(con);
			}
		}

//		System.out.println("Row constraintes are...");
//		for (Constraint con: rowConstraint) {
//			System.out.println(con.toString());
//		}
//		
//		System.out.println("Col constraintes are...");
//		for (Constraint con: colConstraint) {
//			System.out.println(con.toString());
//		}
		
		Variables vars = new Variables(rows, cols);
//		for (Variable var : vars.getVars()) {
//			System.out.println(var);
//		}
		
		Solver solver = new Solver(rows, cols, rowConstraint, colConstraint, vars);
		solver.solve();
	}

}
