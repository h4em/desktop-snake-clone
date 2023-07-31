import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.util.ArrayList;
import java.util.List;

public class FruitHandler implements CellEventDispatcher{
    private Cell fruitCell;

    //TODO: singleton?
    public FruitHandler(int rowNum, int colNum) {
        cellListeners = new ArrayList<>();
    }

    private void initFruit(int x, int y) {
        fruitCell = new Cell(9, x, y);
        fireCellChanged(fruitCell);
    }

    private List<CellListener> cellListeners;

    @Override
    public void addCellListener(CellListener cl) {
        cellListeners.add(cl);
    }

    @Override
    public void fireCellChanged(Cell targetCell) {
        for(CellListener cl : cellListeners) {
            cl.cellChanged(new CellEvent(targetCell));
        }
    }
}
