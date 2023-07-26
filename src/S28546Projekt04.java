import javax.swing.*;

public class S28546Projekt04 {
    public static void main(String args[]) {
        Game game = new Game();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View view = new View(game);
            }
        });
    }
}