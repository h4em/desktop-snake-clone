import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame implements GameStatusListener {
    private GameInterface game;

    private JTable gameTable;
    private JPanel scorePanel;

    private KeyAdapter keyAdapter;

    public void show() {
        pack();
        setVisible(true);
        centerOnScreen();
    }

    public void setScorePanel(JPanel scorePanel) {
        this.scorePanel = scorePanel;
        getContentPane().add(scorePanel);
    }

    public void setGameTable(AbstractTableModel tableModel) {
        gameTable = new GameTable(tableModel);
        getContentPane().add(gameTable);
    }


    //TODO: tam bylo getContentPane add new GamePanel BORDERLAYOUT CENTER, czy to sie nie zjebie przy packu()?
    public MainFrame(GameInterface game) {
        this.setTitle("desktop-snake-clone");
        this.setFocusable(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setKeyAdapter();

        this.game = game;

        game.setGameStatusListener(this);

        this.getContentPane().add(new GamePanel(game), BorderLayout.CENTER);

        setKeyAdapter();

        this.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                  MainFrame.this.addKeyListener(keyAdapter);
                }

                @Override
                public void focusLost(FocusEvent e) {
                  MainFrame.this.removeKeyListener(keyAdapter);
                }
            }
        );
    }

    private void setKeyAdapter() {
        keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_RIGHT:
                        game.setSnakeDirection(1);
                        break;
                    case KeyEvent.VK_DOWN:
                        game.setSnakeDirection(2);
                        break;
                    case KeyEvent.VK_LEFT:
                        game.setSnakeDirection(3);
                        break;
                    case KeyEvent.VK_UP:
                        game.setSnakeDirection(4);
                        break;
                }
            }
        };
    }

    private void centerOnScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screenSize.width - this.getWidth()) / 2;
        int centerY = (screenSize.height - this.getHeight()) / 2;
        this.setLocation(centerX, centerY - 30);
    }

    @Override
    public void gameEnded() {
        ;
    }
}

