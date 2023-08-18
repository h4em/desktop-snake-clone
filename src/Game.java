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
    private boolean setupFinished;

    public boolean setupFinished() {
        return setupFinished;
    }

    private void saveScoreIfNewHighscore() {
        if(highscore > score)
            fileManager.saveScore(highscore);
    }

    public Game() {
        fileManager = new FileManager();
        highscore = fileManager.readScore();

        gameBoard = new Gameboard();
        snake = new Snake();

        init();

        setupFinished = true;

        start();
    }

    private void init() {
        Field snakeStartField = new Field(12, 5);
        Field fruitStartField = new Field(12, 10);

        snake.init(snakeStartField);
        gameBoard.init(snakeStartField, fruitStartField);
    }

    @Override
    public void run() {
        while(!gameOver) {
            while(snake.isAlive()) {
                tick();
                delay(90);
            }
            fireSnakeCrashed();
            hold(); //notify na przyciskach
        }
        //saveScoreIfNewHighscore();
        //fireApplicationEnd?
    }

    private void tick() {
        if(!snake.hasStarted()) {
            hold();
        }

        snake.move();

        if(snake.hasCollided())
            return;

        //TODO: tu jest niby ten duzy gap ze sie trzeba domyslic ale chyba huj
        if(fruitEaten()) {
            score++;
            spawnNewFruit();
            fireScoreUpdated();
        } else {
            gameBoard.setField(snake.getTail(), FieldFlag.FREE);
            snake.removeTail();
        }
        gameBoard.setField(snake.getHead(), FieldFlag.SNAKE);
    }

    private synchronized void hold() {
        try {
            this.wait();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void proceed() {
        this.notify();
    }

    private boolean isOnHold() {
        return getState() == State.WAITING;
    }

    private void delay(long duration) {
        try {
            Thread.sleep(duration);
        } catch(InterruptedException e) {
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

    //TODO: jest wogle jakis bug ze dwa razy sie klika?
    @Override
    public void keyPressed(KeyEvent e) {
        if(isOnHold())
            proceed();

        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP -> snake.setDirection(Direction.UP);
            case KeyEvent.VK_DOWN -> snake.setDirection(Direction.DOWN);
            case KeyEvent.VK_LEFT -> snake.setDirection(Direction.LEFT);
            case KeyEvent.VK_RIGHT -> snake.setDirection(Direction.RIGHT);
        }
    }

    //TODO: tu zawsze gra jest na holdzie wiec notify na obu przyciskach
    @Override
    public void retryButtonPressed() {
        System.out.println("dupa");
    }

    @Override
    public void quitButtonPressed() {
        System.out.println("quit");
    }

    private GameEventListener gameEventListener;

    public void setGameEventListener(GameEventListener gameEventListener) { this.gameEventListener = gameEventListener; }

    private void fireScoreUpdated() {
        gameEventListener.scoreUpdated(new GameEvent(Integer.toString(score)));
    }

    private void fireHighScoreUpdated() { gameEventListener.highscoreUpdated(new GameEvent(Integer.toString(highscore))); }

    private void fireSnakeCrashed() {
        gameEventListener.snakeCrashed();
    }

    private void fireGameEnded() {
        gameEventListener.gameEnded();
    }

    public Gameboard getGameBoard() {
        return gameBoard;
    }
}