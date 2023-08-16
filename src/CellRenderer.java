import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component rendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        //TODO: casting na fieldFlag?
        int cellValue = (int) value;
        if(cellValue == 0) {
            rendererComponent.setBackground(table.getGridColor());
        } else if(cellValue == 9) {
            rendererComponent.setBackground(new Color(248, 152, 29));
        } else {
            rendererComponent.setBackground(new Color(83, 130, 161));
        }

        rendererComponent.setForeground(rendererComponent.getBackground());

        return rendererComponent;
    }
}