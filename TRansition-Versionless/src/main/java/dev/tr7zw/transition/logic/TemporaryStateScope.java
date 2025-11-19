package dev.tr7zw.transition.logic;

import lombok.*;

import java.util.*;
import java.util.function.*;

/**
 * @param <T> Collection of TemporalHolder. Get applied in order and reverted in
 *            reverse.
 */
public class TemporaryStateScope<T> {

    private final List<TemporalHolder<T>> holders;

    public TemporaryStateScope(Collection<TemporalHolder<T>> holders) {
        this.holders = new ArrayList<>(holders);
    }

    public void apply(T obj, Consumer<T> consumer) {
        for (TemporalHolder<T> holder : holders) {
            holder.prepareState(obj);
        }
        consumer.accept(obj);
        for (int i = holders.size() - 1; i >= 0; i--) {
            holders.get(i).revertSate(obj);
        }
    }

    public interface TemporalHolder<T> {
        void prepareState(T obj);

        void revertSate(T obj);
    }

}
