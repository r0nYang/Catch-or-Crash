import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import javax.swing.*;

import static java.lang.Math.abs;

public class gameScreen extends JPanel implements KeyListener {
    final int PANEL_WIDTH = 600;
    final int PANEL_HEIGHT = 700;
    final Image ballCatcher = new ImageIcon("src/Image/net.png").getImage();
    int netWidth = 80;
    int netHeight = 80;
    int ballCatcherX = PANEL_WIDTH - netWidth;
    int ballCatcherY = PANEL_HEIGHT-netHeight - 15;
    int ballWidth = 50;
    int ballHeight = 50;
    listOfFallingObjects fallingObjects;
    public final Timer timer;
    Iterator<fallingObject> it;
    int spawnBallCountDown = 15;
    int spawnBombCountDown = 35;
    int scoreNum = 0;
    Graphics g2D;
    boolean acceptKeyPress = true;


    gameScreen() {
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.setBackground(Color.white);
        fallingObjects = new listOfFallingObjects();
        JButton restartButton = getjButton();
        this.add(restartButton);

        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (fallingObject obj: fallingObjects.getFallingObjects()) {
                    obj.setY(obj.getY()+10);
                    repaint();
                }
                if (spawnBallCountDown == 0) {
                    fallingObject newBall = new ball();
                    fallingObjects.addFallingObject(newBall);
                    spawnBallCountDown = 15;
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

    private JButton getjButton() {
        JButton restartButton = new JButton(new AbstractAction("Restart") {
            @Override
            public void actionPerformed(ActionEvent e) {
                fallingObjects = new listOfFallingObjects();
                scoreNum=0;
                acceptKeyPress=true;
                timer.start();
                requestFocusInWindow(); // resets focus of the panel; enables net movement after restarting game.
            }
        });
        restartButton.setFont(new Font("Arial", Font.BOLD, 18));
        restartButton.setBackground(Color.WHITE);
        restartButton.setFocusPainted(false);
        restartButton.setBorderPainted(false);
        return restartButton;
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
        g.setColor(Color.red);
        g.setFont( new Font("Lucida Sans Unicode",Font.BOLD, 20));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Score: "+ scoreNum, (metrics.stringWidth("Score: "+ scoreNum)) - netWidth + 10, g.getFont().getSize());
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
        g.setFont( new Font("Lucida Sans Unicode",Font.BOLD, 75));
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
