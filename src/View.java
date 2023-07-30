import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;

public class View extends JFrame implements GameStatusListener {
    private GameInterface game;
    public View(GameInterface game) {
        this.setTitle("desktop-snake-clone");
        this.setVisible(true);
        this.setFocusable(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.game = game;

        game.setGameStatusListener(this);

        this.getContentPane().add(new GamePanel(game), BorderLayout.CENTER);

        //TODO: to jest za bardzo crude, refactor
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

        this.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                  View.this.addKeyListener(keyAdapter);
                }

                @Override
                public void focusLost(FocusEvent e) {
                  View.this.removeKeyListener(keyAdapter);
                }
            }
        );

        this.pack();

        this.centerOnScreen();
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

