package com.task;

import com.task.drawers.SwingWindow;

public class Main {
    public static void main(String[] args) {
        SwingWindow swingWindow = new SwingWindow(800, 600);
        swingWindow.setVisible(true);
        System.out.println(String.format("hello %s", 3));
        /*StatisticsMatrix statisticsMatrix = new StatisticsMatrix(matrixes);
        statisticsMatrix.printMatrix();
        StringBuilder sb = new StringBuilder();
        sb.append("sum = ").append(statisticsMatrix.getSum())
                .append(", max = ").append(statisticsMatrix.getMax())
                .append(", average = ").append(statisticsMatrix.getAverage())
                .append(", countNotZeroOrEmpty = ").append(statisticsMatrix.getCountNotNull());
        System.out.println(sb.toString());*/
    }
}
