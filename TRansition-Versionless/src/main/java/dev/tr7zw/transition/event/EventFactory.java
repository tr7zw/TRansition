package dev.tr7zw.transition.event;

import java.util.*;
import java.util.function.*;

/**
 * Cut down self-implemented version of the Fabric API events. Should do the
 * job? https://en.wikipedia.org/wiki/KISS_principle
 * 
 * @author tr7zw
 *
 */
public class EventFactory {

    public static <T> Event<T> createEvent() {
        return new EventImpl<>();
    }

    private static class EventImpl<T> implements Event<T> {

        private List<Consumer<T>> handler = new ArrayList<>();

        @Override
        public void register(Consumer<T> handle) {
            handler.add(handle);
        }

        @Override
        public T callEvent(T event) {
            for (Consumer<T> con : handler) {
                con.accept(event);
            }
            return event;
        }

    }

}
