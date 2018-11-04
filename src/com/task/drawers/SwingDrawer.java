package com.task.drawers;

import com.task.matrixes.IMatrix;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SwingDrawer<T extends Number> implements IDrawer<T> {
    private Container container;
    private Container currentContainer;
    private Container rootContainer;
    private Font font;
    private Border labelBorder;
    private Border border;
    private Color labelColor;
    private boolean enableBorder;
    private int gridCols;

    public SwingDrawer(Container rootContainer, boolean enableBorder) {
        this.container = Box.createVerticalBox();
        this.rootContainer = rootContainer;
        this.enableBorder = enableBorder;
        font = new Font("Helvetica Neue", Font.PLAIN, 20);
        labelBorder = BorderFactory.createLineBorder(Color.GREEN);
        border = BorderFactory.createLineBorder(Color.RED);
        labelColor = Color.BLACK;
        if(enableBorder){
            container.add(new Panel());
            container.add(new Panel());
            container.add(new Panel());
        } else {
            container.add(new Panel());
        }
    }

    @Override
    public void drawBorder(IMatrix<T> matrix) {
        if (!enableBorder) return;
        if (currentContainer == null) setContainer(matrix.getRows(), matrix.getCols());
        Container gridUp = (Container) container.getComponent(0);
        Container gridDown = (Container) container.getComponent(2);
        gridUp.removeAll();
        gridDown.removeAll();
        gridUp.setLayout(new GridLayout(1, matrix.getCols() + 2, 2, 2));
        gridDown.setLayout(new GridLayout(1, matrix.getCols() + 2, 2, 2));
        String delimiterP = "—";
        for (int i = 0; i < matrix.getCols() + 2; i++) {
            drawBoard(gridUp, delimiterP);
            drawBoard(gridDown, delimiterP);
        }
        drawItemsBorders(matrix.getRows(), gridCols);
    }

    private void drawItemsBorders(int gridRows, int gridCols){
        for(int i = 0; i < gridRows; i++){
            drawBoard(i, 0, "|");
            drawBoard(i, gridCols - 1, "|");
        }
    }

    @Override
    public void drawItem(IMatrix<T> matrix, int i, int j) {
        if (currentContainer == null) setContainer(matrix.getRows(), matrix.getCols());
        T value = matrix.get(i, j);
        JLabel cLabel = getLabelFromContainer(i, j, gridCols);
        cLabel.setText(value.toString());
        cLabel.setToolTipText("(" + i + "," + j + ")");
    }

    private void setContainer(int rows, int cols) {
        if (enableBorder) {
            cols += 2;
            currentContainer = (Container) container.getComponent(1);
        } else {
            currentContainer = (Container) container.getComponent(0);
        }
        currentContainer.setLayout(new GridLayout(rows, cols, 2, 2));
        gridCols = cols;
        for(int i = 0; i < rows * cols; i++){
            JLabel label = new JLabel();
            initDefaultPropLabel(label);
            currentContainer.add(label);
        }
        rootContainer.add(container);
    }

    private void initDefaultPropLabel(JLabel label){
        label.setForeground(labelColor);
        label.setFont(font);
        label.setText(" ");
        label.setBorder(labelBorder);
        label.setToolTipText("(" + "," + ")");
        label.setHorizontalAlignment(JLabel.CENTER);
    }

    private int getSerialNumberFromIndexes(int i, int j, int cols){
        return i * cols + j;
    }

    private JLabel getLabelFromContainer(int i, int j, int gridCols){
        if(enableBorder) j+=1;
        int serialNumber = getSerialNumberFromIndexes(i, j, gridCols);
        return getLabel(serialNumber, currentContainer);
    }

    private JLabel getLabel(int i, Container c){
        Component[] array = c.getComponents();
        if(i < array.length && array[i] instanceof JLabel){
            return (JLabel)array[i];
        }
        throw new UnsupportedOperationException("JLabel not found in container");
    }

    private void drawBoard(Container c, String s) {
        JLabel board = new JLabel();
        board.setText(s);
        board.setBorder(border);
        board.setHorizontalAlignment(JLabel.CENTER);
        c.add(board);
    }

    private void drawBoard(int i, int j, String s) {
        int serialNumber = getSerialNumberFromIndexes(i, j, gridCols);
        JLabel board = getLabel(serialNumber, currentContainer);
        board.setText(s);
        board.setBorder(border);
        board.setHorizontalAlignment(JLabel.CENTER);
    }
}
