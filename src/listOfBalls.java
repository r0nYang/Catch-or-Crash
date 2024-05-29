import java.util.*;
public class listOfBalls implements Iterable<fallingObject> {
    private List<fallingObject> fallingObjects;

    public listOfBalls() {
        fallingObjects = new ArrayList<>();
    }

    public void addFallingObject(fallingObject b) {
        if (!fallingObjects.contains(b)) { // maybe check if the balls are colliding instead?
            fallingObjects.add(b);
        }
    }

    public List<fallingObject> getFallingObjects() {
        return fallingObjects;
    }

    @Override
    public Iterator<fallingObject> iterator() {
        return fallingObjects.iterator();
    }
}
