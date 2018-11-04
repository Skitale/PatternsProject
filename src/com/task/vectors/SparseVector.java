package com.task.vectors;

import com.task.utils.Validator;

import java.util.HashMap;
import java.util.Map;

public class SparseVector<T extends Number> implements IVector<T> {
    private Map<Integer, T> data;
    private int size;
    private T zeroValue;

    public SparseVector(int size, T zeroValue) {
        Validator.validateSize(size);
        this.size = size;
        data = new HashMap<>(this.size);
        this.zeroValue = zeroValue;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void set(int pos, T value) {
        Validator.validateIndex(pos, getSize());
        if(data.containsKey(pos)){
            data.replace(pos, value);
        } else {
            data.put(pos, value);
        }
    }

    @Override
    public T get(int pos) {
        Validator.validateIndex(pos, getSize());
        if(data.containsKey(pos)) return data.get(pos);
        return zeroValue;
    }
}
