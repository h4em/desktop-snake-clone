import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameTable extends JTable implements CellListener {
    public GameTable(AbstractTableModel model) {
        this.setModel(model);
        this.setRowHeight(30);
        this.setEnabled(false);
        this.setGridColor(new Color(208, 208, 208));

        TableColumnModel columnModel = this.getColumnModel();
        int columnCount = columnModel.getColumnCount();

        for(int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
            TableColumn column = columnModel.getColumn(columnIndex);
            column.setPreferredWidth(30);
        }

        for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
            TableColumn column = columnModel.getColumn(columnIndex);
            column.setCellRenderer(new CellRenderer());
        }
    }

    @Override
    public void cellValueUpdated(CellEvent evt) {
        int[] values = (int[]) evt.getSource();
        int row = values[0];
        int col = values[1];
        int val = values[2];
        this.getModel().setValueAt(val, row, col);
        this.repaint();
    }
}
