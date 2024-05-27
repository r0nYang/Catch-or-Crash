import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import javax.swing.*;

import static java.lang.Math.abs;

public class gameScreen extends JPanel implements KeyListener {
    final int PANEL_WIDTH = 600;
    final int PANEL_HEIGHT = 700;
    Image ballCatcher;
    int ballCatcherX = 250;
    int ballCatcherY = 550;
    int netWidth = 80;
    int netHeight = 80;
    int ballWidth = 60;
    int ballHeight = 60;
    Graphics g2D;
    listOfBalls balls;
    ball ball1;
    ball ball2;
    private final Timer timer;


    gameScreen() {
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.setBackground(Color.white);
        ballCatcher = new ImageIcon("src/Image/net.png").getImage();
        balls = new listOfBalls();
        ball1 = new ball();
        ball2 = new ball();
        balls.addBall(ball1);
        balls.addBall(ball2);
        timer = new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (ball b: balls.getBalls()) {
                    if (b.getY() + 150>= PANEL_HEIGHT) {
                        timer.stop();
                        // NEW window to indicate game over.
                    } else {
                        b.setY(b.getY()+50);
                    }
                    repaint();
                }
            }
        });
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        g2D = (Graphics2D) g;
        Iterator<ball> it = balls.iterator();
        while (it.hasNext()) {
            ball b = it.next();
            g2D.drawImage(ball.ballImg, b.getX(), b.getY(), ballWidth, ballHeight, null);
            if (b.getY()>= ballCatcherY && abs(b.getX()- ballCatcherX) < 20) { // !!! FIND CENTER
                it.remove();
            }
            if (b.getY()+ballHeight >= PANEL_HEIGHT) {
                timer.stop();
            }
        }
        g2D.drawImage(ballCatcher, ballCatcherX, ballCatcherY, netWidth, netHeight, null);

    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (ballCatcherX +80+10 <= PANEL_WIDTH) {
                ballCatcherX += 10;
                repaint();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (ballCatcherX -10 >= 0) {
                ballCatcherX -= 10;
                repaint();
            }
        }
    }

    public void keyReleased(KeyEvent e) {
    }
}
