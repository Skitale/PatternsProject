package com.task.utils;

import com.task.matrixes.IMatrix;

public class StatisticsMatrix {
    private IMatrix<Integer> matrix;
    private int sum;
    private double average;
    private int max;
    private int countNotNullAndZero;
    private int num_ = 2;

    public StatisticsMatrix(IMatrix<Integer> matrix) {
        this.matrix = matrix;
        if (matrix == null) throw new UnsupportedOperationException("Matrix is null");
        calculateValues();
    }

    private void calculateValues() {
        max = Integer.MIN_VALUE;
        int countNotNull = 0;
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getCols(); j++) {
                if (matrix.get(i, j) != null && !matrix.get(i, j).equals(0)) {
                    sum += matrix.get(i, j);
                    if (matrix.get(i, j) > max) {
                        max = matrix.get(i, j);
                    }
                    countNotNullAndZero++;
                }
                if(matrix.get(i, j) != null){
                    countNotNull++;
                }
            }
        }
        average = (double) sum / (double) countNotNull;
    }

    public void printMatrix() {
        int maxSymbols = Integer.valueOf(max).toString().length();
        String delimiterP = new String(new char[maxSymbols * 2]).replace('\0', '-');
        String delimiterSpace = new String(new char[maxSymbols]).replace('\0', ' ');
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getCols(); j++) {
                if (matrix.get(i, j) != null) {
                    int needSymbols = maxSymbols - matrix.get(i,j).toString().length();
                    System.out.print("(" + matrix.get(i, j));
                    System.out.print(new String(new char[needSymbols]).replace('\0', ' '));
                    System.out.print(")");
                } else {
                    System.out.print("(" + delimiterSpace + ")");
                }
                if (j != matrix.getRows() - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            for(int k = 0; k < matrix.getCols(); k++) {
                System.out.print(delimiterP);
            }
            System.out.println();
        }
        System.out.println();
    }

    public int getSum() {
        return sum;
    }

    public double getAverage() {
        return average;
    }

    public int getMax() {
        return max;
    }

    public int getCountNotNull() {
        return countNotNullAndZero;
    }
}
