import java.util.ArrayList;
import java.util.List;

public final class Gameboard {
    private int[][] gameBoard;
    public static final int gameBoardBoundX = 25;
    public static final int gameBoardBoundY = 16;
    public Gameboard() {
        gameBoard = new int[gameBoardBoundX][gameBoardBoundY];
    }

    public void init(Field snakeStartField, Field fruitStartField) {
        setField(snakeStartField, FieldFlag.SNAKE);
        setField(fruitStartField, FieldFlag.FRUIT);
    }

    public List<Field> getFreeFields() {
        List<Field> result = new ArrayList<Field>();

        for(int i = 0; i < gameBoard.length; i++) {
            for(int j = 0; j < gameBoard[i].length; j++) {
                int element = gameBoard[i][j];
                if(isFree(element))
                    result.add(new Field(i, j));
            }
        }

        return result;
    }

    public Field getFruitField() {
        for(int i = 0; i < gameBoard.length; i++) {
            for(int j = 0; j < gameBoard[i].length; j++) {
                int element = gameBoard[i][j];
                if(isFruit(element))
                    return new Field(i, j);
            }
        }
        return null;
    }

    public void setField(Field field, FieldFlag flag) {
        int x = field.x;
        int y = field.y;

        switch(flag) {
            case SNAKE -> gameBoard[x][y] = 1;
            case FRUIT -> gameBoard[x][y] = 9;
            case FREE -> gameBoard[x][y] = 0;
        }


       if(dataModelListener != null)
            fireFieldChanged(field);
    }

    public void clearBoard() {
        for(int i = 0; i < gameBoardBoundX; i++) {
            for (int j = 0; j < gameBoardBoundY; j++) {
                gameBoard[i][j] = 0;
                if(dataModelListener != null)
                    fireFieldChanged(new Field(i, j));
            }
        }
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

    private boolean isFree(int value) {
        return value == 0;
    }

    private boolean isSnake(int value) {
        return value == 1;
    }

    private boolean isFruit(int value) {
        return value == 9;
    }

    private DataModelListener dataModelListener;
    public void setDataModelListener(DataModelListener dml) {
        dataModelListener = dml;
    }
    public void fireFieldChanged(Field field) {
        dataModelListener.fieldChanged(new DataModelEvent(field));
    }
}