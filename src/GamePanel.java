import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    public GamePanel(GameInterface game) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //TODO: jakos ten casting obejsc zeby ladniej bylo?
        JPanel scorePanel = new ScorePanel();
        game.setScoreListener((ScoreListener) scorePanel);

        //TODO: to samo
        JTable gameTable = new GameTable(new SnakeGameModel());
        game.setCellListener((CellListener) gameTable);

        this.add(scorePanel);
        this.add(gameTable);
    }
}
