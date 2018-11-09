package com.task.matrixes;

import com.task.matrixes.iterators.IIterator;
import com.task.vectors.IVector;
import com.task.vectors.SparseVector;
import com.task.drawers.IDrawer;

import java.util.function.BiFunction;

public class SparseMatrix<T extends Number> extends AbstractMatrix<T> {
    public SparseMatrix(int rows, int cols, IDrawer<T> drawer, T zeroValue) {
        super(rows, cols, drawer, zeroValue);
    }

    public SparseMatrix(int rows, int cols, T zeroValue) {
        super(rows, cols, zeroValue);
    }

    @Override
    public IVector<T> createVector(int cols, T zeroValue) {
        return new SparseVector<>(cols, zeroValue);
    }

    @Override
    public void draw() {
        drawBorder();
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                if (!getZeroValue().equals(get(i, j))) {
                    drawItem(i, j);
                }
            }
        }
        drawBorder();
    }

    @Override
    public void iterate(IIterator<T> it, BiFunction<Integer, Integer, T> get) {
        for(int i = 0; i < getRows(); i++){
            for(int j = 0; j < getCols(); j++){
                if(!getZeroValue().equals(get.apply(i, j))) {
                    it.iterator(i, j, get.apply(i, j));
                }
            }
        }
    }
}
