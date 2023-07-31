import javax.swing.table.AbstractTableModel;
import java.util.Arrays;

public class SnakeGameTableModel extends AbstractTableModel implements CellListener {
    private int[][] gameBoardData;

    public SnakeGameTableModel(int rowNumber, int colNumber) {
        gameBoardData = new int[rowNumber][colNumber];
    }

    @Override
    public void cellChanged(CellEvent e) {
        Cell cell = (Cell) e.getSource();
        setValueAt(cell.getValue(), cell.getX(), cell.getY());
        fireTableDataChanged();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return gameBoardData[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        gameBoardData[rowIndex][columnIndex] = (int) aValue;
    }

    @Override
    public int getColumnCount() {
        return gameBoardData[0].length;
    }

    @Override
    public int getRowCount() {
        return gameBoardData.length;
    }

    public void clearBoard() {
        Arrays.fill(gameBoardData, 0);
    }
}