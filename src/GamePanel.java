import event.GameEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements SnakeListener {
    private JTable gameTable;
    private JPanel playerDetailsPanel;
    private JPanel gameTablePanel;

    @Override
    public void snakeMoved(SnakeEvent snakeEvent) {
        this.revalidate();
        this.repaint();
    }

    public GamePanel(Game game) {
        this.setLayout(new GridBagLayout());
        //binding
        game.addSnakeListener(this);

        playerDetailsPanel = new PlayerDetailsPanel();
        gameTable = new GameTable(new SnakeGameModel(game.getGameBoard()));//? to chyba nie powinno tu byc

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        this.add(playerDetailsPanel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0; //kolumny sie nie resizuja
        gbc.weighty = 0;

        this.add(gameTable, gbc);
    }
}
