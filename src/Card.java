import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Card {
    private static ArrayList<String> cardImages = new ArrayList<>();

    private static final String blank = "blankCard.png";
    private static final String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
    private static final String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace"};

    //this is for a alert controller - https://docs.oracle.com/javase/8/docs/api/java/awt/Component.html
    private Component alertController;

    public Card(Component alertController) {
        this.alertController = alertController;
        makeNewDeck(false);
    }

    public void makeNewDeck(boolean showAlert) {
        cardImages.clear();
        for (String rank : ranks) {
            for (String suit : suits) {
                cardImages.add(rank + "Of" + suit + ".png");
            }
        }

        if (showAlert && alertController != null) {
            JOptionPane.showMessageDialog(alertController, "New deck of cards has been created", "Alert", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public String dealCard() {
        if (cardImages.size() == 0) {
            makeNewDeck(true);
            System.out.println("made new deck");
        }

        int rand = (int) (Math.random() * cardImages.size());
        String getCard = cardImages.get(rand);
        System.out.println(getCard);
        cardImages.remove(rand);
        return getCard;
    }

    public static String getBlank() {
        return blank;
    }
}
