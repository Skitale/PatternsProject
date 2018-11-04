package com.task.vectors;

import com.task.utils.Validator;

public class NormalVector<T extends Number> implements IVector<T> {
    private T[] data;

    public NormalVector(int size, T zeroValue) {
        Validator.validateSize(size);
        this.data = (T[]) new Number[size];
        for(int i = 0; i < size; i++){
            this.data[i] = zeroValue;
        }
    }

    @Override
    public int getSize() {
        return data.length;
    }

    @Override
    public void set(int pos, T value) {
        Validator.validateIndex(pos, getSize());
        this.data[pos] = value;
    }

    @Override
    public T get(int pos) {
        Validator.validateIndex(pos, getSize());
        return this.data[pos];
    }
}
