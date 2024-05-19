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
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.setBackground(Color.white);
        ballCatcher = new ImageIcon("src/net.png").getImage();
        timer = new Timer(10, this);
        timer.start();
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
            x = 1;  // move to the right
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {  
            x = -1;  // move to the left
        }
    }

    public void keyReleased(KeyEvent e) {
    }
}
