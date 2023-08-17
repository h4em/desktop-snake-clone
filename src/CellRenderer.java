import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        switch ((int) value) {
            case 0 -> component.setBackground(table.getGridColor());
            case 1 -> component.setBackground(new Color(83, 130, 161));
            case 9 -> component.setBackground(new Color(248, 152, 29));
        }

        component.setForeground(component.getBackground());

        return component;
    }
}