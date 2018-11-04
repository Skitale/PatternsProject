package com.task.utils;

import com.task.matrixes.IMatrix;

public class InitiatorMatrix {
    public static void fillMatrix(IMatrix<Integer> matrix, int countNotZero, int maxValue){
        int count = 0;
        for(int i = 0; i < matrix.getRows(); i++){
            for (int j = 0; j < matrix.getCols(); j++){
                if(matrix.get(i, j) != null && !matrix.get(i, j).equals(0)) continue;
                matrix.set(i, j, randInt(1, maxValue));
                count++;
                if(count >= countNotZero) return;
            }
        }
    }

    public static void randomFillMatrix(IMatrix<Integer> matrix, int countNotZero, int maxValue){
        int counter = 0;
        Validator.validateSize(countNotZero);
        if(countNotZero > matrix.getCols() * matrix.getRows()) countNotZero = matrix.getCols() * matrix.getRows();
        while (counter != countNotZero){
            int i = randInt(0, matrix.getRows());
            int j = randInt(0, matrix.getCols());
            if(matrix.get(i, j) == null || matrix.get(i, j).equals(0)) {
                int value = randInt(1, maxValue);
                matrix.set(i, j, value);
                counter++;
            }
        }
    }
    private static int randInt(int from, int to){
        return from + (int) (Math.random() * to);
    }
}
