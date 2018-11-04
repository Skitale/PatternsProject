package com.task;

import com.task.drawers.ConsoleDrawer;
import com.task.drawers.IDrawer;
import com.task.matrixes.IMatrix;
import com.task.matrixes.NormalMatrix;
import com.task.matrixes.SparseMatrix;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MatrixTest {
    private static final int ROWS = 10;
    private static final int COLS = 10;
    private static final IDrawer<Integer> drawer = new ConsoleDrawer<>(true);

    @Test
    public void testNormalVector(){
        IMatrix<Integer> matrix = new NormalMatrix<>(ROWS, COLS, drawer, 0);
        testMatrix(matrix);

        assertEquals(Integer.valueOf(0), matrix.get(7, 7));
    }

    @Test
    public void testSparseVector(){
        IMatrix<Integer> matrix = new SparseMatrix<>(ROWS, COLS, drawer, 0);
        testMatrix(matrix);

        assertEquals(null, matrix.get(7,7));
    }

    private void testMatrix(IMatrix<Integer> matrix){
        if(matrix == null) return;
        try {
            matrix.set(-1, 1, 23);
            fail();
        } catch (UnsupportedOperationException e){
            assertEquals(e.getMessage(), "Index is negative or more than rows size");
        }

        try {
            matrix.set(1, -1, 23);
            fail();
        } catch (UnsupportedOperationException e){
            assertEquals(e.getMessage(), "Index is negative or more than cols size");
        }

        try {
            matrix.set(ROWS, 6, 23);
            fail();
        } catch (UnsupportedOperationException e){
            assertEquals(e.getMessage(), "Index is negative or more than rows size");
        }

        try {
            matrix.set(2, COLS, 23);
            fail();
        } catch (UnsupportedOperationException e){
            assertEquals(e.getMessage(), "Index is negative or more than cols size");
        }

        matrix.set(1,1, 2);
        matrix.set(0,0, 23);
        matrix.set(ROWS - 1, COLS - 1, 9999);
        matrix.set(5,5, 123);

        assertEquals(Integer.valueOf(2), matrix.get(1,1));
        assertEquals(Integer.valueOf(23), matrix.get(0,0));
        assertEquals(Integer.valueOf(9999), matrix.get(ROWS - 1, COLS - 1));
        assertEquals(Integer.valueOf(123), matrix.get(5, 5));

        try {
            matrix.get(-3, 3);
            fail();
        } catch (UnsupportedOperationException e) {
            assertEquals(e.getMessage(), "Index is negative or more than rows size");
        }

        try {
            matrix.get(3, -3);
            fail();
        } catch (UnsupportedOperationException e) {
            assertEquals(e.getMessage(), "Index is negative or more than cols size");
        }

        try {
            matrix.get(ROWS, 3);
            fail();
        } catch (UnsupportedOperationException e) {
            assertEquals(e.getMessage(), "Index is negative or more than rows size");
        }

        try {
            matrix.get(3, COLS);
            fail();
        } catch (UnsupportedOperationException e) {
            assertEquals(e.getMessage(), "Index is negative or more than cols size");
        }
    }
}
