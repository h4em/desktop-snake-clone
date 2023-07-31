import javax.swing.*;

public class Main {
    private static final int gameBoardBoundX = 25;
    private static final int gameBoardBoundY = 16;

    private int highscore;

    //fruit handler? dobra mysl ino

    public Main() {
        SnakeGameTableModel snakeGameTableModel = new SnakeGameTableModel(gameBoardBoundX, gameBoardBoundY);

        Snake snake = new Snake(snakeGameTableModel);


        FileManager fm = new FileManager();
        highscore = fm.readHighscore();

        FruitHandler fh = new FruitHandler(gameBoardBoundX, gameBoardBoundY);




    }

    public static void main(String args[]) {
        Main main = new Main();
        MainFrame frame = new MainFrame();

        //TODO: gdzie invoke later?

        //TODO: to jest suabe chyba
        JPanel scorePanel = new ScorePanel();
        //TODO: gowno?
        ((ScoreListener) scorePanel).scoreUpdated(main.highscore);
        //sub score do snakea
        frame.setScorePanel(scorePanel);

        SnakeGameTableModel snakeGameTableModel = new SnakeGameTableModel(gameBoardBoundX, gameBoardBoundY);
        frame.setGameTable(snakeGameTableModel);

        frame.show();
    }
}