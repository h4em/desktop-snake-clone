import javax.swing.table.AbstractTableModel;

public class SnakeGameModel extends AbstractTableModel {
    int[][] gameBoard;

    public SnakeGameModel(int[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return Integer.toString(gameBoard[rowIndex][columnIndex]);
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
