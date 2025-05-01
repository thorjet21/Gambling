import java.util.ArrayList;
import java.util.Random;

public class Card {
    private static ArrayList<String> cardImages = new ArrayList<>();

    private static final String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
    private static final String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace"};

    public Card() {
        makeNewDeck();
    }

    private void makeNewDeck() {
        cardImages.clear();
        for (String rank : ranks) {
            for (String suit : suits) {
                cardImages.add(rank + "Of" + suit + ".png");
            }
        }
    }

    public String dealCard() {
        int rand = (int)(Math.random()*cardImages.size());
        String getCard = cardImages.get(rand);
        System.out.println(getCard);
        cardImages.remove(rand);
        return getCard;
    }
}
