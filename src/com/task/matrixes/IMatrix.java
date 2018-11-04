package com.task.matrixes;

import com.sun.istack.internal.Nullable;
import com.task.drawers.IDrawable;

public interface IMatrix<T extends Number> extends IDrawable {
    int getRows();

    int getCols();

    @Nullable
    T get(int row, int col);

    void set(int row, int col, T value);

    default IMatrix<T> getInstance(){
        return this;
    }
}
