interface GameEventListener {
    void scoreUpdated(GameEvent e);
    void highscoreUpdated(GameEvent e);
    void snakeCrashed();
    void gameEnded();
}
