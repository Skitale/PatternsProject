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
        swingWindow.setVisible(true);
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
        //matrix.draw();

        AbstractMatrix<Integer> matrix2 = new SparseMatrix<>(5, 3,0);
        matrix2.setDrawer(new ConsoleDrawer<>(true));
        InitiatorMatrix.randomFillMatrix(matrix2, 5, 20);
       // matrix2.draw();

        AbstractMatrix<Integer> matrix3 = new NormalMatrix<>(5, 3,0);
        matrix3.setDrawer(new ConsoleDrawer<>(true));
        InitiatorMatrix.randomFillMatrix(matrix3, 5, 20);
        //matrix3.draw();

        AMatrixBridge<Integer> matrix2x2 = new SparseMatrix<>(2, 2,0);
        matrix2x2.setDrawer(new ConsoleDrawer<>(true));
        //InitiatorMatrix.randomFillMatrix(matrix2x2, 1, 20);
        matrix2x2.set(0, 0, 1);
        //matrix2x2.draw();

        AMatrixBridge<Integer> matrix2x2T = new SparseMatrix<>(2, 2,0);
        matrix2x2T.setDrawer(new ConsoleDrawer<>(true));
        //InitiatorMatrix.randomFillMatrix(matrix2x2T, 1, 20);
        matrix2x2T.set(0, 1, 9);
        //matrix2x2T.draw();

        HorizontalMatrixGroup group = new HorizontalMatrixGroup(new ConsoleDrawer<>(true));
        group.add(matrix2x2);
        group.add(matrix);
        group.add(matrix3);
        //group.draw();

        VerticalMatrixGroup groupH2 = new VerticalMatrixGroup(new ConsoleDrawer<>(true));
        //groupH2.add(matrix);
        //groupH2.add(matrix2);
        //groupH2.add(matrix3);
        groupH2.add(matrix2x2T);

        VerticalMatrixGroup vGroup = new VerticalMatrixGroup(new ConsoleDrawer<>(true));
        vGroup.add(group);
        vGroup.add(matrix2);
        vGroup.add(matrix3);
        vGroup.add(groupH2);
        //vGroup.draw();

        /*AMatrixBridge<Integer> mTest = new RenumberingDecorator(vGroup, 0, 7, false);
        mTest.draw();*/
        //mTest = new RenumberingDecorator(mTest, 0,1, true);
        /*for(int i = 0; i < mTest.getRows(); i++){
            for(int j = 0; j < mTest.getCols(); j++){
                System.out.print("("+mTest.get(i,j)+")");
            }
            System.out.println();
        }*/
        //mTest.draw();

        AbstractMatrix<Integer> m1W = new SparseMatrix<>(2, 2,0);
        m1W.set(0,0, 1);
        AbstractMatrix<Integer> m2W = new SparseMatrix<>(2, 2,0);
        m2W.set(1,1, 2);
        AbstractMatrix<Integer> m3W = new NormalMatrix<>(2, 2,0);
        m3W.set(1,0, 3);
        HorizontalMatrixGroup gW1H = new HorizontalMatrixGroup(null);
        gW1H.add(m1W); gW1H.add(m2W); gW1H.add(m3W);

        AbstractMatrix<Integer> m2H = new SparseMatrix<>(2, 2,0);
        m2H.set(0, 1, 4);
        AbstractMatrix<Integer> m3H = new NormalMatrix<>(2, 2,0);
        m3H.set(1, 0, 5);
        VerticalMatrixGroup vGroup1 = new VerticalMatrixGroup(new ConsoleDrawer<>(true));
        vGroup1.add(gW1H); vGroup1.add(m2H); vGroup1.add(m3H);
         vGroup1.draw();
        RenumberingDecorator rd = new RenumberingDecorator(vGroup1,0, 4, false);
        rd.draw();
        /*AbstractMatrix<Integer> matrix3 = new NormalMatrix<>(1, 10,0);
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
        matrix.draw();*/
        /*for(int i = 0; i < group2.getRows(); i++){
            for(int j = 0; j < group2.getCols(); j++){
                System.out.print("("+group2.get(i,j)+")");
            }
            System.out.println();
        }
        System.out.println();
        group2.draw();*/

        /*matrix.set(0,0, 0);*//* *//*matrix.set(0,1, 0);*//* matrix.set(0,2, 16);
        matrix.set(1,0, 14); *//*matrix.set(1,1, 0);*//* matrix.set(1,2, 11);
        matrix.set(2,0, 1); matrix.set(2,1, 10); *//*matrix.set(2,2, 0);*//*
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
