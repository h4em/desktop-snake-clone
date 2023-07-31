import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

public class GameTable extends JTable implements TableModelListener {
    public GameTable(AbstractTableModel model) {
        this.setModel(model);
        this.setCellRenderers();
        this.setEnabled(false);

        setRowHeight(30);
        setColumnWidth(30);

        setBackgroundColor(208, 208, 208);
    }

    private void setCellRenderers() {
        int columnCount = columnModel.getColumnCount();
        for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
            TableColumn column = columnModel.getColumn(columnIndex);
            column.setCellRenderer(new CellRenderer());
        }
    }

    private void setColumnWidth(int columnWidth) {
        int columnCount = columnModel.getColumnCount();
        for(int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
            TableColumn column = columnModel.getColumn(columnIndex);
            column.setPreferredWidth(columnWidth);
        }
    }

    private void setBackgroundColor(int r, int g, int b) {
        setGridColor(new Color(r, g, b));
    }
}