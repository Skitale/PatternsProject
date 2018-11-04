package com.task.utils;

public class Validator {
    public static void validateSize(int size) {
        if (size < 0) throw new UnsupportedOperationException("Size is negative");
    }

    public static void validateRowColIndexes(int rowIndex, int rowsSize, int colIndex, int colsSize) {
        validateIndex(rowIndex, rowsSize, "rows");
        validateIndex(colIndex, colsSize, "cols");
    }

    public static void validateIndex(int index, int maxSize, String represent) {
        if (isCorrect(index, maxSize))
            throw new UnsupportedOperationException("Index is negative or more than " + represent + " size");
    }

    public static void validateIndex(int index, int maxSize) {
        if (isCorrect(index, maxSize))
            throw new UnsupportedOperationException("Index is negative or more than max size");
    }

    private static boolean isCorrect(int index, int maxSize) {
        return index < 0 || index >= maxSize;
    }

}
