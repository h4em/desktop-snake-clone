import java.awt.event.KeyEvent;

public interface UserActionListener {
    void keyPressed(KeyEvent e);
    void retryButtonPressed();
    void quitButtonPressed();
    void endingDialogClosed();
}
