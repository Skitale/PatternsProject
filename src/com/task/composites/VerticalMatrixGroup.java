package com.task.composites;

import com.task.drawers.IDrawer;
import com.task.matrixes.AMatrixBridge;
import com.task.matrixes.IMatrix;
import com.task.utils.Validator;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class VerticalMatrixGroup extends AbstractGroup {

    public VerticalMatrixGroup(IDrawer<Integer> drawer) {
        super(drawer);
    }

    @Override
    protected Pair<Integer, Integer> convertLocalIndexesToGlobalIndexes(AMatrixBridge<Integer> matrix, int i, int j) {
        int sizeIndexI = 0;
        for (AMatrixBridge<Integer> item : matrixList) {
            if (item.equals(matrix)) {
                break;
            }
            sizeIndexI += item.getRows();
        }
        return new Pair<>(sizeIndexI + i, j);
    }

    @Override
    protected Pair<Integer, Integer> convertGlobalIndexesToLocalIndexes(AMatrixBridge<Integer> matrix, int i, int j) {
        int sizeIndexI = 0;
        for (AMatrixBridge<Integer> item : matrixList) {
            if (item.equals(matrix)) {
                break;
            }
            sizeIndexI += item.getRows();
        }
        return new Pair<>(i - sizeIndexI, j);
    }

    @Override
    public int getRows() {
        int sum = 0;
        for (IMatrix<Integer> matrix : matrixList) {
            sum += matrix.getRows();
        }
        return sum;
    }

    @Override
    public int getCols() {
        int max = Integer.MIN_VALUE;
        for (IMatrix<Integer> matrix : matrixList) {
            if (matrix.getCols() > max) {
                max = matrix.getCols();
            }
        }
        return max;
    }

    @Override
    public Integer get(int row, int col) {
        Validator.validateRowColIndexes(row, getRows(), col, getCols());
        int offset = 0;
        for (IMatrix<Integer> matrix : matrixList) {
            if (row - offset < matrix.getRows()) {
                if (col < matrix.getCols()) {
                    return matrix.get(row - offset, col);
                } else {
                    return 0;
                }
            }
            offset += matrix.getRows();
        }
        return 0;
    }

    @Override
    public void set(int row, int col, Integer value) {
        Validator.validateRowColIndexes(row, getRows(), col, getCols());
        int offset = 0;
        for (IMatrix<Integer> matrix : matrixList) {
            if (row - offset < matrix.getRows()) {
                if (col < matrix.getCols()) {
                    matrix.set(row - offset, col, value);
                }
            }
            offset += matrix.getRows();
        }
    }

    @Override
    protected List<Pair<Integer, Integer>> getAllEmptyCells() {
        List<Pair<Integer, Integer>> listEmptyValue = new ArrayList<>();
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                int offset = 0;
                for (IMatrix<Integer> matrix : matrixList) {
                    if (i - offset < matrix.getRows()) {
                        if (j < matrix.getCols()) {
                            break;
                        } else {
                            listEmptyValue.add(new Pair<>(i, j));
                            break;
                        }
                    }
                    offset += matrix.getRows();
                }
            }
        }
        return listEmptyValue;
    }
}
