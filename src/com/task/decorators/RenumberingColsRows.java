package com.task.decorators;

import com.task.drawers.IDrawer;
import com.task.matrixes.AbstractMatrix;
import com.task.matrixes.IMatrix;
import com.task.utils.Validator;
import com.task.vectors.IVector;

public class RenumberingColsRows extends AbstractMatrix<Integer> {
    private AbstractMatrix<Integer> matrix;
    private boolean isRenumRows;
    private int from;
    private int to;
    private IDrawer<Integer> drawer;

    public RenumberingColsRows(AbstractMatrix<Integer> matrix, int from, int to, boolean isRenumRows) {
        super(0, 0, matrix.getZeroValue());
        this.drawer = matrix.getDrawer();
        if (isRenumRows) {
            Validator.validateIndex(from, matrix.getRows());
            Validator.validateIndex(to, matrix.getRows());
        } else {
            Validator.validateIndex(from, matrix.getCols());
            Validator.validateIndex(to, matrix.getCols());
        }
        this.matrix = matrix;
        this.to = to;
        this.from = from;
        this.isRenumRows = isRenumRows;
    }

    @Override
    public int getRows() {
        return matrix.getRows();
    }

    @Override
    public int getCols() {
        return matrix.getCols();
    }

    @Override
    public void setDrawer(IDrawer<Integer> drawer) {
        this.drawer = drawer;
    }

    @Override
    public IDrawer<Integer> getDrawer() {
        return this.drawer;
    }

    @Override
    public Integer get(int row, int col) {
        if (isRenumRows) {
            return getForRenRow(row, col);
        } else {
            return getForRenCol(row, col);
        }
    }

    @Override
    public void set(int row, int col, Integer value) {
        if (isRenumRows) {
            setForRenRow(row, col, value);
        } else {
            setForRenCol(row, col, value);
        }
    }

    @Override
    public IVector<Integer> createVector(int cols, Integer zeroValue) {
        return matrix.createVector(cols, zeroValue);
    }

    @Override
    protected void drawBorder() {
        drawer.drawBorder(this);
    }

    @Override
    protected void drawItem(int i, int j) {
        drawer.drawItem(this, i, j);
    }

    @Override
    public void draw() {
        drawBorder();
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                drawItem(i, j);
            }
        }
        drawBorder();
    }

    private int getForRenRow(int row, int col) {
        if (row == from) {
            return matrix.get(to, col);
        } else if (row == to) {
            return matrix.get(from, col);
        }
        return matrix.get(row, col);
    }

    private int getForRenCol(int row, int col) {
        if (col == from) {
            return matrix.get(row, to);
        } else if (col == to) {
            return matrix.get(row, from);
        }
        return matrix.get(row, col);
    }

    private void setForRenRow(int row, int col, int value) {
        if (row == from) {
            matrix.set(to, col, value);
        } else if (row == to) {
            matrix.set(from, col, value);
        } else {
            matrix.set(row, col, value);
        }
    }

    private void setForRenCol(int row, int col, int value) {
        if (col == from) {
            matrix.set(row, to, value);
        } else if (col == to) {
            matrix.set(row, from, value);
        } else {
            matrix.set(row, col, value);
        }
    }

    @Override
    public IMatrix<Integer> getInstance() {
        return matrix.getInstance();
    }

    /*private void drawForRenRow(int row, int col) {
        if (row == from) {
            matrix.drawItem(to, col);
        } else if (row == to) {
            matrix.drawItem(from, col);
        } else {
            matrix.drawItem(row, col);
        }
    }

    private void drawForRenCols(int row, int col) {
        if (col == from) {
            matrix.drawItem(row, to);
        } else if (col == to) {
            matrix.drawItem(row, from);
        } else {
            matrix.drawItem(row, col);
        }
    }*/
}
