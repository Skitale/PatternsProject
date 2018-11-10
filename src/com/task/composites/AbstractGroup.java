package com.task.composites;

import com.task.drawers.IDrawer;
import com.task.matrixes.AMatrixBridge;
import com.task.matrixes.iterators.IIterator;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

abstract public class AbstractGroup extends AMatrixBridge<Integer> {
    protected List<AMatrixBridge<Integer>> matrixList;

    public AbstractGroup(IDrawer<Integer> drawer) {
        super(drawer);
        matrixList = new ArrayList<>();
    }

    abstract protected Pair<Integer, Integer> convertLocalIndexesToGlobalIndexes(AMatrixBridge<Integer> matrix, int i, int j);

    abstract protected Pair<Integer, Integer> convertGlobalIndexesToLocalIndexes(AMatrixBridge<Integer> matrix, int i, int j);

    @Override
    public void draw(){
        drawBorder();
        for(Pair<Integer, Integer> p : getAllEmptyCells()){
            drawItem(p.getKey(), p.getValue());
        }
        for(AMatrixBridge<Integer> matrix : matrixList){
            matrix.iterate(new IIterator<Integer>() {
                @Override
                public void iterator(int i, int j, Integer value) {
                    Pair<Integer, Integer> pair = convertLocalIndexesToGlobalIndexes(matrix, i, j);
                    drawItem(pair.getKey(), pair.getValue());
                }
            }, new BiFunction<Integer, Integer, Integer>() {
                @Override
                public Integer apply(Integer i, Integer j) {
                    return matrix.get(i, j);
                }
            });
        }
        drawBorder();
    }

    @Override
    public void iterate(IIterator<Integer> it, BiFunction<Integer, Integer, Integer> get) {
        for(Pair<Integer, Integer> p : getAllEmptyCells()){
            it.iterator(p.getKey(), p.getValue(), get.apply(p.getKey(), p.getValue()));
        }
        for(AMatrixBridge<Integer> matrix : matrixList){
            matrix.iterate(new IIterator<Integer>() {
                @Override
                public void iterator(int i, int j, Integer value) {
                    Pair<Integer, Integer> pair = convertLocalIndexesToGlobalIndexes(matrix, i, j);
                    it.iterator(pair.getKey(), pair.getValue(), value);
                }
            }, new BiFunction<Integer, Integer, Integer>() {
                @Override
                public Integer apply(Integer i, Integer j) {
                    Pair<Integer, Integer> pair = convertLocalIndexesToGlobalIndexes(matrix, i, j);
                    return get.apply(pair.getKey(), pair.getValue());
                }
            });
        }
    }

    abstract protected List<Pair<Integer, Integer>> getAllEmptyCells();

    public void add(AMatrixBridge<Integer> matrix) {
        matrixList.add(matrix);
    }
}
