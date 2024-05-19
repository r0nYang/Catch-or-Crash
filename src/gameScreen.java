import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class gameScreen extends JPanel implements KeyListener {
    final int PANEL_WIDTH = 600;
    final int PANEL_HEIGHT = 700;
    Timer timer;
    Image ballCatcher;
    int x = 250;
    int y = 550;
    int width = 80;
    int height = 80;


    gameScreen() {
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.setBackground(Color.white);
        ballCatcher = new ImageIcon("src/net.png").getImage();

    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(ballCatcher, x, y, width, height, null);
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
