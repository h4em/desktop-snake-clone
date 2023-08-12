import javax.swing.*;

public class Main {
    public static void main(String args[]) {
        Game game = new Game();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main.init(game, new Frame());
            }
        });

        game.start();
    }

    public static void init(Game game, Frame frame) {

    }
}

/*
    jak tworzysz gamea to tam nic ma nie zaczyna leciec,
    czekac na inity wszystkich listenerow

 */