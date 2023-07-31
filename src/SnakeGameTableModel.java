import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class SnakeGameTableModel extends AbstractTableModel implements TableModelListener {
    private int[][] gameBoardData;

    public SnakeGameTableModel() {
        initBoard(25, 16);
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        ;
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
        for(int i = 0; i < gameBoardData.length; i++) {
            for(int j = 0; j < gameBoardData[i].length; j++) {
                gameBoardData[i][j] = 0;
            }
        }
    }

    private void initBoard(int rowNumber, int colNumber) {
        gameBoardData = new int[rowNumber][colNumber];
    }
}