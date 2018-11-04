package com.task.matrixes;

import com.task.drawers.IDrawer;

abstract public class AMatrixBridge<T extends Number> implements IMatrix<T> {
    private IDrawer<T> drawer;

    public AMatrixBridge(IDrawer<T> drawer) {
        this.drawer = drawer;
    }

    @Override
    abstract public int getRows();

    @Override
    abstract public int getCols();

    @Override
    abstract public T get(int row, int col);

    @Override
    abstract public void set(int row, int col, T value);

    @Override
    public void draw(){
        drawBorder();
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                drawItem(i, j);
            }
        }
        drawBorder();
    }

    protected void drawBorder() {
        drawer.drawBorder(this);
    }

    protected void drawItem(int i, int j) {
        drawer.drawItem(this, i, j);
    }

    public void setDrawer(IDrawer<T> drawer) {
        this.drawer = drawer;
    }

    public IDrawer<T> getDrawer() {
        return drawer;
    }

    public AMatrixBridge<T> getSourceObject(){
        return this;
    }
}
