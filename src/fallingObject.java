import javax.swing.*;
import java.awt.*;
import java.util.Random;

public abstract class fallingObject {
    protected int x;
    protected int y = 50;
    public Image objectImage;
    Random rand = new Random();
    public fallingObject() {
        x= rand.nextInt(600-60);
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
