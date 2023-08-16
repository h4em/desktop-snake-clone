import java.awt.*;
import java.awt.event.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

public
    class Frame
    extends JFrame {
    private GameTable gameTable;
    private GameTableModel gameTableModel;
    private ScorePanel scorePanel;
    private GameOverDialog gameOverDialog;
    private KeyAdapter keyAdapter;
    private UserActionListener userActionListener;

    public GameOverDialog getGameOverDialog() {
        return gameOverDialog;
    }

    public boolean setupFinished() {
        if(gameTable == null)
            return false;
        if(gameTableModel == null)
            return false;
        if(gameOverDialog == null)
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
                setGameOverDialog();

                setKeyListener();
                setFocusListener();

                validate();
                pack();
                centerOnScreen();
                setVisible(true);
            }
        });
    }

    public ScoreListener getScoreListener() {
        return scorePanel;
    }
    public GameEventListener getGameEventListener() {
        return null;
    }
    public GameStatusListener getGameStatusListener() {return gameOverDialog;}

    //TODO: czy nie slabe to jest?
    public void setUserActionListener(UserActionListener ual) {
        userActionListener = ual;
        gameOverDialog.setUserActionListener(ual);
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

    private void setKeyListener() {
        keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                userActionListener.keyPressed(e);
            }
        };
        addKeyListener(keyAdapter);
    }

    private void setGameOverDialog() {
        gameOverDialog = new GameOverDialog(this);
    }

    private void setScorePanel() {
        scorePanel = new ScorePanel();
        getContentPane().add(scorePanel);
    }

    private void setGameTable() {
        gameTableModel = new GameTableModel();
        gameTable = new GameTable(gameTableModel);
        getContentPane().add(gameTable);
    }

    private void centerOnScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screenSize.width - this.getWidth()) / 2;
        int centerY = (screenSize.height - this.getHeight()) / 2;
        setLocation(centerX, centerY - 30);
    }
}

