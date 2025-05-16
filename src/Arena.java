import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Arena extends JPanel {
    private ArrayList<Image> cardImages = new ArrayList<>();
    private Card cardDeck;

    public Arena() {
        setBackground(Color.BLACK);
        cardDeck = new Card(this);
        dealCard();
    }

    public void dealCard() {
        String cardFile = "Cards/" + cardDeck.dealCard();
        ImageIcon cardIcon = new ImageIcon(cardFile);
        cardImages.add(cardIcon.getImage());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 10;
        for (Image img : cardImages) {
            g.drawImage(img, x, getHeight() - 110, 70, 100, null);
            x += 80;
        }
    }
}

