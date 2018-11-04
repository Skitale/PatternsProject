package com.task;

import com.task.decorators.RenumberingColsRows;
import com.task.drawers.ConsoleDrawer;
import com.task.drawers.SwingWindow;
import com.task.matrixes.AbstractMatrix;
import com.task.matrixes.IMatrix;
import com.task.matrixes.NormalMatrix;
import com.task.utils.InitiatorMatrix;

public class Main {
    public static void main(String[] args) {
        SwingWindow swingWindow = new SwingWindow(800, 600);
        //swingWindow.setVisible(true);
        /*StatisticsMatrix statisticsMatrix = new StatisticsMatrix(matrixes);
        statisticsMatrix.printMatrix();
        StringBuilder sb = new StringBuilder();
        sb.append("sum = ").append(statisticsMatrix.getSum())
                .append(", max = ").append(statisticsMatrix.getMax())
                .append(", average = ").append(statisticsMatrix.getAverage())
                .append(", countNotZeroOrEmpty = ").append(statisticsMatrix.getCountNotNull());
        System.out.println(sb.toString());*/

        AbstractMatrix<Integer> matrix = new NormalMatrix<>(3, 3,0);
        matrix.setDrawer(new ConsoleDrawer<>(true));
        InitiatorMatrix.randomFillMatrix(matrix, 5, 20);
        /*matrix.set(0,0, 0); matrix.set(0,1, 0); matrix.set(0,2, 16);
        matrix.set(1,0, 14); matrix.set(1,1, 0); matrix.set(1,2, 11);
        matrix.set(2,0, 0); matrix.set(2,1, 10); matrix.set(2,2, 3);*/
        matrix.draw();
        matrix = new RenumberingColsRows(matrix, 0, 2, true);
        matrix.draw();
        matrix = new RenumberingColsRows(matrix, 0, 2, false);
        matrix.draw();
        matrix = new RenumberingColsRows(matrix, 0, 1, true);
        matrix.draw();
        System.out.println("SOURCE:");
        IMatrix<Integer> source = matrix.getInstance();
        source.draw();
    }
}
