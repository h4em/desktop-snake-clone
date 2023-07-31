public interface CellEventDispatcher {
    void addCellListener(CellListener cl);
    void fireCellChanged(Cell targetCell);
}
