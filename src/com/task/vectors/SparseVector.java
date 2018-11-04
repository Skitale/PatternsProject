package com.task.vectors;

import com.task.utils.Validator;

import java.util.HashMap;
import java.util.Map;

public class SparseVector implements IVector<Integer> {
    private Map<Integer, Integer> data;
    private int size;

    public SparseVector(int size) {
        Validator.validateSize(size);
        this.size = size;
        data = new HashMap<>(this.size);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void set(int pos, Integer value) {
        Validator.validateIndex(pos, getSize());
        if(data.containsKey(pos)){
            data.replace(pos, value);
        } else {
            data.put(pos, value);
        }
    }

    @Override
    public Integer get(int pos) {
        Validator.validateIndex(pos, getSize());
        if(data.containsKey(pos)) return data.get(pos);
        return 0;
    }
}
