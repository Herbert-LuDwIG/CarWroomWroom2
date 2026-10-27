package ooop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class DarbsArFailu {

	public static void save(Car car, String fName, int num) {
		
		try {
			FileWriter fw = new FileWriter(fName, true);
			PrintWriter pw = new PrintWriter(fw);	
			pw.println(car.readAtributes(num));
			pw.println("+".repeat(20));
			pw.close();
			JOptionPane.showMessageDialog(null, "Car has been saved in " + fName);
			
		} catch(IOException e) {
			JOptionPane.showMessageDialog(null, "Error has accured while writing in file", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}// save
	
	public static void read(String fName) {
		String line, str = "";
		
		try {
			FileReader fr = new FileReader(fName);
			BufferedReader br = new BufferedReader(fr);
			while((line = br.readLine()) != null) {
				str += line + "\n";
				
			}
			br.close();
				JOptionPane.showMessageDialog(null, str, "File content", JOptionPane.INFORMATION_MESSAGE);
		} catch(IOException e) {
			JOptionPane.showMessageDialog(null, "There's no any car in the garage", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}// read
	
}