package ooop;

import java.awt.Image;
import java.net.URL;
//import java.time.Year;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Car {
	
//atributes
	String name, breed, skincolor;
	double age, fuel;
	
	ImageIcon image;
	Random rand = new Random();
	
	//constructor
	public Car(
			String name,String breed,String skincolor,
			int BirthYear, String way, double fuel) {
		
		this.name = name;
		this.breed = breed;
		this.fuel = fuel;
		this.skincolor = skincolor;
		
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
		JOptionPane.showMessageDialog(null, name + " says 'mur murr murrr'", "Notification", JOptionPane.INFORMATION_MESSAGE, image);
	}// murrat
	
	String readAtributes() {
		return name + "\nThis car is " + age + " years old " + "\nModel: " + breed + "\nColor " + skincolor;
	}// readAtributes
	
	public  int Refuel(double credits, int fuel) {
		
		
		//JOptionPane.showMessageDialog(null, fuel);
		
		if(this.fuel < 100) {
			double rest = 100 - fuel;
			double cost = 10 * (rest / 5);

			credits -= cost;
			this.fuel = 100;
			String way = "refuel";
		image = makeAnImage(way);
			JOptionPane.showMessageDialog(null, "You have paid " + cost + " to refuel your car", "Random JOptionPane name", JOptionPane.PLAIN_MESSAGE, image);
			//return fuel;
		} else if(this.fuel == 100){
			String way2 = "nein";
			image = makeAnImage(way2);
			JOptionPane.showMessageDialog(null, "The car is full!", "Random JOptionPane name", JOptionPane.ERROR_MESSAGE, image);
		}
		
		return fuel;
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
	
}// Minka