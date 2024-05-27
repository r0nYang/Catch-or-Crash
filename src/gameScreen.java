import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import javax.swing.*;

import static java.lang.Math.abs;

public class gameScreen extends JPanel implements KeyListener {
    final int PANEL_WIDTH = 600;
    final int PANEL_HEIGHT = 700;
    Image ballCatcher;
    int x = 250;
    int y = 550;
    int netWidth = 80;
    int netHeight = 80;
    int ballWidth = 60;
    int ballHeight = 60;
    Graphics g2D;
    boolean isRunning = false;
    listOfBalls balls;
    ball ball1;
    ball ball2;
    private Timer time;


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
        time = new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (ball b: balls.getBalls()) {
                    if (b.getY() + 150>= PANEL_HEIGHT) {
                        isRunning=false;
                        time.stop();
                        // NEW window to indicate game over.
                    } else {
                        b.setY(b.getY()+50);
                    }
                    repaint();
                }
            }
        });
        time.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        g2D = (Graphics2D) g;
//        g2D.drawImage(ball, 25, 25, ballWidth, ballHeight, null);
        Iterator<ball> it = balls.iterator();
        while (it.hasNext()) {
            ball b = it.next();
            g2D.drawImage(ball.ballImg, b.getX(), b.getY(), ballWidth, ballHeight, null);
            if (b.getY()>= y && abs(b.getX()-x) < 30) {
                it.remove();
            }
            if (b.getY()+ballHeight >= PANEL_HEIGHT) {
                time.stop();
            }
        }
        g2D.drawImage(ballCatcher, x, y, netWidth, netHeight, null);

    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (x+80+10 <= PANEL_WIDTH) {
                x += 10;
                repaint();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (x-10 >= 0) {
                x -= 10;
                repaint();
            }
        }
//        else if (e.getKeyCode() == KeyEvent.VK_T) {
//            if (!isRunning) {
//                isRunning=true;
//                time.start();
//            } else {
//                isRunning=false;
//                time.stop();
//            }
//        }

    }

    public void keyReleased(KeyEvent e) {
    }
}
