package ooop;

import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
//import java.time.Year;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Car {
	
//atributes
	ArrayList<String> tips = new ArrayList<>();
	ArrayList<String> CarName = new ArrayList<>();	
	ArrayList<String> Color1 = new ArrayList<>();	
	ArrayList<String> Model = new ArrayList<>();	
	ArrayList<Integer> bDay1 = new ArrayList<>();
	
	int bDay;
	double fuel;
	ImageIcon image;
	Random rand = new Random();
	
	//constructor
	public Car(
			ArrayList<String> name,ArrayList<String> breed,ArrayList<String> skincolor,
			ArrayList<Integer> BirthYear, String way, double fuel) {
		
		this.CarName = name;
		this.Model = breed;
		this.fuel = fuel;
		this.Color1 = skincolor;
		this.bDay1 = BirthYear;
		image = makeAnImage(way);
		
		
	}// Minka
	
	public ImageIcon makeAnImage(String way) {
		if(!way.endsWith(".png")) {
			way += ".png";
		}
		URL Resource = getClass().getResource("/image/" + way);
		if(Resource != null) {
			return new ImageIcon(new ImageIcon(Resource).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		} else {
			JOptionPane.showMessageDialog(null, "Can not find any images: " + way, "Warning", JOptionPane.WARNING_MESSAGE);
			return null;
		}
	}// makeAnImage
	
	void murrat() {
		String way = "car";
		image = makeAnImage(way);
		JOptionPane.showMessageDialog(null, CarName.get(0) + " says 'mur murr murrr'", "Notification", JOptionPane.INFORMATION_MESSAGE, image);
	}// murrat
	
	String readAtributes(int num) {
		return CarName.get(num) + "\nThis car is " + bDay1.get(num) + " years old " + "\nModel: " + Model.get(num) + "\nColor " + Color1.get(num);
	}// readAtributes
	
	public double Refuel(double credits, int fuel) {
		
		
		//JOptionPane.showMessageDialog(null, fuel);
		
		if(this.fuel < 100) {
			double rest = 100 - fuel;
			double cost = 10 * (rest / 5);

			credits -= cost;
			this.fuel = 100;
			String way = "refuel";
		image = makeAnImage(way);
		JOptionPane.showMessageDialog(null, credits);
			JOptionPane.showMessageDialog(null, "You have paid " + cost + " to refuel your car", "Random JOptionPane name", JOptionPane.PLAIN_MESSAGE, image);
			return credits;
		} else if(this.fuel == 100){
			String way2 = "nein";
			image = makeAnImage(way2);
			JOptionPane.showMessageDialog(null, "The car is full!", "Random JOptionPane name", JOptionPane.ERROR_MESSAGE, image);
		}
		
		return credits;
	}
	
	
	 public String close(String menu) {
		 String[] LeaveOrStay = {"Leave", "Stay"};
		 
		 String method = (String) JOptionPane.showInputDialog(null, "Leave?", "Random JOptionPane name", 
				 			JOptionPane.QUESTION_MESSAGE,null, LeaveOrStay, LeaveOrStay[1]);
		 
		switch(method) {
		
		
		case "Leave": 
			
			
			String way = "sadSpongeBob";
			image = makeAnImage(way);
			JOptionPane.showMessageDialog(null, "Bye!", "Random JOptionPane name", JOptionPane.PLAIN_MESSAGE, image);
			menu = "CloseTrue";
			break;
			
		case "Stay":
			
			
			String way2 = "colgate-dog";
			 image = makeAnImage(way2);
			 JOptionPane.showMessageDialog(null, "Thanks for staying!", "Random JOptionPane name", JOptionPane.PLAIN_MESSAGE, image);
			break;
		}
		 
		 return menu;
	}// close
	
	 void error() {
		 String way = "poor";
		 image = makeAnImage(way);
		 JOptionPane.showMessageDialog(null, "Not enough credits!", "Random JOptionPane name", JOptionPane.PLAIN_MESSAGE, image);
	 }// error
	 
	 public static int select(int num,
		        ArrayList<String> name,
		        ArrayList<String> breed,
		        ArrayList<String> skincolor,
		        ArrayList<Integer> BirthYear) {

		    ArrayList<String> SMTH = new ArrayList<>();

		    for (int i = 0; i < name.size(); i++) {
		        SMTH.add(name.get(i) + " | "
		                + breed.get(i) + " | "
		                + skincolor.get(i) + " | "
		                + BirthYear.get(i));
		    }

		    String selected = (String) JOptionPane.showInputDialog(
		            null,
		            "Which car will you select?",
		            "Select a car",
		            JOptionPane.QUESTION_MESSAGE,
		            null,
		            SMTH.toArray(),
		            SMTH.get(0)
		    );

		    if (selected == null) {
		        return -1; // User cancelled the dialog
		    }

		    num = SMTH.indexOf(selected);

		    return num;
		}
	
}// Minka