import java.awt.event.KeyEvent;
import java.util.*;

public
    class Game
    extends Thread
    implements UserActionListener {
    private Snake snake;
    private Gameboard gameBoard;
    private FileManager fileManager;
    private int score;
    private int highscore;
    private boolean gameOver;
    public static final int gameBoardBoundX = 25;
    public static final int gameBoardBoundY = 16;

    public Game() {
        fileManager = new FileManager();
        highscore = fileManager.readScore();

        gameBoard = new Gameboard(gameBoardBoundX, gameBoardBoundY);
        snake = new Snake();

        initGameboard();
    }

    private void initGameboard() {
        gameBoard.freshBoard();

        Field snakeStartField = new Field(12, 10);
        snake.addSnakeSegment(snakeStartField);
        snake.setHead(snakeStartField);
        snake.setTail(snakeStartField);
        gameBoard.setField(snakeStartField, FieldFlag.SNAKE);

        Field fruitStartField = new Field(12, 12);
        gameBoard.setField(fruitStartField, FieldFlag.FRUIT);
    }

    @Override
    public void run() {
        while(!gameOver) {
            while(snake.isAlive()) {
                tick();
                delay(90);
            }
            //fireSnakeCrashed();
            //czekaj na response i wtedy albo terminate albo nowy snake;
        }
        saveScoreIfNewHighscore();
        //fireApplicationEnd?
    }

    private void delay(long duration) {
        try {
            Thread.sleep(duration);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void tick() {
        snake.move();

        if(snake.hasCollided())
            return;

        //TODO: tu jest niby ten duzy gap ze sie trzeba domyslic ale chyba huj
        if(fruitEaten()) {
            score++;
            spawnNewFruit();
        } else {
            gameBoard.setField(snake.getTail(), FieldFlag.FREE);
            snake.removeTail();
        }
        gameBoard.setField(snake.getHead(), FieldFlag.SNAKE);
    }

    private boolean fruitEaten() {
        Field fruitField = gameBoard.getFruitField();
        Field snakeHead = snake.getHead();
        return snakeHead == fruitField;
    }

    private void spawnNewFruit() {
        ArrayList<Field> freeFields = (ArrayList<Field>) gameBoard.getFreeFields();
        int r = getRandomInt(freeFields.size());
        gameBoard.setField(freeFields.get(r), FieldFlag.FRUIT);
    }

    private int getRandomInt(int bound) {
        Random random = new Random();
        return random.nextInt(bound);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP -> snake.setDirection(Direction.UP);
            case KeyEvent.VK_DOWN -> snake.setDirection(Direction.DOWN);
            case KeyEvent.VK_LEFT -> snake.setDirection(Direction.LEFT);
            case KeyEvent.VK_RIGHT -> snake.setDirection(Direction.RIGHT);
        }
    }

    @Override
    public void retryButtonPressed() {
        System.out.println("retry");
    }

    @Override
    public void quitButtonPressed() {
        System.out.println("quit");
    }

    @Override
    public void endingDialogClosed() {
        System.out.println("dialogClosed");
    }

    private void saveScoreIfNewHighscore() {
        if(highscore > score)
            fileManager.saveScore(highscore);
    }

    //TODO: te inty???
    private ScoreListener scoreListener;
    public void setScoreListener(ScoreListener sl) {this.scoreListener = sl;}
    private void fireScoreUpdated(int score){
        scoreListener.scoreUpdated(score);
    }
    private void fireHighScoreUpdated(int score) {scoreListener.highscoreUpdated(score);}
}