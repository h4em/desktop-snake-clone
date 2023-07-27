import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Snake {
    private Queue<int[]> snakeSegments;

    private int[] head;
    private int[] tail;

    private int direction;

    private int maxBoardX;
    private int maxBoardY;

    public Snake() {
        direction = 0;
        snakeSegments = new LinkedList<>();
    }

    public boolean move() {
        int[] newSegment = new int[2];

        switch(direction) {
            //east
            case 1: {
                newSegment = new int[]{head[0], head[1] + 1};
                break;
            }

            //south
            case 2: {
                newSegment = new int[] {head[0] + 1, head[1]};
                break;
            }

            //west
            case 3: {
                newSegment = new int[] {head[0], head[1] - 1};
                break;
            }

            //north
            case 4: {
                newSegment = new int[] {head[0] - 1, head[1]};
                break;
            }
            default:
                break;
        }

        if(wallCollision(newSegment) || snakeCollision(newSegment))
            return false;

        snakeSegments.add(newSegment);
        head = newSegment;

        return true;
    }

    public void removeTail() {
        snakeSegments.poll();
        tail = snakeSegments.peek();
    }

    public void initSnake(int[] head, int[] tail) {
        this.head = head;
        this.tail = tail;

        snakeSegments.add(tail);
        snakeSegments.add(head);
    }

    public void setBoardBounds(int maxX, int maxY) {
        this.maxBoardX = maxX;
        this.maxBoardY = maxY;
    }

    private boolean segmentsEqual(int[] s1, int[] s2) {
        return (s1[0] == s2[0] && s1[1] == s2[1]);
    }

    private boolean snakeCollision(int[] newSegment) {
        for(int[] s : snakeSegments) {
            if(segmentsEqual(s, newSegment))
                return true;
        }
        return false;
    }

    private boolean wallCollision(int[] newSegment) {
        if(newSegment[0] < 0 || newSegment[1] < 0)
            return true;
        if(newSegment[0] == maxBoardX || newSegment[1] == maxBoardY)
            return true;
        return false;
    }

    public boolean directionChangeAllowed(int newDirection) {
        switch(direction) {
            case 0, 1: {
                if(newDirection == 3)
                    return false;
                break;
            }
            case 2: {
                if(newDirection == 4)
                    return false;
                break;
            }
            case 3: {
                if(newDirection == 1)
                    return false;
                break;
            }
            case 4: {
                if(newDirection == 2)
                    return false;
                break;
            }
            default: break;
        }
        return true;
    }

    public boolean hasStarted() {
        return direction != 0;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int[] getHead() {
        return this.head;
    }

    public int[] getTail() {
        return this.tail;
    }
}
