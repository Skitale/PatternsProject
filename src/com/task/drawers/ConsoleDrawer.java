package com.task.drawers;

import com.task.matrixes.IMatrix;

public class ConsoleDrawer<T extends Number> implements IDrawer<T> {
    private boolean enableBorder;
    private int maxSymbNum;
    private String[][] grid;
    private int numCall;

    public ConsoleDrawer(boolean enableBorder) {
        this.enableBorder = enableBorder;
        this.maxSymbNum = -1;
    }

    @Override
    public void drawBorder(IMatrix<T> matrix) {
        numCall++;
        if (numCall == 2) {
            drawItemsFromGrid(matrix);
            numCall = 0;
            grid = null;
        }
        if (!enableBorder) return;
        if (this.maxSymbNum == -1) this.maxSymbNum = getMaxSymbolsNum(matrix);
        String delimiterP = new String(new char[matrix.getCols() * (maxSymbNum + 1)]).replace('\0', '—');
        System.out.println();
        System.out.println(delimiterP);
    }

    private void drawItemsFromGrid(IMatrix<T> matrix) {
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getCols(); j++) {
                printRawItem(matrix, i, j);
            }
        }
    }

    @Override
    public void drawItem(IMatrix<T> matrix, int i, int j) {
        setRawItemToGrid(matrix, i, j);
    }

    private void setRawItemToGrid(IMatrix<T> matrix, int i, int j) {
        if (grid == null) initGrid(matrix);
        T value = matrix.get(i, j);
        grid[i][j] = value.toString();
    }

    private void printRawItem(IMatrix<T> matrix, int i, int j) {
        if (this.maxSymbNum == -1) this.maxSymbNum = getMaxSymbolsNum(matrix);
        if (grid == null) return;
        String value = grid[i][j];
        if (j == 0 && enableBorder) System.out.print("|");
        int valueSymbols = maxSymbNum - value.length();
        String det = new String(new char[valueSymbols]).replace('\0', ' ');
        value = "(" + value + det + ")";
        System.out.print(value);
        if (j != matrix.getCols() - 1) System.out.print("|");
        if (j == matrix.getCols() - 1 && enableBorder) {
            System.out.print("|");
        }
        if (j == matrix.getCols() - 1 && i != matrix.getRows() - 1) {
            System.out.println();
        }
    }

    private int getMaxSymbolsNum(IMatrix<T> matrix) {
        int max = 0;
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getCols(); j++) {
                if (matrix.get(i, j).toString().length() > max) {
                    max = matrix.get(i, j).toString().length();
                }
            }
        }
        return max;
    }

    private void initGrid(IMatrix<T> matrix) {
        grid = new String[matrix.getRows()][matrix.getCols()];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = " ";
            }
        }
    }


}
