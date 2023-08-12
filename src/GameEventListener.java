interface GameEventListener {
    //snake -> model
    void fieldTaken(int x, int y);
    void fruitSpawned(int x, int y);
    void fieldFreed(int x, int y);
    void fruitEaten();
    void newFruitRequest();
}
