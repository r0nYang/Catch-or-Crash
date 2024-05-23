import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class gameScreen extends JPanel implements KeyListener {
    final int PANEL_WIDTH = 600;
    final int PANEL_HEIGHT = 700;
    Timer timer;
    Image ballCatcher;
    Image ball;
    Image test; // !!!!! del later
    int x = 250;
    int y = 550;
    int netWidth = 80;
    int netHeight = 80;
    int ballWidth = 500;
    int ballHeight = 290;


    gameScreen() {
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.setBackground(Color.white);
        ballCatcher = new ImageIcon("src/net.png").getImage();
        ball = new ImageIcon("src/ball.png").getImage();
        test = new ImageIcon("src/Capt22ure.png").getImage();

    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(test, x, y, netWidth, netHeight, null); // to check the hitbox of net
        g2D.drawImage(ballCatcher, x, y, netWidth, netHeight, null);
        g2D.drawImage(ball, 25, 25, ballWidth, ballHeight, null);

    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (x > PANEL_WIDTH - 100) {
                x += 0;
            } else {
                x += 10;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (x < 20) {
                x -= 0;
            } else {
                x -= 10;
            }
        }
        repaint();
    }

    public void keyReleased(KeyEvent e) {
    }
}
