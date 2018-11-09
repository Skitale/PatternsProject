package com.task.composites;

import com.task.drawers.IDrawer;
import com.task.matrixes.AMatrixBridge;
import com.task.matrixes.AbstractMatrix;
import com.task.matrixes.iterators.IIterator;
import com.task.utils.Validator;
import javafx.util.Pair;

public class HorizontalMatrixGroup extends AbstractGroup {

    public HorizontalMatrixGroup(IDrawer<Integer> drawer) {
        super(drawer);
    }

    @Override
    protected Pair<Integer, Integer> convertLocalIndexesToGlobalIndexes(AMatrixBridge<Integer> locMatrix, int i, int j) {
        int sizeIndexJ = 0;
        for (AMatrixBridge<Integer> item : matrixList) {
            if (item.equals(locMatrix)) {
                break;
            }
            sizeIndexJ += item.getCols();
        }
        return new Pair<>(i, sizeIndexJ + j);
    }

    @Override
    protected Pair<Integer, Integer> convertGlobalIndexesToLocalIndexes(AMatrixBridge<Integer> locMatrix, int i, int j) {
        int sizeIndexJ = 0;
        for (AMatrixBridge<Integer> item : matrixList) {
            if (item.equals(locMatrix)) {
                break;
            }
            sizeIndexJ += item.getCols();
        }
        return new Pair<>(i, j - sizeIndexJ);
    }

    @Override
    public int getRows() {
        int max = Integer.MIN_VALUE;
        for (AMatrixBridge<Integer> matrix : matrixList) {
            if (matrix.getRows() > max) {
                max = matrix.getRows();
            }
        }
        return max;
    }

    @Override
    public int getCols() {
        int sum = 0;
        for (AMatrixBridge<Integer> matrix : matrixList) {
            sum += matrix.getCols();
        }
        return sum;
    }

    @Override
    public Integer get(int row, int col) {
        Validator.validateRowColIndexes(row, getRows(), col, getCols());
        int offset = 0;
        for (AMatrixBridge<Integer> matrix : matrixList) {
            if (col - offset < matrix.getCols()) {
                if (row < matrix.getRows()) {
                    return matrix.get(row, col - offset);
                } else {
                    return 0;
                }
            }
            offset += matrix.getCols();
        }
        return 0;
    }

    @Override
    public void set(int row, int col, Integer value) {
        Validator.validateRowColIndexes(row, getRows(), col, getCols());
        int offset = 0;
        for (AMatrixBridge<Integer> matrix : matrixList) {
            if (col - offset < matrix.getCols()) {
                if (row < matrix.getRows()) {
                    matrix.set(row, col - offset, value);
                }
            }
        }
    }
}
