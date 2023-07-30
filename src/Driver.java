import javax.swing.*;

public class Driver {
    public static void main(String args[]) {
        Game game = new Game();
        //TODO: co to jest invokeLater
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View view = new View(game);
            }
        });
    }
}