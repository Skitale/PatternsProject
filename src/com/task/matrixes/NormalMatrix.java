package com.task.matrixes;

import com.task.vectors.IVector;
import com.task.vectors.NormalVector;
import com.task.drawers.IDrawer;

public class NormalMatrix<T extends Number> extends AbstractMatrix<T> {
    public NormalMatrix(int rows, int cols, T zeroValue) {
        super(rows, cols, zeroValue);
    }

    public NormalMatrix(int rows, int cols, IDrawer<T> drawer, T zeroValue) {
        super(rows, cols, drawer, zeroValue);
    }

    @Override
    public IVector<T> createVector(int cols, T zeroValue) {
        return new NormalVector<>(cols, zeroValue);
    }

    @Override
    public void draw() {
        drawBorder();
        for(int i = 0; i < getRows(); i++){
            for(int j = 0; j < getCols(); j++){
                drawItem(i, j);
            }
        }
        drawBorder();
    }
}
