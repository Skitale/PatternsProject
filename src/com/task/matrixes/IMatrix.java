package com.task.matrixes;

import com.sun.istack.internal.Nullable;
import com.task.drawers.IDrawable;
import com.task.matrixes.iterators.IIterable;

public interface IMatrix<T extends Number> extends IDrawable, IIterable<T> {
    int getRows();

    int getCols();

    @Nullable
    T get(int row, int col);

    void set(int row, int col, T value);
}
