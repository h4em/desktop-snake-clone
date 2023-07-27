public interface GameInterface {
    Leaderboard getLeaderboard();

    void setSnakeDirection(int direction);

    void setGameStatusListener(GameStatusListener gl);

    void setPlayerListener(ScoreListener pl);

    void setCellListener(CellListener cl);
}
