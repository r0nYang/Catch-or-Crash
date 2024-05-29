import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import javax.swing.*;

import static java.lang.Math.abs;

public class gameScreen extends JPanel implements KeyListener {
    final int PANEL_WIDTH = 600;
    final int PANEL_HEIGHT = 700;
    final Image ballCatcher = new ImageIcon("src/Image/net.png").getImage();;
    int netWidth = 80;
    int netHeight = 80;
    int ballCatcherX = PANEL_WIDTH - netWidth;
    int ballCatcherY = PANEL_HEIGHT-netHeight;
    int ballWidth = 50;
    int ballHeight = 50;
    listOfFallingObjects fallingObjects;
    private final Timer timer;
    Iterator<fallingObject> it;
    int spawnBallCountDown = 100;
    int spawnBombCountDown = 35;
    int scoreNum = 0;
    JLabel score = new JLabel();
    Graphics g2D;
    boolean acceptKeyPress = true;


    gameScreen() {
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.setBackground(Color.white);
        fallingObjects = new listOfFallingObjects();
        score.setText("Score: " + scoreNum);
        this.add(score);
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (fallingObject obj: fallingObjects.getFallingObjects()) {
//                    if (obj.getY() + ballHeight>= PANEL_HEIGHT) {
//                        if (obj instanceof ball) {
//                            timer.stop();
//                            endScreen(g2D);
//                        } else { // must be a bomb otherwise
//                            fallingObjects.rem
//                        }
//
//                    } else {
//                        obj.setY(obj.getY()+10);
//                    }
                    obj.setY(obj.getY()+10);
                    repaint();
                }
                if (spawnBallCountDown == 0) {
                    fallingObject newBall = new ball();
                    fallingObjects.addFallingObject(newBall);
                    spawnBallCountDown = 100;
                } else if (spawnBombCountDown == 0){
                    fallingObject newBomb = new bomb();
                    fallingObjects.addFallingObject(newBomb);
                    spawnBombCountDown = 35;
                } else {
                    spawnBallCountDown--;
                    spawnBombCountDown--;
                }
            }
        });
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        g2D = (Graphics2D) g;
        it = fallingObjects.iterator();
        while (it.hasNext()) {
            fallingObject fObj = it.next();

            g2D.drawImage(fObj.objectImage, fObj.getX(), fObj.getY(), ballWidth, ballHeight, null);
            checkForCollision(fObj, g);
            checkForBoundary(g, fObj);
        }
        g2D.drawImage(ballCatcher, ballCatcherX, ballCatcherY, netWidth, netHeight, null);

    }

    private void checkForBoundary(Graphics g, fallingObject fObj) {
        if (fObj.getY()+ballHeight >= PANEL_HEIGHT) { // HAVE TO CHECK FOR BOMB
            if (fObj.getY() + ballHeight>= PANEL_HEIGHT) {
                if (fObj instanceof ball) {
                    timer.stop();
                    acceptKeyPress = false;
                    endScreen(g);
                } else { // must be a bomb otherwise
                    it.remove();
                }
            }
        }
    }

    private void checkForCollision(fallingObject fObj, Graphics g) {
        int objectCenter = fObj.getX() + ballWidth / 2;
        int netCenter = ballCatcherX + netWidth / 2;
        if (fObj.getY()>= ballCatcherY && abs(objectCenter - netCenter) < 40) {
            if (fObj instanceof ball) {
                it.remove();
                scoreNum++;
                score.setText("Score: " + scoreNum);
            } else { // must otherwise be a bomb
                timer.stop();
                acceptKeyPress = false;
                endScreen(g);
            }

        }
    }

    public void endScreen(Graphics g) {
        g.setColor(Color.red);
        this.setBackground(Color.white);
        g.setFont( new Font("Ink Free",Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (PANEL_WIDTH - metrics2.stringWidth("Game Over"))/2, PANEL_HEIGHT/2);
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && acceptKeyPress) {
            if (ballCatcherX +80+10 <= PANEL_WIDTH) {
                ballCatcherX += 20;
                repaint();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && acceptKeyPress) {
            if (ballCatcherX -10 >= 0) {
                ballCatcherX -= 20;
                repaint();
            }
        }
    }

    public void keyReleased(KeyEvent e) {
    }
}
