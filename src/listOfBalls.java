import java.util.*;
public class listOfBalls implements Iterable<ball> {
    private List<ball> balls;

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

    @Override
    public Iterator<ball> iterator() {
        return balls.iterator();
    }
}
