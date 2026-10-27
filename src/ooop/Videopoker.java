package ooop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

public class Videopoker {

    public static double Poker(double credits) {

        int bet = 150;

        if (credits < bet) {
            JOptionPane.showMessageDialog(null, "Not enough credits!");
            return credits;
        }

        credits -= bet;

        String[] vertibas = {
            "2", "3", "4", "5", "6", "7",
            "8", "9", "10", "J", "Q", "K", "A"
        };

        String[] mastis = {"♥", "♠", "♦", "♣"};

        List<String> kartis = new ArrayList<>();

        for (String vertiba : vertibas) {
            for (String masts : mastis) {
                kartis.add(vertiba + masts);
            }
        }

        Collections.shuffle(kartis);

        List<String> speletajaKartis = new ArrayList<>();

        JOptionPane.showMessageDialog(null, "--- POKER ---" + "\nPress \"Ok\" to shuffle");
        //System.out.println("\n--- VIDEO POKER ---");
        //System.out.println("Kārtis tiek dalītas...");

        for (int i = 0; i < 5; i++) {
            String karte = kartis.get(i);
            speletajaKartis.add(karte);

            //System.out.println("Kārts " + (i + 1) + ": " + karte);

            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return credits;
            }
        }

        JOptionPane.showMessageDialog(null, "Your cards: " + speletajaKartis);
        //System.out.println("\nTavas kārtis: " + speletajaKartis);

        int[] skaits = new int[15];

        String pirmaisMasts = speletajaKartis.get(0)
                .substring(speletajaKartis.get(0).length() - 1);

        boolean flush = true;

        // Saskaita kāršu vērtības un pārbauda mastis
        for (String karte : speletajaKartis) {
            String vertiba = karte.substring(0, karte.length() - 1);
            String masts = karte.substring(karte.length() - 1);

            int v = vertiba(vertiba);
            skaits[v]++;

            if (!masts.equals(pirmaisMasts)) {
                flush = false;
            }
        }

        boolean straight = straightHigh(skaits) > 0;

        int straightHigh = straightHigh(skaits);

        int pairs = 0;
        boolean three = false;
        boolean four = false;

        for (int i = 2; i <= 14; i++) {
            if (skaits[i] == 2) {
                pairs++;
            } else if (skaits[i] == 3) {
                three = true;
            } else if (skaits[i] == 4) {
                four = true;
            }
        }

        String kombinacija;

        // Nosaka kombināciju
        if (flush && straight && straightHigh == 14) {
            kombinacija = "Royal Flush";
        } else if (flush && straight) {
            kombinacija = "Straight Flush";
        } else if (four) {
            kombinacija = "Four of a Kind";
        } else if (three && pairs == 1) {
            kombinacija = "Full House";
        } else if (flush) {
            kombinacija = "Flush";
        } else if (straight) {
            kombinacija = "Straight";
        } else if (three) {
            kombinacija = "Three of a Kind";
        } else if (pairs == 2) {
            kombinacija = "Two Pair";
        } else if (pairs == 1) {
            kombinacija = "One Pair";
        } else {
            kombinacija = "High Card";
        }

        // Izmaksas par kombinācijām
        int win;

        switch (kombinacija) {
            case "Royal Flush":
            	win = 1000;
                break;
            case "Straight Flush":
            	win = 500;
                break;
            case "Four of a Kind":
            	win = 750;
                break;
            case "Full House":
            	win = 600;
                break;
            case "Flush":
            	win = 650;
                break;
            case "Straight":
                win = 350;
                break;
            case "Three of a Kind":
                win = 325;
                break;
            case "Two Pair":
                win = 300;
                break;
            case "One Pair":
                win = 200;
                break;
            case "High Card":
                win = 0;
                break;
            default:
                win = 0;
        }

        credits += win;

        JOptionPane.showMessageDialog(null, "Combination: " + kombinacija + "\nYour win: " + win + "\nCredits: " + credits);
        //System.out.println("\nKombinācija: " + kombinacija);
        //System.out.println("Saņemtā izmaksa: " + win);
        //System.out.println("Atlikušie kredīti: " + credits);

        return credits;
    }

    public static int vertiba(String v) {
        switch (v) {
            case "J":
                return 11;
            case "Q":
                return 12;
            case "K":
                return 13;
            case "A":
                return 14;
            default:
                return Integer.parseInt(v);
        }
    }


    public static int straightHigh(int[] skaits) {

        for (int i = 2; i <= 10; i++) {
            boolean irTaisne = true;

            for (int j = i; j < i + 5; j++) {
                if (skaits[j] == 0) {
                    irTaisne = false;
                    break;
                }
            }

            if (irTaisne) {
                return i + 4;
            }
        }

        if (skaits[14] > 0 && skaits[2] > 0 && skaits[3] > 0 && skaits[4] > 0  && skaits[5] > 0) {
            return 5;
        }

        return 0;
    }
}