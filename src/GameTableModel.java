import javax.swing.table.AbstractTableModel;

public
    class GameTableModel
    extends AbstractTableModel {
    private int[][] gameBoardData;

    //jak tu sie to jakos zainituje tak zeby nie bylo new keyword to nie bedzie duplikacji wtedy.
    public GameTableModel() {
        gameBoardData = new int[Game.gameBoardBoundX][Game.gameBoardBoundY];
    }

    //tu ten model bedzie musial chyba wystawiac jakas metode zeby sie zbindowac z actual modelem, to jest viewmodel tak naprawde.


    //cos takiego?
    public void setData(int[][] data) {
        gameBoardData = data;
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
}