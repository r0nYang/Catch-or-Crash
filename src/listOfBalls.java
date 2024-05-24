import java.util.*;
public class listOfBalls {
    List<ball> balls;

    public listOfBalls() {
        balls = new ArrayList<>();
    }

    public void addBall(ball b) {
        if (!balls.contains(b)) {
            balls.add(b);
        }
    }

    public List<ball> getBalls() {
        return balls;
    }

}
