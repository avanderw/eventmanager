package net.avdw.eventmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import org.pmw.tinylog.Logger;

public class EventManager {

    Map<String, List<Consumer>> listeners = new HashMap();

    public EventManager(String... eventTypes) {
        for (String eventType : eventTypes) {
            listeners.put(eventType, new ArrayList());
        }
    }

    public void subscribe(String eventType, Consumer consumer) {
        
        listeners.get(eventType).add(consumer);
    }

    public void unsubscribe(String eventType, Consumer consumer) {
        listeners.get(eventType).remove(consumer);
    }

    public void fire(String eventType, Object payload) {
        Logger.trace(String.format("%s ( %s )", eventType, payload));
        if (listeners.get(eventType) != null) {
            listeners.get(eventType).forEach((listener) -> {
                listener.accept(payload);
            });
        }
    }
}
