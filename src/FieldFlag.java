public enum FieldFlag {
    SNAKE(1), FRUIT(9), FREE(0);
    private final int value;
    private FieldFlag(int value) {
        this.value = value;
    }
}
