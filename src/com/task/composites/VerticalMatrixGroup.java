package com.task.composites;

import com.task.drawers.IDrawer;
import com.task.matrixes.AMatrixBridge;
import com.task.matrixes.IMatrix;
import com.task.utils.Validator;

import java.util.ArrayList;
import java.util.List;

public class VerticalMatrixGroup extends AMatrixBridge<Integer>{
    private List<AMatrixBridge<Integer>> matrixList;

    public VerticalMatrixGroup(IDrawer<Integer> drawer) {
        super(drawer);
        matrixList = new ArrayList<>();
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

    public void add(AMatrixBridge<Integer> matrix) {
        matrixList.add(matrix);
    }
}
