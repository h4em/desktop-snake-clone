public enum Direction {
    //TODO: te valuesy sa niepotrzebne ino
    UP(1), DOWN(2), LEFT(3), RIGHT(4);
    private int value;

    private Direction(int value) {
        this.value = value;
    }

    public static boolean areOpposite(Direction d1, Direction d2) {
        if((d1 == UP && d2 == DOWN) || (d1 == DOWN && d2 == UP))
            return true;
        if((d1 == LEFT && d2 == RIGHT) || (d1 == RIGHT && d2 == LEFT))
            return true;
        return false;
    }
}
