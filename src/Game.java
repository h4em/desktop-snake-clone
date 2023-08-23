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

    public Game() {
        fileManager = new FileManager();
        highscore = fileManager.readScore();

        gameBoard = new Gameboard();
        snake = new Snake();

        init();

        start();
    }

    private void init() {
        Field snakeStartField = new Field(12, 5);
        Field fruitStartField = new Field(12, 10);

        snake.init(snakeStartField);

        gameBoard.clearBoard();
        gameBoard.init(snakeStartField, fruitStartField);
    }

    @Override
    public void run() {
        while (!gameOver) {
            while(snake.isAlive()) {
                tick();
                delay(90);
            }
            fireSnakeCrashed();
            waitForUserAction();
        }
        saveScoreIfNewHighscore();
        fireGameEnded();
    }

    private void tick()  {
        if(!snake.hasStarted())
            waitForUserAction();

        snake.move();

        if(snake.collided())
            return;

        //TODO: duzy gap ze sie trzeba domyslec ocb
        if(fruitEaten()) {
            score++;
            fireScoreUpdated();

            spawnNewFruit();

            if(score > highscore) {
                highscore = score;
                fireHighScoreUpdated();
            }

        } else {
            gameBoard.setField(snake.getTail(), FieldFlag.FREE);
            snake.removeTail();
        }
        gameBoard.setField(snake.getHead(), FieldFlag.SNAKE);
    }

    private synchronized void waitForUserAction() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean isWaiting() {
        return getState() == State.WAITING;
    }

    private void delay(long duration)  {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean fruitEaten() {
        Field fruitField = gameBoard.getFruitField();
        Field snakeHead = snake.getHead();
        return snakeHead.equals(fruitField);
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
    public synchronized void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(!isArrowKey(keyCode))
            return;
        if(isWaiting())
            notify();

        switch (keyCode) {
            case KeyEvent.VK_UP -> snake.setDirection(Direction.UP);
            case KeyEvent.VK_DOWN -> snake.setDirection(Direction.DOWN);
            case KeyEvent.VK_LEFT -> snake.setDirection(Direction.LEFT);
            case KeyEvent.VK_RIGHT -> snake.setDirection(Direction.RIGHT);
        }
    }

    private boolean isArrowKey(int keyCode) {
        if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT)
            return true;
        return false;
    }

    @Override
    public synchronized void retryButtonPressed() {
        if(isWaiting())
            notify();
        restart();
    }

    private void restart() {
        score = 0;
        fireScoreUpdated();
        snake = new Snake();
        init();
    }

    private void saveScoreIfNewHighscore() {
        if(score >= highscore)
            fileManager.saveScore(score);
    }

    @Override
    public synchronized void quitButtonPressed() {
        if(isWaiting())
            notify();
        gameOver = true;
        fireGameEnded();
    }

    public Gameboard getGameBoard() {
        return gameBoard;
    }

    private GameEventListener gameEventListener;

    public void setGameEventListener(GameEventListener gameEventListener) { this.gameEventListener = gameEventListener; }

    private void fireScoreUpdated() {
        gameEventListener.scoreUpdated(new GameEvent(Integer.toString(score)));
    }

    public void fireHighScoreUpdated() {
        gameEventListener.highscoreUpdated(new GameEvent(Integer.toString(highscore)));
    }

    private void fireSnakeCrashed() {
        gameEventListener.snakeCrashed();
    }

    private void fireGameEnded() {
        gameEventListener.gameEnded();
    }
}