import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    public GamePanel(GameInterface game) {
        this.setLayout(new GridBagLayout());

        JPanel playerDetailsPanel = new ScorePanel();
        game.setPlayerListener((ScoreListener) playerDetailsPanel);

        JTable gameTable = new GameTable(new SnakeGameModel());
        game.setCellListener((CellListener) gameTable);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        this.add(playerDetailsPanel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0;
        gbc.weighty = 0;

        this.add(gameTable, gbc);
    }
}
