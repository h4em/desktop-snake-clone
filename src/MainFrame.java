import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {

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
        tableModel.addTableModelListener(gameTable); //!!!
        getContentPane().add(gameTable);
    }

    //TODO: troche bez sensu to za kazdym razem ustawiac
    public void setKeyAdapter(Movable snake) {
        keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_RIGHT:
                    snake.setDirection(1);
                    break;
                case KeyEvent.VK_DOWN:
                    snake.setDirection(2);
                    break;
                case KeyEvent.VK_LEFT:
                    snake.setDirection(3);
                    break;
                case KeyEvent.VK_UP:
                    snake.setDirection(4);
                    break;
            }
            }
        };
    }

    //TODO: tam bylo getContentPane add new GamePanel BORDERLAYOUT CENTER, czy to sie nie zjebie przy packu()?
    public MainFrame() {
        this.setTitle("desktop-snake-clone");
        this.setFocusable(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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

    private void centerOnScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screenSize.width - this.getWidth()) / 2;
        int centerY = (screenSize.height - this.getHeight()) / 2;
        this.setLocation(centerX, centerY - 30);
    }
}

