import javax.swing.table.DefaultTableModel;
public
    class GameTableViewModel
    extends DefaultTableModel
    implements DataModelListener {
    private int[][] gameBoardData;

    @Override
    public void fieldChanged(DataModelEvent e) {
        Field field = (Field) e.getSource();
        fireTableCellUpdated(field.x, field.y);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return gameBoardData[rowIndex][columnIndex];
    }
    @Override
    public int getRowCount() {
        return Gameboard.gameBoardBoundX;
    }
    @Override
    public int getColumnCount() {
        return Gameboard.gameBoardBoundY;
    }

    public void setGameBoardData(int[][] gameBoardData) {
        this.gameBoardData = gameBoardData;
    }
}