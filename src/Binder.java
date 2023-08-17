import javax.swing.*;

public class Binder {
    public static void BindComponents(Game game, Frame frame) {
        waitForSetup(frame);
        waitForSetup(game);

        bindGameTable(game, frame);
        bindScorePanel(game, frame);
        bindGameEventListener(game, frame);
        bindUserActionListener(game, frame);
    }

    private static void bindUserActionListener(Game game, Frame frame) {
        frame.setUserActionListener(game);
    }

    private static void bindGameEventListener(Game game, Frame frame) {
        game.setGameEventListener(frame);
    }

    private static void bindGameTable(Game game, Frame frame) {
        GameTable gameTable = frame.getGameTable();
        GameTableModel gameTableModel = (GameTableModel) gameTable.getModel();

        Gameboard gameboard = game.getGameBoard();
        gameboard.setDataModelListener(gameTableModel);
        gameboard.fireDataInitialised();
    }

    //git
    private static void bindScorePanel(Game game, Frame frame) {
        game.setGameEventListener(frame);
    }

    public static void waitForSetup(Game game) {
        while(!game.setupFinished()) {
            try {
                Thread.sleep(10);
            } catch(InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    public static void waitForSetup(Frame frame) {
        while(!frame.setupFinished()) {
            try {
                Thread.sleep(10);
            } catch(InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
