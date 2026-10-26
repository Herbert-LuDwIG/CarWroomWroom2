package ooop;

import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.Image;
import java.net.URL;

public class slots {
	
	private static void paraditLogu(String tekst, int milliseconds) {
	    JOptionPane pane = new JOptionPane(
	        tekst,
	        JOptionPane.INFORMATION_MESSAGE
	    );

	    JDialog dialog = pane.createDialog("SLOTS GAME");

	    Timer timer = new Timer(milliseconds, e -> {
	        dialog.dispose();
	    });

	    timer.setRepeats(false);
	    timer.start();

	    dialog.setVisible(true);
	}
	
    public static double Slots(double credits) {
    	int bet = 150;
    	if (credits < bet) {
            JOptionPane.showMessageDialog(null, "Not enough credits!");
            return credits;
        }
    	
        Random random = new Random();

        int izvele = 0;

        String[] simboli = {"🍒", "🍋", "🍉", "⭐", "7️⃣"};

        JOptionPane.showMessageDialog(
            null,
            "----------------------------------\n" +
            "|             CASINO             |\n" +
            "|            Klondaika           |\n" +
            "|                                |\n" +
            "|           SLOTS GAME           |\n" +
            "----------------------------------",
            "Casino",
            JOptionPane.INFORMATION_MESSAGE
        );

        do {
            String menu =
                "========== MENU ==========\n" +
                "Credits: " + credits + "\n\n" +
                "1 - Spin\n" +
                "2 - Rules\n" +
                "3 - Go away\n\n" +
                "Choose:";

            String ievade = JOptionPane.showInputDialog(
                null,
                menu,
                "SLOTS GAME",
                JOptionPane.QUESTION_MESSAGE
            );

            // Ja lietotājs aizver logu, spēle beidzas
            if (ievade == null) {
                izvele = 3;
                break;
            }

            try {
                izvele = Integer.parseInt(ievade);

                if (izvele < 1 || izvele > 5) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Nepareiza izvele!",
                        "Kļūda",
                        JOptionPane.ERROR_MESSAGE
                    );
                    continue;
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                    null,
                    "Kļūda! Ievadi veselu skaitli.",
                    "Kļūda",
                    JOptionPane.ERROR_MESSAGE
                );
                continue;
            }

            if (izvele == 1) {

         

                credits -= 150;

                String a = simboli[random.nextInt(simboli.length)];
                String b = simboli[random.nextInt(simboli.length)];
                String c = simboli[random.nextInt(simboli.length)];

                paraditLogu(
                	    "Slot spinning...\n\n" +
                	    "[  ?  |  ?  |  ?  ]",
                	    1000
                	);

                	paraditLogu(
                	    "Slot spinning...\n\n" +
                	    "[  " + a + "  |  ?  |  ?  ]",
                	    1000
                	);

                	paraditLogu(
                	    "Slot spinning...\n\n" +
                	    "[  " + a + "  |  " + b + "  |  ?  ]",
                	    1000
                	);

                String rezultats =
                    "[  " + a + "  |  " + b + "  |  " + c + "  ]";

                String pazinojums;

                if (a.equals("7️⃣") &&
                    b.equals("7️⃣") &&
                    c.equals("7️⃣")) {

                    credits += 1000;
                    
                    pazinojums = "MEGA JACKPOT!!! +1000 credits!";

                } else if (a.equals(b) && b.equals(c)) {

                    credits += 500;
                    pazinojums = "W spin! +500 credits!";

                } else if (a.equals(b) ||
                           b.equals(c) ||
                           a.equals(c)) {

                    credits += 200;
                    pazinojums = "Nice! 200 credits!";

                } else {
                    pazinojums = "Good luck next time!";
                }

                JOptionPane.showMessageDialog(
                    null,
                    rezultats + "\n\n" +
                    pazinojums + "\n\n" +
                    "Atlikušie krediti: " + credits,
                    "SLOTS GAME",
                    JOptionPane.INFORMATION_MESSAGE
                );

            } else if (izvele == 2) {

                JOptionPane.showMessageDialog(
                    null,
                    "======= SLOT GAME RULES =======\n\n" +
                    "One bet price: 150 credits\n" +
                    "3 same symbols: +500 credits\n" +
                    "2 same symbols: +200 credits\n" +
                    "3 of 7️⃣ in a row: +1000 credits\n" +
                    "No matches: +0 credits\n\n" +
                    "================================",
                    "Rules",
                    JOptionPane.INFORMATION_MESSAGE
                );

            }

        } while (izvele != 3);

        JOptionPane.showMessageDialog(
            null,
            "The game has been stopped!\n\n" +
            "Your credits: " + credits,
            "SLOTS GAME",
            JOptionPane.INFORMATION_MESSAGE
        );

        return credits;
    }// Slots
    
    public ImageIcon makeAnImage2(String way) {
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
    
}// Class