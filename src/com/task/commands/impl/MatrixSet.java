package com.task.commands.impl;

import com.task.commands.ACommand;
import com.task.matrixes.IMatrix;

public class MatrixSet extends ACommand {
    private IMatrix<Integer> matrix;
    private int indexI;
    private int indexJ;
    private int value;

    public MatrixSet(IMatrix<Integer> matrix, int i, int j, int value){
        this.matrix = matrix;
        this.indexI = i;
        this.indexJ = j;
        this.value = value;
    }

    @Override
    protected void doExecute() {
        matrix.set(indexI, indexJ, value);
    }

    @Override
    protected ACommand clone() {
        return new MatrixSet(matrix, indexI, indexJ, value);
    }
}
