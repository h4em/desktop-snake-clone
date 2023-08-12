import java.awt.event.KeyEvent;

public
    class Game
    extends Thread
    implements UserActionListener {
    private Snake snake;
    private FileManager fileManager;
    private FruitHandler fruitHandler;
    private int score;
    private int highscore;
    private boolean gameOver;

    public Game() {
        fileManager = new FileManager();
        highscore = fileManager.readScore();
        //dziala

        snake = new Snake(12, 10);

        start();
    }

    @Override
    public void run() {
        while(!gameOver) {
            while(snake.isAlive()) {
                //nic
            }
            //fireSnakeCrashed();
            //czekaj na response i wtedy albo terminate albo nowy snake;
        }
        saveScoreIfNewHighscore();
        //fireApplicationEnd?
    }

    @Override
    public void keyPressed(KeyEvent e) {

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

    private void saveScoreIfNewHighscore() {
        if(highscore > score)
            fileManager.saveScore(highscore);
    }

    //TODO: te voidy?
    private GameStatusListener gameStatusListener;
    public void setGameStatusListener(GameStatusListener gsl) {
        gameStatusListener = gsl;
    }
    private void fireGameEnded() {gameStatusListener.gameEnded();};

    //TODO: te inty???
    private ScoreListener scoreListener;
    public void setScoreListener(ScoreListener sl) {this.scoreListener = sl;}
    private void fireScoreUpdated(int score){
        scoreListener.scoreUpdated(score);
    }
    private void fireHighScoreUpdated(int score) {scoreListener.highscoreUpdated(score);}
}