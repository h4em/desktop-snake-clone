import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    public GamePanel(GameInterface game) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel scorePanel = new ScorePanel();
        game.setPlayerListener((ScoreListener) scorePanel);

        JTable gameTable = new GameTable(new SnakeGameModel());
        game.setCellListener((CellListener) gameTable);

        this.add(scorePanel);

        this.add(gameTable);
    }
}
