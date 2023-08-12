import java.util.Random;

public
    class FruitHandler
    implements GameEventListener{
    private void spawnNewFruit() {
        int x = generateRandomInt(SnakeGameTableModel.gameBoardBoundX);
        int y = generateRandomInt(SnakeGameTableModel.gameBoardBoundY);
        fireFruitSpawned(x, y);
    }

    private void fireFruitSpawned(int x, int y) {
        gameEventListener.fruitSpawned(x, y);
    }

    @Override
    public void fruitEaten() {
        spawnNewFruit();
    }

    @Override
    public void newFruitRequest() {
        spawnNewFruit();
    }

    private int generateRandomInt(int bound) {
        Random random = new Random();
        return random.nextInt(bound);
    }
    private GameEventListener gameEventListener;

    public void setGameEventListener(GameEventListener gameEventListener) {
        this.gameEventListener = gameEventListener;
    }

    @Override
    public void fruitSpawned(int x, int y) {
        ;
    }

    @Override
    public void fieldTaken(int x, int y) {
        ;
    }

    @Override
    public void fieldFreed(int x, int y) {
        ;
    }
}
