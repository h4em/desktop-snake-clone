import javax.swing.table.DefaultTableModel;
public
    class GameTableModel
    extends DefaultTableModel
    implements DataModelListener {
    private int[][] gameBoardData;

    @Override
    public void dataInitialised(DataModelEvent e) {
        gameBoardData = (int[][]) e.getSource();
        fireTableDataChanged();
    }

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
}