import javax.swing.*;
import javax.swing.table.*;

public class LeaderboardTable extends JTable {
    public LeaderboardTable(AbstractTableModel model) {
        this.setModel(model);
        this.setRowHeight(30);
        this.setEnabled(false);

        TableColumnModel columnModel = this.getColumnModel();
        int columnCount = columnModel.getColumnCount();

        for(int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
            TableColumn column = columnModel.getColumn(columnIndex);
            column.setPreferredWidth(30);
        }
    }
}
