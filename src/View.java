import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class View extends JFrame {
    public View(Game game) {
        this.setTitle("S28546Projekt04");
        this.setVisible(true);
        this.setSize(new Dimension(1280,960));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setFocusable(true);

        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_RIGHT) {
                    game.setSnakeDirection(1);
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    game.setSnakeDirection(2);
                } else if (keyCode == KeyEvent.VK_LEFT) {
                    game.setSnakeDirection(3);
                } else if (keyCode == KeyEvent.VK_UP) {
                    game.setSnakeDirection(4);
                }
            }
        };

        //musi byc inaczej nie dzialalo, jakby jtable pochlanial kazdy input
        FocusListener focusListener = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                View.this.addKeyListener(keyAdapter);
            }

            @Override
            public void focusLost(FocusEvent e) {
                View.this.removeKeyListener(keyAdapter);
            }
        };

        this.addFocusListener(focusListener);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(new GamePanel(game), BorderLayout.CENTER);
        mainPanel.add(new LeaderboardPanel(), BorderLayout.EAST); //tutaj playersow

        //jeszcze popup z playerem;
        this.getContentPane().add(mainPanel);
    }
}

//obecny gracz nickname
//obecne punkty to moze byc po lewej nad tabela
//leaderboardsy po prawej lista -> czy to tez nie bedzie tabela?
//glowna tabela gry
