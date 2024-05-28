import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import javax.swing.*;

import static java.lang.Math.abs;

public class gameScreen extends JPanel implements KeyListener {
    final int PANEL_WIDTH = 600;
    final int PANEL_HEIGHT = 700;
    final Image ballCatcher;
    int netWidth = 80;
    int netHeight = 80;
    int ballCatcherX = PANEL_WIDTH - netWidth;
    int ballCatcherY = PANEL_HEIGHT-netHeight;
    int ballWidth = 50;
    int ballHeight = 50;
    listOfBalls balls;
    private final Timer timer;
    Iterator<ball> it;
    int currentNum;
    int scoreNum = 0;
    JLabel score = new JLabel();


    gameScreen() {
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.setBackground(Color.white);
        ballCatcher = new ImageIcon("src/Image/net.png").getImage();
        balls = new listOfBalls();
        score.setText("Score: " + scoreNum);
        this.add(score);
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (ball b: balls.getBalls()) {
                    if (b.getY() + ballHeight>= PANEL_HEIGHT) {
                        timer.stop();
                        // NEW window to indicate game over.
                    } else {
                        b.setY(b.getY()+10);
                    }
                    repaint();
                }
                if (currentNum >= 15) {
                    ball newBall = new ball(); // ADD DELAY
                    balls.addBall(newBall);
                    currentNum = 0;
                } else {
                    currentNum++;
                }

            }
        });
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics g2D = (Graphics2D) g;
        it = balls.iterator(); // MOVE TO FIELD?
        while (it.hasNext()) {
            ball b = it.next();
            g2D.drawImage(ball.ballImg, b.getX(), b.getY(), ballWidth, ballHeight, null);
            if (b.getY()>= ballCatcherY && abs((b.getX() + ballWidth/2) - (ballCatcherX + netWidth/2)) < 40) { // !!! FIND CENTER
                it.remove();
                scoreNum++;
                score.setText("Score: " + scoreNum);
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
                ballCatcherX += 15;
                repaint();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (ballCatcherX -10 >= 0) {
                ballCatcherX -= 15;
                repaint();
            }
        }
    }

    public void keyReleased(KeyEvent e) {
    }
}
