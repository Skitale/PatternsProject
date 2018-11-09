package com.task.decorators;

import com.task.matrixes.AMatrixBridge;
import com.task.matrixes.iterators.IIterator;
import com.task.utils.Validator;

import java.util.function.BiFunction;

public class RenumberingDecorator extends AMatrixBridge<Integer> {
    private AMatrixBridge<Integer> matrix;
    private boolean isRenumRows;
    private int from;
    private int to;

    public RenumberingDecorator(AMatrixBridge<Integer> matrix, int from, int to, boolean isRenumRows) {
        super(matrix.getDrawer());
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
    public void draw() {
        drawBorder();
        matrix.iterate((i, j, value) -> drawItem(i, j), (i, j) -> get(i, j));
        drawBorder();
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
    public AMatrixBridge<Integer> getSourceObject() {
        return matrix.getSourceObject();
    }

    @Override
    public void iterate(IIterator<Integer> it, BiFunction<Integer, Integer, Integer> get) {
        matrix.iterate(it, get);
    }
}
