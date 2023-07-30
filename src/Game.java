import java.util.Random;

public class Game extends Thread implements GameInterface {
    private boolean gameOver;
    private int[][] gameBoard;
    private int score;
    private Snake snake;
    public Game() {
        gameOver = false;
        score = 0;

        initBoard();

        snake = new Snake();
        snake.initSnake(new int[] {12, 5}, new int[] {12,4});
        snake.setBoardBounds(gameBoard.length, gameBoard[0].length);

        start();
    }

    @Override
    public void run() {
        while(!gameOver) {
            synchronized (this) {
                try {
                    if(!snake.hasStarted()) {
                        this.wait();
                    }
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }

            tick();

            try {
                Thread.sleep(90);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void tick() {
        if(snake.move()) {
            takeField(snake.getHead());
            fireCellUpdated(makeCell(snake.getHead()));
        } else {
            gameOver();
        }

        if(!checkForFruit()) {
            spawnNewFruit();
            fireCellUpdated(makeCell(getFieldOfFruit()));
            fireScoreUpdated(++score);
        } else {
            freeField(snake.getTail());
            fireCellUpdated(makeCell(snake.getTail()));
            snake.removeTail();
        }
    }

    private void gameOver() {
        this.gameOver = true;

        FileManager fm = new FileManager();

        fireGameEnded();
    }

    @Override
    public synchronized void setSnakeDirection(int direction) {
        if(snake.directionChangeAllowed(direction)) {
            if(!snake.hasStarted()) {
                this.notify();
            }
            snake.setDirection(direction);
        }
    }

    private boolean checkForFruit() {
        for (int[] row : gameBoard) {
            for (int cell : row)
                if(cell == 9)
                    return true;
        }
        return false;
    }

    private int[] getFieldOfFruit() {
        int[] res = new int[2];
        for(int i = 0; i < gameBoard.length; i++) {
            for(int j = 0; j < gameBoard[i].length; j++) {
                if(gameBoard[i][j] == 9) {
                    res[0] = i;
                    res[1] = j;
                }
            }
        }
        return res;
    }

    private void spawnNewFruit() {
        Random random = new Random();
        int row = random.nextInt(gameBoard.length);
        int col = random.nextInt(gameBoard[0].length);
        while(gameBoard[row][col] == 1) {
            row = random.nextInt(gameBoard.length);
            col = random.nextInt(gameBoard[0].length);
        }
        gameBoard[row][col] = 9;
    }

    private void takeField(int[] field) {
        gameBoard[field[0]][field[1]] = 1;
    }

    private void freeField(int[] field) {
        gameBoard[field[0]][field[1]] = 0;
    }

    private void initBoard() {
        gameBoard = new int[25][16];

        gameBoard[12][4] = 1;
        gameBoard[12][5] = 1;

        gameBoard[12][10] = 9;
    }

    private GameStatusListener gameStatusListener;

    @Override
    public void setGameStatusListener(GameStatusListener gl) {
        this.gameStatusListener = gl;
    }

    private void fireGameEnded() {
        gameStatusListener.gameEnded();
    }

    private int[] makeCell(int[] field) {
        int[] res = new int[3];
        res[0] = field[0];
        res[1] = field[1];
        res[2] = gameBoard[field[0]][field[1]];
        return res;
    }

    private CellListener cellListener;
    @Override
    public void setCellListener(CellListener cl) {
        this.cellListener = cl;

        fireCellUpdated(makeCell(new int[] {12,4}));
        fireCellUpdated(makeCell(new int[] {12,5}));
        fireCellUpdated(makeCell(new int[] {12,10}));
    }

    private void fireCellUpdated(int[] cell) {
        cellListener.cellValueUpdated(new CellEvent(cell));
    }

    private ScoreListener playerListener;
    @Override
    public void setPlayerListener(ScoreListener pl) {this.playerListener = pl;}
    private void fireScoreUpdated(int score){
        playerListener.scoreUpdated(score);
    }
}