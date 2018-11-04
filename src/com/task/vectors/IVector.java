package com.task.vectors;

import com.sun.istack.internal.Nullable;

public interface IVector<T extends Number> {
    int getSize();

    void set(int pos, T value);

    @Nullable
    T get(int pos);
}
