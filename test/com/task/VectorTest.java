package com.task;


import com.task.vectors.IVector;
import com.task.vectors.NormalVector;
import com.task.vectors.SparseVector;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class VectorTest {
    private static final int SIZE = 20;

    @Test
    public void testNormalVector(){
        IVector<Integer> vector = new NormalVector<>(SIZE, 0);
        testVector(vector);

        assertEquals(Integer.valueOf(0), vector.get(3));
    }

    @Test
    public void testSparseVector(){
        IVector<Integer> vector = new SparseVector(SIZE);
        testVector(vector);

        assertEquals(null, vector.get(3));
    }

    private void testVector(IVector<Integer> vector){
        if(vector == null) fail();
        try {
            vector.set(-1, 2);
            fail();
        } catch (UnsupportedOperationException e){
            assertEquals(e.getMessage(), "Index is negative or more than max size");
        }

        try {
            vector.set(SIZE, 2);
            fail();
        } catch (UnsupportedOperationException e){
            assertEquals(e.getMessage(), "Index is negative or more than max size");
        }

            vector.set(2, 5);
            vector.set(7, 1412);
            vector.set(SIZE - 1, 232);
            vector.set(0, 2234);

            assertEquals(vector.get(2) , Integer.valueOf(5));
            assertEquals(vector.get(7), Integer.valueOf(1412));
            assertEquals(vector.get(SIZE - 1), Integer.valueOf(232));
            assertEquals(vector.get(0), Integer.valueOf(2234));

        try {
            vector.get(-1);
            fail();
        } catch (UnsupportedOperationException e) {
            assertEquals(e.getMessage(), "Index is negative or more than max size");
        }

        try {
            vector.get(SIZE);
            fail();
        } catch (UnsupportedOperationException e){
            assertEquals(e.getMessage(), "Index is negative or more than max size");
        }
    }
}
