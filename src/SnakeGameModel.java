import javax.swing.table.AbstractTableModel;

public class SnakeGameModel extends AbstractTableModel {
    int[][] gameBoard;

    public SnakeGameModel() {
        this.gameBoard = new int[25][16];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return gameBoard[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        gameBoard[rowIndex][columnIndex] = (int) aValue;
    }

    @Override
    public int getColumnCount() {
        return 16;
    }

    @Override
    public int getRowCount() {
        return 25;
    }
}
