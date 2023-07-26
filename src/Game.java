import java.util.*;
import java.util.Random;
public class Game extends Thread {
    private boolean gameOver;
    private int[][] gameBoard;
    private List<Player> leadingPlayers;
    private Player player;
    private int currScore;
    private int snakeDirection;
    private Queue<int[]> snakeSegments;

    //kolekcja listenerow

    public Game() {
        gameOver = false;
        currScore = 0;

        leadingPlayers = new LinkedList<>();
        snakeSegments = new LinkedList<>();
        snakeListeners = new LinkedList<>();

        initBoard();
        initSnake();

        start();
    }

    @Override
    public void run() {
        while(!gameOver) {
            synchronized (this) {
                try {
                    if(snakeDirection == 0) {
                        this.wait();
                    }
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }

            tick();

            try {
                Thread.sleep(100);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

    private void gameOver() {
        this.gameOver = true;
        //TODO: zapis binarny tutaj, moze pytac czy jeszcze raz, wtedy czy jest nowy nickname;
    }

    private void tick() {
        moveSnake();
        if(!checkForFruit()) {
            currScore++;
            spawnNewFruit();
        }
    }

    //TODO: brakuje tego illegal move o 180, tutaj jak dasz cos takiego to jest traktowane jako kolizja ze soba
    //to gdzies gdzie nasluchuje keypressa, jezeli nowy keypress 180 stopni od starego to nie zmieniaj i tyle chyba
    private void moveSnake() {
        boolean ateFruit = false;
        int[] head = ((LinkedList<int[]>) snakeSegments).getLast();
        int[] newHead = new int[2];

        switch(snakeDirection) {
            //east
            case 1: {
                newHead = new int[]{head[0], head[1] + 1};
                break;
            }

            //south
            case 2: {
                newHead = new int[] {head[0] + 1, head[1]};
                break;
            }

            //west
            case 3: {
                newHead = new int[] {head[0], head[1] - 1};
                break;
            }

            //north
            case 4: {
                newHead = new int[] {head[0] - 1, head[1]};
                break;
            }
            default:
                break;
        }

        //kolizja
        if(wallCollision(newHead) || snakeCollision(newHead)) {
            gameOver();
            return;
        }

        if(gameBoard[newHead[0]][newHead[1]] == 9)
            ateFruit = true;

        gameBoard[head[0]][head[1]] = 2;
        gameBoard[newHead[0]][newHead[1]] = 1;

        snakeSegments.add(newHead);

        fireBoardUpdated();

        if(!ateFruit) {
            int tail[] = snakeSegments.poll();
            gameBoard[tail[0]][tail[1]] = 0;
        }
    }

    private List<SnakeListener> snakeListeners;

    public void addSnakeListener(SnakeListener sl){
        this.snakeListeners.add(sl);
    }

    private ScoreListener sl;
    public void addScoreListener(ScoreListener sl) {this.sl = sl;}


    private void fireBoardUpdated(){
        for(SnakeListener sl : snakeListeners)
            sl.snakeMoved(new SnakeEvent(this));
    }

    public synchronized void setSnakeDirection(int newDirection) {
        if(snakeDirection == 0) {
            this.notify();
        }
        if(directionChangeAllowed(newDirection)) {
            this.snakeDirection = newDirection; //czy to tez nie musi byc synchronized?
        }
    }

    private boolean directionChangeAllowed(int newDirection) {
        switch(snakeDirection) {
            case 1: {
                if(newDirection == 3)
                    return false;
                break;
            }
            case 2: {
                if(newDirection == 4)
                    return false;
                break;
            }
            case 3: {
                if(newDirection == 1)
                    return false;
                break;
            }
            case 4: {
                if(newDirection == 2)
                    return false;
                break;
            }
            default: break;
        }
        return true;
    }
    private boolean snakeCollision(int[] newHead) {
        return (gameBoard[newHead[0]][newHead[1]] == 1 || gameBoard[newHead[0]][newHead[1]] == 2);
    }

    private boolean wallCollision(int[] newHead) {
        if(newHead[0] < 0 || newHead[1] < 0)
            return true;
        if(newHead[0] > 24 || newHead[1] > 15)
            return true;
        return false;
    }

    private void initSnake() {
        snakeDirection = 0;
        //tyl
        snakeSegments.add(new int[] {12, 4});
        //glowa
        snakeSegments.add(new int[] {12, 5});
    }

    private void initBoard() {
        gameBoard = new int[25][16];

        gameBoard[12][4] = 2;
        gameBoard[12][5] = 1;

        gameBoard[12][10] = 9;
    }

    private boolean checkForFruit() {
        for (int[] row : gameBoard) {
            for (int cell : row)
                if(cell == 9)
                    return true;
        }
        return false;
    }

    private void spawnNewFruit() {
        Random random = new Random();
        int row = random.nextInt(25);
        int col = random.nextInt(16);
        while(gameBoard[row][col] == 1 || gameBoard[row][col] == 2) {
            row = random.nextInt(25);
            col = random.nextInt(16);
        }
        gameBoard[row][col] = 9;
    }
}