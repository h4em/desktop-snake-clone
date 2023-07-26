import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component rendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        int cellValue =  Integer.parseInt((String)value);
        if(cellValue == 0) {
            rendererComponent.setBackground(Color.WHITE);
        } else if(cellValue == 9) {
            rendererComponent.setBackground(Color.RED);
        } else {
            rendererComponent.setBackground(Color.GREEN);
        }
        return rendererComponent;
    }
}
