import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ball {
    private int x;
    private int y = 10;
    public static Image ballImg;
    Random rand = new Random();

    public ball() {
        ballImg = new ImageIcon("src/Image/ball.png").getImage();
        x= rand.nextInt(500);
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
}
