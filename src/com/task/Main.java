package com.task;

import com.task.composites.HorizontalMatrixGroup;
import com.task.composites.VerticalMatrixGroup;
import com.task.decorators.RenumberingDecorator;
import com.task.drawers.ConsoleDrawer;
import com.task.drawers.IDrawer;
import com.task.matrixes.AMatrixBridge;
import com.task.matrixes.AbstractMatrix;
import com.task.matrixes.NormalMatrix;
import com.task.matrixes.SparseMatrix;
import com.task.utils.InitiatorMatrix;

public class Main {
    public static void main(String[] args) {
        IDrawer<Integer> drawer = new ConsoleDrawer<>(true);
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

        AMatrixBridge<Integer> matrix = new SparseMatrix<>(3, 3,0);
        matrix.setDrawer(drawer);
        InitiatorMatrix.randomFillMatrix(matrix, 5, 20);
        for(int i = 0; i < matrix.getRows(); i++){
            for(int j = 0; j < matrix.getCols(); j++){
                System.out.print("("+matrix.get(i,j)+")");
            }
            System.out.println();
        }
        System.out.println();
        AbstractMatrix<Integer> matrix2 = new NormalMatrix<>(5, 3,0);
        matrix2.setDrawer(new ConsoleDrawer<>(true));
        InitiatorMatrix.randomFillMatrix(matrix2, 5, 20);
        for(int i = 0; i < matrix2.getRows(); i++){
            for(int j = 0; j < matrix2.getCols(); j++){
                System.out.print("("+matrix2.get(i,j)+")");
            }
            System.out.println();
        }
        System.out.println();

        AbstractMatrix<Integer> matrix3 = new NormalMatrix<>(1, 10,0);
        matrix2.setDrawer(drawer);
        matrix3.set(0,0, 44);
        matrix3.set(0,1, 33);

        VerticalMatrixGroup group1 = new VerticalMatrixGroup(drawer);
        group1.add(matrix);
        group1.add(matrix2);
        group1.add(matrix3);
        group1.draw();

        HorizontalMatrixGroup group2 = new HorizontalMatrixGroup(drawer);
        group2.add(matrix2);
        group2.add(matrix);
        group2.draw();

        VerticalMatrixGroup group = new VerticalMatrixGroup(drawer);
        group.add(group2);
        group.add(group1);
        group.draw();
        matrix = new RenumberingDecorator(group, 0, 5, false);
        matrix.draw();
        /*for(int i = 0; i < group2.getRows(); i++){
            for(int j = 0; j < group2.getCols(); j++){
                System.out.print("("+group2.get(i,j)+")");
            }
            System.out.println();
        }
        System.out.println();
        group2.draw();*/
        /*matrix.set(0,0, 0);*//* matrix.set(0,1, 0); matrix.set(0,2, 16);
        matrix.set(1,0, 14); matrix.set(1,1, 0); matrix.set(1,2, 11);
        matrix.set(2,0, 1); *//*matrix.set(2,1, 10); matrix.set(2,2, 3);*//*
        System.out.println();
        matrix.draw();
        matrix = new RenumberingDecorator(matrix, 0, 2, true);
        matrix.draw();
        matrix = new RenumberingDecorator(matrix, 0, 2, false);
        matrix.draw();
        matrix = new RenumberingDecorator(matrix, 0, 1, true);
        matrix.draw();
        System.out.println("SOURCE:");
        AMatrixBridge<Integer> source = matrix.getSourceObject();
        source.draw();*/

    }
}
