public interface GameInterface {

    void setSnakeDirection(int direction);

    void setGameStatusListener(GameStatusListener gl);

    void setPlayerListener(ScoreListener pl);

    void setCellListener(CellListener cl);
}
