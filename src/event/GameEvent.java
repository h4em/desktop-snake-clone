package event;

import java.util.EventObject;

public class GameEvent extends EventObject {
    public GameEvent(Object source) {
        super(source);
    }
}
