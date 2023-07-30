public interface GameInterface {

    void setSnakeDirection(int direction);

    void setGameStatusListener(GameStatusListener gl);

    void setScoreListener(ScoreListener sl);

    void setCellListener(CellListener cl);
}
