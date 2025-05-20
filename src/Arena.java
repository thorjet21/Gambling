import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Arena extends JPanel {
    private ArrayList<String> deck;
    private ArrayList<Image> playerHand = new ArrayList<>();
    private ArrayList<Image> dealerHand = new ArrayList<>();
    private ArrayList<String> playerCards = new ArrayList<>();
    private ArrayList<String> dealerCards = new ArrayList<>();
    private Card cardDeck;

    private boolean playerTurn = true;
    private boolean roundOver = false;

    public Arena() {
        setBackground(Color.BLACK);
        cardDeck = new Card(this);
        startNewRound();
    }

    public void startNewRound() {
        playerHand.clear();
        dealerHand.clear();
        playerCards.clear();
        dealerCards.clear();
        roundOver = false;
        playerTurn = true;

        // Deal initial 2 cards to player and dealer
        for (int i = 0; i < 2; i++) {
            dealCardToPlayer();
            dealCardToDealer();
        }
        repaint();
    }

    public void dealCardToPlayer() {
        String cardFile = "Cards/" + cardDeck.dealCard();
        playerCards.add(cardFile);
        playerHand.add(new ImageIcon(cardFile).getImage());
        repaint();

        if (calculateTotal(playerCards) > 21) {
            roundOver = true;
            showResult("You busted! Dealer wins.");
        }
    }

    public void dealCardToDealer() {
        String cardFile = "Cards/" + cardDeck.dealCard();
        dealerCards.add(cardFile);
        dealerHand.add(new ImageIcon(cardFile).getImage());
        repaint();
    }

    public void stand() {
        playerTurn = false;

        while (calculateTotal(dealerCards) < 17) {
            dealCardToDealer();
        }

        int playerTotal = calculateTotal(playerCards);
        int dealerTotal = calculateTotal(dealerCards);

        if (dealerTotal > 21 || playerTotal > dealerTotal) {
            showResult("You win!");
        } else if (dealerTotal == playerTotal) {
            showResult("It's a tie!");
        } else {
            showResult("Dealer wins!");
        }

        roundOver = true;
    }

    public void doubleDown() {
        if (playerTurn && playerCards.size() == 2) {
            dealCardToPlayer();
            stand(); // force stand after double
        }
    }

    public void showResult(String message) {
        JOptionPane.showMessageDialog(this, message, "Game Result", JOptionPane.INFORMATION_MESSAGE);
        startNewRound();
    }

    public int calculateTotal(ArrayList<String> cards) {
        int total = 0;
        int aceCount = 0;

        for (String card : cards) {
            String rank = card.substring(card.lastIndexOf("/") + 1);
            rank = rank.substring(0, rank.indexOf("Of"));

            if (rank.equals("jack") || rank.equals("queen") || rank.equals("king")) {
                total += 10;
            } else if (rank.equals("ace")) {
                total += 11;
                aceCount++;
            } else {
                total += Integer.parseInt(rank);
            }
        }

        while (total > 21 && aceCount > 0) {
            total -= 10;
            aceCount--;
        }

        return total;
    }
    public boolean isRoundOver() {
        return roundOver;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw player's cards
        int x = 10;
        for (Image img : playerHand) {
            g.drawImage(img, x, getHeight() - 120, 70, 100, null);
            x += 80;
        }

        // Draw dealer's cards
        x = 10;
        for (Image img : dealerHand) {
            g.drawImage(img, x, 20, 70, 100, null);
            x += 80;
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Player", 10, getHeight() - 130);
        g.drawString("Dealer", 10, 15);
    }
}
