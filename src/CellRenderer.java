import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component rendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        int cellValue = (int) value;
        if(cellValue == 0) {
            rendererComponent.setBackground(new Color(208, 208, 208));
        } else if(cellValue == 9) {
            rendererComponent.setBackground(new Color(159, 5, 5));
        } else {
            rendererComponent.setBackground(new Color(5, 159, 5));
        }

        rendererComponent.setForeground(rendererComponent.getBackground());

        return rendererComponent;
    }
}
