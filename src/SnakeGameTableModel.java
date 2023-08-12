import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;

public
    class SnakeGameTableModel
    extends AbstractTableModel
    implements GameEventListener {
    private int[][] gameBoardData;
    public static final int gameBoardBoundX = 25;
    public static final int gameBoardBoundY = 16;

    public SnakeGameTableModel() {
        gameBoardData = new int[gameBoardBoundX][gameBoardBoundY];
        gameEventListeners = new LinkedList<GameEventListener>();
    }

    @Override
    public void fieldTaken(int x, int y) {
        if(isFruit(gameBoardData[x][y]))
            fireFruitEaten();
        setValueAt(1, x, y);
    }

    @Override
    public void fruitSpawned(int x, int y) {
        if(isSnake(gameBoardData[x][y]))
            fireNewFruitRequest();
        else
            setValueAt(9, x, y);
    }

    private void fireNewFruitRequest() {
        for(GameEventListener gel : gameEventListeners) {
            gel.newFruitRequest();
        }
    }

    private void fireFruitEaten() {
        for(GameEventListener gel : gameEventListeners) {
            gel.fruitEaten();
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return gameBoardData[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        gameBoardData[rowIndex][columnIndex] = (int) aValue;
    }

    private boolean isFruit(int val) {
        return val == 9;
    }

    private boolean isSnake(int val) {
        return val == 1;
    }

    @Override
    public int getColumnCount() {
        return gameBoardBoundY;
    }

    @Override
    public int getRowCount() {
        return gameBoardBoundX;
    }

    private LinkedList<GameEventListener> gameEventListeners;

    public void addGameEventListener(GameEventListener gel){
        gameEventListeners.add(gel);
    }

    @Override
    public void fieldFreed(int x, int y) {
        ;
    }
    @Override
    public void fruitEaten() {
        ;
    }
    @Override
    public void newFruitRequest() {
        ;
    }
}