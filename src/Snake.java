import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public
    class Snake
    extends Thread
    implements GameEventListener {
    private Point head;
    private Point tail;
    private Queue<Point> segments;
    private int direction;
    private boolean crashed;
    private boolean eatenFruit;

    public Snake(int startPosX, int startPosY) {
        segments = new LinkedList<Point>();

        Point startSegment = new Point(startPosX, startPosY);
        segments.add(startSegment);
        head = tail = startSegment;
    }

    @Override
    public void run() {
        while(!crashed) {
            eatenFruit = false;
            move();

            if(collision()) {
                crashed = true;
            } else {
                fireFieldTaken();
            }

            //ten delay jest tak dla pewnosci chyba, bug przy kolizji ci odejmie ogon ale to dobrze?
            delay(90);

            if(!eatenFruit) {
                fireFieldFreed();
                removeTail();
            }
        }
        //fireSnakeCrashed();
    }

    private GameEventListener gameEventListener;

    public void setGameEventListener(GameEventListener gel) {gameEventListener = gel;}

    private void fireFieldTaken() {
        gameEventListener.fieldTaken(head.x, head.y);
    }

    private void fireFieldFreed() {
        gameEventListener.fieldFreed(tail.x, tail.y);
    }

    private void move() {
        Point newHead = new Point();

        switch(direction) {
            //east
            case 1: {
                newHead.x = head.x;
                newHead.y = head.y + 1;
                break;
            }

            //south
            case 2: {
                newHead.x = head.x + 1;
                newHead.y = head.y;
                break;
            }

            //west
            case 3: {
                newHead.x = head.x;
                newHead.y = head.y - 1;
                break;
            }

            //north
            case 4: {
                newHead.x = head.x - 1;
                newHead.y = head.y;
                break;
            }
            default:
                break;
        }

        addSnakeSegment(newHead);
        updateHead(newHead);
    }

    private boolean collision() {
        return wallCollision() || selfCollision();
    }

    private boolean selfCollision() {
        Iterator<Point> iterator = segments.iterator();
        while(iterator.hasNext()) {
            Point p = iterator.next();
            if(!iterator.hasNext() && p.equals(head)) {
                return true;
            }
        }
        return false;
    }

    //publiczne atrybuty Point'a?
    private boolean wallCollision() {
        if(head.x < 0 || head.y < 0)
            return true;
        if(head.x == SnakeGameTableModel.gameBoardBoundX || head.y == SnakeGameTableModel.gameBoardBoundY)
            return true;
        return false;
    }

    private void delay(long duration) {
        try {
            Thread.sleep(duration);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setDirection(int newDirection) {
        if(directionChangeAllowed(newDirection))
            direction = newDirection;
    }

    @Override
    public void fruitEaten() {
        eatenFruit = true; //?
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

    private void addSnakeSegment(Point segment){
        segments.add(segment);
    }

    private void updateHead(Point newHead) {
        head = newHead;
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

    public int getLength() {return segments.size();}
    @Override
    public void fieldFreed(int x, int y) {
        ;
    }
    @Override
    public void fieldTaken(int x, int y) {
        ;
    }
    @Override
    public void fruitSpawned(int x, int y) {
        ;
    }
    @Override
    public void newFruitRequest() {
        ;
    }
}
