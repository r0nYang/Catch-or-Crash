import java.util.*;

import static java.lang.Math.abs;

public class listOfFallingObjects implements Iterable<fallingObject> {
    private List<fallingObject> fallingObjects;

    public listOfFallingObjects() {
        fallingObjects = new ArrayList<>();
    }

    public void addFallingObject(fallingObject obj) {
        if (notColliding(obj)) {
            fallingObjects.add(obj);
        }
    }

    private boolean notColliding(fallingObject obj) {
        for (fallingObject f: fallingObjects) {
            if (abs(obj.getX() - f.getX()) < 20 && abs(obj.getY() - f.getY()) < 40) {
                return false;
            }
        }
        return true;
    }


    public List<fallingObject> getFallingObjects() {
        return fallingObjects;
    }

    @Override
    public Iterator<fallingObject> iterator() {
        return fallingObjects.iterator();
    }
}
