import javax.print.attribute.standard.Finishings;
import javax.swing.*;

public class Main {

    //TODO: ??? usunac te kolejnosci, jakby tu pierwszy byl game to sie wyjebie wszystko
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Frame frame = new Frame();
                Game game = new Game();

                game.setCellListener(frame.getCellListener());
                game.setScoreListener(frame.getScoreListener());
                game.setGameStatusListener(frame.getGameStatusListener());

                frame.setUserActionListener(game);
            }
        });
    }
}