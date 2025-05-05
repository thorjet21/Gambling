import javax.swing.*;
import java.awt.*;

public class Arena extends JPanel {
    private Image cardImage;

    public Arena() {
        setBackground(Color.BLACK);
        Card card = new Card();
        String deal = "Cards/" + card.dealCard();

        ImageIcon pic = new ImageIcon(deal);
        cardImage = pic.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(cardImage, 10, getHeight()-110, 70, 100, null);

    }
}
