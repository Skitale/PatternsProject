package com.task.drawers;

import com.task.matrixes.IMatrix;

public interface IDrawer<T extends Number> {
    void drawBorder(IMatrix<T> abstractMatrix);

    void drawItem(IMatrix<T> abstractMatrix, int i, int j);
}
