import java.awt.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public
    class Snake {
    private Field head;
    private Field tail;
    private Queue<Field> segments;
    private Direction direction;
    private boolean crashed;

    public Snake() {
        segments = new LinkedList<Field>();
    }

    public void init(Field startField) {
        addSnakeSegment(startField);
        setHead(startField);
        setTail(startField);
    }

    public void move() {
        Field newHead = calculateNewHead();
        addSnakeSegment(newHead);
        setHead(newHead);
    }

    private Field calculateNewHead() {
        int x = head.x;
        int y = head.y;

        Field result = switch(direction) {
            case UP -> new Field(x - 1, y);
            case DOWN -> new Field(x + 1, y);
            case LEFT -> new Field(x, y - 1);
            case RIGHT -> new Field(x, y + 1);
            default -> null;
        };

        return result;
    }

    public boolean collided() {
        return wallCollision() || selfCollision();
    }

    private boolean selfCollision() {
        LinkedList<Field> lSegments =  (LinkedList<Field>) segments;

        int first = lSegments.indexOf(head);
        int last = lSegments.lastIndexOf(head);

        if(first != last) {
            crashed = true;
            return true;
        }

        return false;
    }

    private boolean wallCollision() {
        if(head.x < 0 || head.y < 0) {
            crashed = true;
            return true;
        }
        if(head.x == Gameboard.gameBoardBoundX || head.y == Gameboard.gameBoardBoundY) {
            crashed = true;
            return true;
        }
        return false;
    }

    public void setDirection(Direction newDirection) {
        if(directionChangeAllowed(newDirection))
            direction = newDirection;
    }

    private boolean directionChangeAllowed(Direction newDirection) {
        return !Direction.areOpposite(direction, newDirection);
    }

    public void removeTail() {
        segments.poll();
        tail = segments.peek();
    }

    public boolean isAlive() {
        return !crashed;
    }

    public boolean hasStarted() {
        return direction != null;
    }

    public void addSnakeSegment(Field segment){
        segments.add(segment);
    }

    public void setHead(Field head) {
        this.head = head;
    }

    public void setTail(Field tail) {
        this.tail = tail;
    }

    public Field getHead() {
        return this.head;
    }

    public Field getTail() {
        return this.tail;
    }
}
