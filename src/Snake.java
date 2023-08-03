import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class Snake extends Thread {
    private Point head;
    private Point tail;
    private Queue<Point> segments;
    private int direction;
    private int gameBoardBoundX;
    private int gameBoardBoundY;

    public Snake(int startPosX, int startPosY) {
        segments = new LinkedList<>();

        Point startSegment = new Point(startPosX, startPosY);
        segments.add(startSegment);
        head = tail = startSegment;
    }

    //TODO: wyjebac te setLocation
    private void tryMoving() {
        Point newSegment = new Point();

        switch(direction) {
            //east
            case 1: {
                newSegment.setLocation(head.getX(), head.getY() + 1);
                break;
            }

            //south
            case 2: {
                newSegment.setLocation(head.getX() + 1, head.getY());
                break;
            }

            //west
            case 3: {
                newSegment.setLocation(head.getX(), head.getY() - 1);
                break;
            }

            //north
            case 4: {
                newSegment.setLocation(head.getX() - 1, head.getY());
                break;
            }
            default:
                break;
        }

        if(wallCollision(newSegment) || snakeCollision(newSegment)) {
            //koniec
        } else {
            //sprawdz czy owoc, zajmij pole?
        }

        segments.add(newSegment);
        head = newSegment;
    }

    private boolean segmentsEqual(Point p1, Point p2) {
        return (p1.getX() == p2.getX() && p1.getY() == p2.getY());
    }

    private boolean snakeCollision(Point newSegment) {
        for(Point p : segments) {
            if(segmentsEqual(p, newSegment))
                return true;
        }
        return false;
    }

    private boolean wallCollision(Point newSegment) {
        if(newSegment.getX() < 0 || newSegment.getY() < 0)
            return true;
        if(newSegment.getX() == gameBoardBoundX || newSegment.getY() == gameBoardBoundY)
            return true;
        return false;
    }
    public void setDirection(int newDirection) {
        if(directionChangeAllowed(newDirection))
            direction = newDirection;
    }

    private boolean directionChangeAllowed(int newDirection) {
        switch(direction) {
            case 1: {
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

    private void removeTail() {
        segments.poll();
        tail = segments.peek();
    }

    public boolean hasStarted() {
        return direction != 0;
    }

    public Point getHead() {
        return this.head;
    }

    public Point getTail() {
        return this.tail;
    }

    public Queue<Point> getSnakeSegments() {
        return segments;
    }

    public int getLength() {return segments.size();}
}
