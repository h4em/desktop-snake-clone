import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Queue;
import java.util.Random;

public
    class Game
    extends Thread
    implements UserActionListener {
    private Snake snake;
    private Point fruit;
    private FileManager fileManager;
    private int score;
    private int highscore;
    private boolean gameOver;

    private void saveScoreIfNewHighscore() {
        if(fileManager != null)
            if(highscore > score)
                fileManager.saveScore(highscore);
    }



    //TODO: JEDNA GRA, wiele wezy.

    //TODO:? jak bedziesz firerowal przed ustawieniem listenera to sie wyjebie wszystko.
    public Game() {
        fileManager = new FileManager();
        highscore = fileManager.readScore();
        System.out.println(highscore);
        snake = new Snake(12, 10);

        //highscore = fileManager.readHighscore();
        //fireHighScoreUpdated(highscore);

        score = snake.getLength();
        //fireScoreUpdated(score);
        //setSnakeField(snake.getHead());

        fruit = new Point(12, 12);
        //setFruitField(fruit);

        start();
    }
    //thread .isAlive();

    @Override
    public void run() {
        while(!gameOver) {
            while(snake.isAlive()) {
                //snake.tryMoving();
                //snake.wait();
            }
            //fireSnakeCrashed();
            //czekaj na response i wtedy albo terminate albo nowy snake;
        }
        saveScoreIfNewHighscore();
        //fireApplicationEnd?
    }
    private void spawnNewFruit() {
        Random random = new Random();
        int row = random.nextInt(25);
        int col = random.nextInt(16);
        Point p = new Point(row, col);

        Queue<Point> snakeSegments = snake.getSnakeSegments();

        //ten .equals jest ciekawy
        while(p.equals(snakeSegments.poll())) {
            row = random.nextInt(25);
            col = random.nextInt(16);
            p = new Point(row, col);
        }

        setFruitField(p);
    }

    //TODO: redundant, no?

    private void setSnakeField(Point p) {
        int x = (int) p.getX();
        int y = (int) p.getY();
        fireCellUpdated(new Cell(1, x, y));
    }

    private void setFruitField(Point p) {
        int x = (int) p.getX();
        int y = (int) p.getY();
        fireCellUpdated(new Cell(9, x, y));
    }

    private void freeField(Point p) {
        int x = (int) p.getX();
        int y = (int) p.getY();
        fireCellUpdated(new Cell(0, x, y));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        ;
    }

    @Override
    public void retryButtonPressed() {
        System.out.println("huj");
    }

    @Override
    public void quitButtonPressed() {
        ;
    }

    @Override
    public void endingDialogClosed() {
        ;
    }

    //TODO: te voidy?
    private GameStatusListener gameStatusListener;
    public void setGameStatusListener(GameStatusListener gsl) {
        gameStatusListener = gsl;
    }
    private void fireGameEnded() {gameStatusListener.gameEnded();};

    private CellListener cellListener;
    public void setCellListener(CellListener cl) {this.cellListener = cl;}
    private void fireCellUpdated(Cell cell){ cellListener.cellChanged(new CellEvent(cell));}

    //TODO: te inty???
    private ScoreListener scoreListener;
    public void setScoreListener(ScoreListener sl) {this.scoreListener = sl;}
    private void fireScoreUpdated(int score){
        scoreListener.scoreUpdated(score);
    }
    private void fireHighScoreUpdated(int score) {scoreListener.highscoreUpdated(score);}
}