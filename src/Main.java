import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("BlackJack");
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());
        //makes it so it cant be resized by the user
        frame.setResizable(false);

        Arena panel = new Arena();
        frame.add(panel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.LIGHT_GRAY);
        //https://docs.oracle.com/javase/8/docs/api/javax/swing/BorderFactory.html
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        //https://docs.oracle.com/javase/8/docs/api/java/awt/FlowLayout.html
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JLabel moneyLabel = new JLabel("Money: $1000");
        moneyLabel.setForeground(Color.WHITE);
        moneyLabel.setFont(new Font("money", Font.BOLD, 14));

        JLabel betLabel = new JLabel("Bet:");
        betLabel.setForeground(Color.WHITE);
        betLabel.setFont(new Font("bet", Font.PLAIN, 14));
        JTextField betMoney = new JTextField(6);
        betMoney.setFont(new Font("betMoney", Font.PLAIN, 14));

        betMoney.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                char x = e.getKeyChar();
                if(!Character.isDigit(x) && x != KeyEvent.VK_BACK_SPACE && x != KeyEvent.VK_DELETE){
                    //https://stackoverflow.com/questions/12550548/what-does-e-consume-do-in-java
                    e.consume();
                }
            }
        });

        JButton hitButton = new JButton("Hit");
        hitButton.addActionListener(e -> panel.dealCard());
        JButton standButton = new JButton("Stand");
        JButton doubleButton = new JButton("Double");

        bottomPanel.add(moneyLabel);
        bottomPanel.add(betLabel);
        bottomPanel.add(betMoney);
        bottomPanel.add(hitButton);
        bottomPanel.add(standButton);
        bottomPanel.add(doubleButton);

        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

    }
}
