import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class gameScreen extends JPanel implements ActionListener {
    final int PANEL_WIDTH = 600;
    final int PANEL_HEIGHT = 700;
    Timer timer;
    Image ballCatcher;
    int x = 0;
    int y = 0;

    gameScreen() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.setBackground(Color.white);
        ballCatcher = new ImageIcon("net.png").getImage();
        timer = new Timer(10, this);
        timer.start();
    }

    public void paint(Graphics g) {

        super.paint(g); // paint background

        Graphics2D g2D = (Graphics2D) g;

        //g2D.drawImage(backgroundImage, 0, 0, null);
        g2D.drawImage(ballCatcher, x, y, null);
    }

    public void actionPerformed(ActionEvent e) {

    }
}
