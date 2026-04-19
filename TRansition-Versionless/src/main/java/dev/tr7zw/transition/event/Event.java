package dev.tr7zw.transition.event;

import java.util.function.*;

public interface Event<T> {

    public void register(Consumer<T> handler);

    public T callEvent(T event);

}
