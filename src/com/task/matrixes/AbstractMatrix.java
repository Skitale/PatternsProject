package com.task.matrixes;

import com.task.matrixes.iterators.IIterator;
import com.task.vectors.IVector;
import com.task.drawers.IDrawer;

import java.util.function.BiFunction;

import static com.task.utils.Validator.validateRowColIndexes;
import static com.task.utils.Validator.validateSize;

public abstract class AbstractMatrix<T extends Number> extends AMatrixBridge<T> {
    private IVector<T>[] data;
    private T zeroValue;

    public AbstractMatrix(int rows, int cols, IDrawer<T> drawer, T zeroValue) {
        super(drawer);
        validateSize(rows);
        validateSize(cols);
        this.data = new IVector[rows];
        this.zeroValue = zeroValue;
        for (int i = 0; i < rows; i++) {
            this.data[i] = createVector(cols, zeroValue);
        }
    }

    public AbstractMatrix(int rows, int cols, T zeroValue) {
        this(rows, cols, null, zeroValue);
    }

    public T getZeroValue(){
        return zeroValue;
    }

    @Override
    public int getRows() {
        return data.length;
    }

    @Override
    public int getCols() {
        return data[0].getSize();
    }

    @Override
    public T get(int row, int col) {
        validateRowColIndexes(row, getRows(), col, getCols());
        return data[row].get(col);
    }

    @Override
    public void set(int row, int col, T value) {
        validateRowColIndexes(row, getRows(), col, getCols());
        data[row].set(col, value);
    }

    abstract public IVector<T> createVector(int cols, T zeroValue);

    @Override
    public void iterate(IIterator<T> it, BiFunction<Integer, Integer, T> get) {
        for(int i = 0; i < getRows(); i++){
            for(int j = 0; j < getCols(); j++){
                it.iterator(i, j, get.apply(i, j));
            }
        }
    }
}
