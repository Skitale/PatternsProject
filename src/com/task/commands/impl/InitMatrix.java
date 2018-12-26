package com.task.commands.impl;

import com.task.commands.ACommand;
import com.task.matrixes.IMatrix;

public class InitMatrix extends ACommand {
    private IMatrix<Integer> matrix;
    private int cols;
    private int rows;

    public InitMatrix(IMatrix<Integer> matrix, int cols, int rows){
        this.matrix = matrix;
        this.cols = cols;
        this.rows = rows;
    }

    @Override
    protected void doExecute() {
        if(matrix == null) return;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                matrix.set(i, j, 0);
            }
        }
    }

    @Override
    protected ACommand clone() {
        return new InitMatrix(matrix, cols, rows);
    }
}
