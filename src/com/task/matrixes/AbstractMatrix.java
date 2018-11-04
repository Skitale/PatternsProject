package com.task.matrixes;

import com.task.vectors.IVector;
import com.task.drawers.IDrawer;

import static com.task.utils.Validator.validateRowColIndexes;
import static com.task.utils.Validator.validateSize;

public abstract class AbstractMatrix<T extends Number> implements IMatrix<T> {
    private IVector<T>[] data;
    private IDrawer<T> drawer;
    private T zeroValue;

    public AbstractMatrix(int rows, int cols, IDrawer<T> drawer, T zeroValue) {
        validateSize(rows);
        validateSize(cols);
        this.drawer = drawer;
        this.data = new IVector[rows];
        this.zeroValue = zeroValue;
        for (int i = 0; i < rows; i++) {
            this.data[i] = createVector(cols, zeroValue);
        }
    }

    public T getZeroValue(){
        return zeroValue;
    }

    public AbstractMatrix(int rows, int cols, T zeroValue) {
        this(rows, cols, null, zeroValue);
    }

    public void setDrawer(IDrawer<T> drawer) {
        this.drawer = drawer;
    }

    public IDrawer<T> getDrawer() {
        return drawer;
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
    abstract public void draw();

    protected void drawBorder() {
        drawer.drawBorder(this);
    }

    protected void drawItem(int i, int j) {
        drawer.drawItem(this, i, j);
    }
}
