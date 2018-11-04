package com.task.drawers;

import com.task.matrixes.IMatrix;

public class ConsoleDrawer<T extends Number> implements IDrawer<T> {
    private boolean enableBorder;
    private int maxSymbNum;
    private int prevI = -1;
    private int prevJ = -1;
    private int curI;
    private int curJ;
    //private StringBuilder sb;
    private String[][] grid;

    public ConsoleDrawer(boolean enableBorder) {
        this.enableBorder = enableBorder;
        this.maxSymbNum = -1;
        //sb = new StringBuilder();
    }

    @Override
    public void drawBorder(IMatrix<T> matrix) {
        if (!enableBorder) return;
        if (this.maxSymbNum == -1) this.maxSymbNum = getMaxSymbolsNum(matrix);
        String delimiterP = new String(new char[matrix.getCols() * (maxSymbNum + 1)]).replace('\0', '—');
        System.out.println();
        System.out.println(delimiterP);
        //sb.append('\n').append(delimiterP);
        resetHelpValue();
    }

    private void resetHelpValue(){
        prevI = -1;
        prevJ = -1;
        curI = 0;
        curJ = 0;
    }

    @Override
    public void drawItem(IMatrix<T> matrix, int i, int j) {
        curI = i;
        curJ = j;
        drawAllStubsItem(matrix);
        printRawItem(matrix, i, j, false);
        prevI = curI;
        prevJ = curJ;
    }

    private void setRawItemToGrid(IMatrix<T> matrix, int i, int j){
        if(grid == null) initGrid(matrix);
        T value = matrix.get(i, j);
        grid[i][j] = value.toString();
    }

    private void printRawItem(IMatrix<T> matrix, int i, int j, boolean enableStab){
        if (this.maxSymbNum == -1) this.maxSymbNum = getMaxSymbolsNum(matrix);
        T value = matrix.get(i, j);
        if (j == 0 && enableBorder) System.out.print("|");
        String strValue = "";
        int valueSymbols = maxSymbNum;
        if (!enableStab) {
            valueSymbols = maxSymbNum - value.toString().length();
            strValue = value.toString();
        }
        String det = new String(new char[valueSymbols]).replace('\0', ' ');
        strValue = "(" + strValue + det + ")";
        System.out.print(strValue);
        if (j != matrix.getCols() - 1) System.out.print("|");
        if (j == matrix.getCols() - 1 && enableBorder) {
            System.out.print("|");
        }
        if (j == matrix.getCols() - 1 /*&& i != matrix.getRows() - 1*/) {
            System.out.println();
        }
    }

    private void drawAllStubsItem(IMatrix<T> matrix){
        if(curI == prevI){
            for(int j = prevJ + 1; j < curJ; j++){
                printRawItem(matrix, curI, j, true);
            }
        } else {
            if(prevI != -1) {
                for (int j = prevJ + 1; j < matrix.getCols(); j++) {
                    printRawItem(matrix, prevI, j, true);
                }
            }

            for(int i = prevI + 1; i < curI; i++){
                for(int j = 0; j < matrix.getCols(); j++){
                    printRawItem(matrix, i, j, true);
                }
            }

            for(int j = 0; j < curJ; j++){
                printRawItem(matrix, curI, j, true);
            }
        }
    }

    private int getMaxSymbolsNum(IMatrix<T> matrix) {
        int max = 0;
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getCols(); j++) {
                if (matrix.get(i,j).toString().length() > max) {
                    max = matrix.get(i,j).toString().length();
                }
            }
        }
        return max;
    }

    private void initGrid(IMatrix<T> matrix){
        grid = new String[matrix.getRows()][matrix.getCols()];
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                grid[i][j] = " ";
            }
        }
    }


}
