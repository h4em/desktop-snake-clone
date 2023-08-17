import java.awt.*;
import java.awt.event.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

public
    class Frame
    extends JFrame
    implements GameEventListener {
    private GameTable gameTable;
    private GameTableModel gameTableModel;
    private ScorePanel scorePanel;
    private GameOverDialog gameOverDialog;
    private KeyAdapter keyAdapter;
    private UserActionListener userActionListener;

    //TODO: settupable interface?
    public boolean setupFinished() {
        if(gameTable == null)
            return false;
        if(gameTableModel == null)
            return false;
        return true;
    }

    public Frame() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                setTitle("Snake");
                setFocusable(true);
                setResizable(false);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

                setScorePanel();
                setGameTable();

                setKeyListener();
                setFocusListener();

                validate();
                pack();
                centerOnScreen();
                setVisible(true);
            }
        });
    }

    //TODO: czy to jest wogole potrzebne?
    private void setFocusListener() {
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                Frame.this.addKeyListener(keyAdapter);
            }

            @Override
            public void focusLost(FocusEvent e) {
                Frame.this.removeKeyListener(keyAdapter);
            }
        });
    }

    @Override
    public void gameEnded() {
        ;
    }

    @Override
    public void snakeCrashed() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gameOverDialog = new GameOverDialog(Frame.this);
                gameOverDialog.setUserActionListener(userActionListener);
            }
        });
    }

    @Override
    public void scoreUpdated(GameEvent e) {
        String newScore = (String) e.getSource();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                scorePanel.setScore(newScore);
            }
        });
    }

    @Override
    public void highscoreUpdated(GameEvent e) {
        String newScore = (String) e.getSource();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                scorePanel.setHighscore(newScore);
            }
        });
    }

    private void setGameTable() {
        gameTableModel = new GameTableModel();
        gameTable = new GameTable(gameTableModel);
        gameTableModel.addTableModelListener(gameTable);

        getContentPane().add(gameTable);
    }

    private void setScorePanel() {
        scorePanel = new ScorePanel();
        getContentPane().add(scorePanel);
    }

    private void setKeyListener() {
        keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                userActionListener.keyPressed(e);
            }
        };
        addKeyListener(keyAdapter);
    }

    public void setUserActionListener(UserActionListener userActionListener) {
        this.userActionListener = userActionListener;
    }

    private void centerOnScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screenSize.width - this.getWidth()) / 2;
        int centerY = (screenSize.height - this.getHeight()) / 2;
        setLocation(centerX, centerY - 30);
    }

    public GameTable getGameTable() {
        return gameTable;
    }
}

