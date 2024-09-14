import javax.swing.*;

public class startScreen extends JFrame {

    gameScreen canvas;

    //create the GUI of the game
    startScreen() {

        canvas = new gameScreen();

        this.setTitle("Project V");
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(canvas);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}