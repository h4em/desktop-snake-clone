import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import javax.swing.*;

public
    class Frame
    extends JFrame
    implements GameEventListener {
    private GameTable gameTable;
    private GameTableViewModel gameTableModel;
    private ScorePanel scorePanel;
    private GameOverDialog gameOverDialog;
    public Frame() {
        SwingUtilities.invokeLater(() -> {
            setTitle("Snake");
            setFocusable(true);
            setResizable(false);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

            setScorePanel();
            setGameTable();

            setKeyListener();

            validate();
            pack();
            centerOnScreen();
            setVisible(true);

            fireSetupFinished();
        });
    }

    private void setScorePanel() {
        scorePanel = new ScorePanel();
        getContentPane().add(scorePanel);
    }

    private void setGameTable() {
        gameTableModel = new GameTableViewModel();
        gameTable = new GameTable(gameTableModel);
        gameTableModel.addTableModelListener(gameTable);

        getContentPane().add(gameTable);
    }

    public GameTable getGameTable() {
        return gameTable;
    }

    private void centerOnScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screenSize.width - this.getWidth()) / 2;
        int centerY = (screenSize.height - this.getHeight()) / 2;
        setLocation(centerX, centerY - 30);
    }

    private void setKeyListener() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                fireKeyPressed(e);
            }
        });
    }

    @Override
    public void gameEnded() {
        SwingUtilities.invokeLater(() -> { dispose();});
    }

    @Override
    public void snakeCrashed() {
        SwingUtilities.invokeLater(() -> {
            gameOverDialog = new GameOverDialog(Frame.this);
            gameOverDialog.setUserActionListener(userActionListener);
        });
    }

    @Override
    public void scoreUpdated(GameEvent e) {
        String newScore = (String) e.getSource();
        SwingUtilities.invokeLater(() -> scorePanel.setScore(newScore));
    }

    @Override
    public void highscoreUpdated(GameEvent e) {
        String newScore = (String) e.getSource();
        SwingUtilities.invokeLater(() -> scorePanel.setHighscore(newScore));
    }

    private SetupListener setupListener;
    public void setSetupListener(SetupListener setupListener) {
        this.setupListener = setupListener;
    }

    private void fireSetupFinished() {
        setupListener.setupFinished();
    }

    private UserActionListener userActionListener;

    public void setUserActionListener(UserActionListener userActionListener) {
        this.userActionListener = userActionListener;
    }

    private void fireKeyPressed(KeyEvent e) {
        userActionListener.keyPressed(e);
    }
}

