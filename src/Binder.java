import javax.swing.*;

public class Binder {
    public static void BindComponents(Game game, Frame frame) {
        waitForSetup(frame);
        bindGameOverDialogButtons(game, frame);
        bindGameTable(game, frame);
    }

    private static void bindGameTable(Game game, Frame frame) {

    }

    private static void bindGameOverDialogButtons(Game game, Frame frame) {
        frame.setUserActionListener(game);
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
