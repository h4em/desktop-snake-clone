import javax.swing.*;

public class GamePanel extends JPanel {
    public GamePanel(GameInterface game) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //TODO: jakos ten casting obejsc zeby ladniej bylo?
        JPanel scorePanel = new ScorePanel();
        game.setScoreListener((ScoreListener) scorePanel);

        //TODO: to samo
        JTable gameTable = new GameTable(new SnakeGameTableModel());
        game.setCellListener((CellListener) gameTable);

        this.add(scorePanel);

        //TODO: Jtable jest komponentem gui, to model ma nasluchiwac na zmiany danych
        this.add(gameTable);
    }
}
