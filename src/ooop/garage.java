package ooop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class  garage{
	static ArrayList<String> tips = new ArrayList<>();
	public static void main(String[] args) {
		
		fileDelete(".");
		init();	
		double credits = 1500;
		int fuel = 90;
		String menu, fileName = "cars.txt";
		Car car = null;
		String[] actionList = {"Buy a car (1000 credits)", "Interact with a car","Check for cash", "Save", "Check saved car", "=== CASINO ===",  "Close"};
		
		
		do {
		menu = (String) JOptionPane.showInputDialog(null, "Choose an action", "Random JOptionPane window name", JOptionPane.QUESTION_MESSAGE, null, actionList, actionList[0]);	
		
		if(menu == null)
		    menu = "Close";
		 
		 switch(menu){
		
		 case "Buy a car (1000 credits)": 
		  if(credits >= 1000) {
		String carName, model, Color, way;
		 int bDay;
		 boolean strap;
		 
		 //String
		 carName = virknesParbaude("Car's brand (Audi, Toyota, BMW)", "Audi"); 
		 model = virknesParbaude("Car's model (A3, X5)", "A3"); 
		 Color = virknesParbaude("Car's color", "Grey"); 
		 way = "car"; 
		 
		 //int
		 bDay = skaitlaParbaude("Car's release year (not olser than 50 years)", (Year.now().getValue()) - 50, (Year.now().getValue()));

		 car = new Car(carName, model, Color, bDay, way, fuel);
		 credits -= 1000;	
		 }else {
			 car.error();
		 }
		 break;
		 
		 //Izsaukt metodi
		 case "Interact with a car":
			 if(car != null) {
				 String[] methodList = {"Pet", "Refuel", "Change a color", "Sell car"};
				 
				 String method = (String) JOptionPane.showInputDialog(null, "Choose a method", "Method chose", JOptionPane.QUESTION_MESSAGE,null, methodList, methodList[0]);
				 
				 if(method == null) 
					 break;
				 
				 
				 switch(method){
				 	case "Pet": car.murrat(); break;
				 	case "Read atributes": JOptionPane.showMessageDialog(null, car.readAtributes(), "Information", JOptionPane.INFORMATION_MESSAGE);  break;
				 	case "Refuel": String way = "refuel"; car.Refuel(credits, fuel); break;
				    case "Sell car":JOptionPane.showMessageDialog(null, "Your car was sold for 1000 credits"); car = null; credits += 1000; fileDelete(".");
				 	
				 }
				 
			 } else {
				 JOptionPane.showMessageDialog(null, "You need a car!", "Warning", JOptionPane.WARNING_MESSAGE);
			 }
			 break;
			 
			 //Saglabāt failā
		  case "Save": 
			  if(car != null) {
				  DarbsArFailu.save(car, fileName);
			  } else {
				  JOptionPane.showMessageDialog(null, "You need a cat to perfom methods!", "Warning", JOptionPane.WARNING_MESSAGE);
			  }
			  
			 //Apskatīt saglabātos kaķus 
		  case "Check saved car":
			  DarbsArFailu.read(fileName);
		 
		  break;
			  
		  // Check for cash
		  case "Check for cash":
		  JOptionPane.showMessageDialog(null, "Your cash: " + credits);
			  break;
			  
			  // === CASINO ===
		  case "=== CASINO ===":
			  String[] methodList2 = {"Poker", "Spins"};
		 		String method2 = (String) JOptionPane.showInputDialog(null, "Game", "Random JOptionPane window name", 
		 				JOptionPane.QUESTION_MESSAGE,null, methodList2, methodList2[0]);
		 		
		 		 if(method2 == null) 
					 break;
		 		 
		 		switch(method2) {
		 		case "Poker": credits = Videopoker.Poker(credits); break;
		 		case "Spins": credits = slots.Slots(credits); break;
		 		}
			  break;
			  
			  // Close
		  case "Close":  
			  if(menu == null) 
					 break;
			  
			  if(car != null) {
				menu = car.close(menu);  
				
			  }  else {
					 JOptionPane.showMessageDialog(null, "Atleast buy a car!", "Warning", JOptionPane.WARNING_MESSAGE);
				 }
			  
		 }//switch		 
		
		}while(!menu.equals("CloseTrue"));	
		
	}// main
	
	static String virknesParbaude(String notification, String def){
	String virkne;
	
	do{
	
	virkne = JOptionPane.showInputDialog(notification, def);
	
	if(virkne == null){
	return null;
	} else {
	virkne = virkne.trim();
	}
	
	}while(!Pattern.matches("^[\\p{L} 0-9]+$", virkne));
	return virkne;
	}// virknesParbaude
	
	static int skaitlaParbaude(String notif, int min, int max){
	String ievade;
	int skaitlis;
	while(true){
	ievade = JOptionPane.showInputDialog(notif, max);
	if(ievade == null){
	return -1;
	
	}
	try{
	skaitlis = Integer.parseInt(ievade);
	if(skaitlis < min || skaitlis > max){
	JOptionPane.showMessageDialog(null, "The number is out of bounds of allowed interval", "Warning", JOptionPane.WARNING_MESSAGE);
	continue;
		}
	return skaitlis;
	
	} catch(NumberFormatException e){
	JOptionPane.showMessageDialog(null, "The number is not integer", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	}
	
	}// skaitlaParbaude
	
	public static void init() {
	    loadTips();
	    
 
	    Random rand = new Random();
	    int r = rand.nextInt(tips.size());

	    JOptionPane pane = new JOptionPane(
	        tips.get(r),
	        JOptionPane.INFORMATION_MESSAGE
	    );

	    JDialog dialog = pane.createDialog(null, "Loading...");
	    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

	   
	    Timer timer = new Timer(3000, e -> dialog.dispose());
	    timer.setRepeats(false);
	    timer.start();

	    dialog.setVisible(true);
	   
	}// init
	
	private static void loadTips() {
		
        try (BufferedReader br = new BufferedReader(new FileReader("tips.txt"))) {

            String line;

            while ((line = br.readLine()) != null) {
                tips.add(line);
            }

        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }// loadTips
	
	public static void fileDelete(String directoryPath) {
		 File directory = new File(directoryPath);

	        if (!directory.isDirectory()) {
	            return;
	        }

	        File[] files = directory.listFiles();

	        if (files == null) {
	            return;
	        }

	        for (File file : files) {
	            if (file.isFile() && file.getName().startsWith("cars") && file.getName().endsWith(".txt")) {
	                file.delete();
	            }
	        }
	}//fileDelete
	
}// MinkuTante