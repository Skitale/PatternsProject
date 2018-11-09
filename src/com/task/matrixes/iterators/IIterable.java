package com.task.matrixes.iterators;

import java.util.function.BiFunction;

public interface IIterable<T extends Number> {
    void iterate(IIterator<T> it, BiFunction<Integer, Integer, T> get);
}
