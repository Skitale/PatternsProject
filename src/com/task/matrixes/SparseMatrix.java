package com.task.matrixes;

import com.task.vectors.IVector;
import com.task.vectors.SparseVector;
import com.task.drawers.IDrawer;

public class SparseMatrix extends AbstractMatrix<Integer> {
    public SparseMatrix(int rows, int cols, IDrawer<Integer> drawer) {
        super(rows, cols, drawer, 0);
    }

    public SparseMatrix(int rows, int cols) {
        super(rows, cols, 0);
    }

    @Override
    protected IVector<Integer> createVector(int cols, Integer zeroValue) {
        return new SparseVector(cols);
    }

    @Override
    public void draw() {
        drawBorder();
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                if (get(i, j) != 0) {
                    drawItem(i, j);
                }
            }
        }
        drawBorder();
    }
}
