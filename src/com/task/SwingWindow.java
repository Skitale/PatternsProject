package com.task;

import com.task.drawers.ConsoleDrawer;
import com.task.drawers.IDrawer;
import com.task.drawers.SwingDrawer;
import com.task.matrixes.AbstractMatrix;
import com.task.matrixes.IMatrix;
import com.task.matrixes.NormalMatrix;
import com.task.matrixes.SparseMatrix;
import com.task.utils.InitiatorMatrix;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.task.utils.SwingUtils.deleteAll;

public class SwingWindow extends JFrame {
    private JButton generateSimpMatrixButton = new JButton("Generate the simple matrixes");
    private JButton generateSparMatrixButton = new JButton("Generate the sparse matrixes");
    private JCheckBox jCheckBox = new JCheckBox("enable border", true);
    private JComboBox<Pair<Integer, Integer>> comboBox = new JComboBox<>();
    private NormalMatrix<Integer> normalMatrix;
    private SparseMatrix<Integer> sparseMatrix;
    private int rows = 20;
    private int cols = 20;
    private Container gridContainer;
    private Container rootContainer;

    public SwingWindow(int width, int height) {
        super("Draw matrixes");
        this.setSize(width, height);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initComboBox();
        rootContainer = Box.createVerticalBox();
        this.setContentPane(rootContainer);

        Box panel = Box.createHorizontalBox();
        panel.add(generateSimpMatrixButton);
        panel.add(generateSparMatrixButton);
        panel.add(comboBox);
        panel.add(jCheckBox);
        rootContainer.add(panel);
        rootContainer.add(Box.createVerticalStrut(5));
        generateSimpMatrixButton.addMouseListener(new GenerateNormalHandler());
        generateSparMatrixButton.addMouseListener(new GenerateSparseHandler());

        gridContainer = new Panel();
        rootContainer.add(gridContainer);
    }

    private void initComboBox() {
        comboBox.addItem(new Pair<>(20, 20));
        comboBox.addItem(new Pair<>(10, 20));
        comboBox.addItem(new Pair<>(20, 10));
        comboBox.addItem(new Pair<>(10, 10));
        comboBox.addItem(new Pair<>(10, 5));
        comboBox.addItem(new Pair<>(5, 10));
        comboBox.addItem(new Pair<>(5, 5));
        comboBox.setSelectedIndex(0);
        comboBox.setMaximumSize(new Dimension(100, 30));
    }

    private void updateRowsCols() {
        Pair<Integer, Integer> sizeMatrix = (Pair<Integer, Integer>) comboBox.getSelectedItem();
        rows = sizeMatrix.getKey();
        cols = sizeMatrix.getValue();
    }

    private void initNormalMatrix() {
        updateRowsCols();
        normalMatrix = new NormalMatrix<>(rows, cols, 0);
        InitiatorMatrix.randomFillMatrix(normalMatrix, rows * 2, 1100);
    }

    private void initSparseMatrix() {
        updateRowsCols();
        sparseMatrix = new SparseMatrix<>(rows, cols, 0);
        InitiatorMatrix.randomFillMatrix(sparseMatrix, rows * 2, 1100);
    }

    public void printMatrixOnSwingWindow(AbstractMatrix<Integer> mat, boolean enableBorder) {
        deleteAll(gridContainer);
        IDrawer<Integer> drawer = new SwingDrawer<>(gridContainer, enableBorder);
        mat.setDrawer(drawer);
        mat.draw();
    }

    public void printMatrixOnConsole(AbstractMatrix<Integer> mat, boolean enableBorder) {
        IDrawer<Integer> drawer = new ConsoleDrawer<>(enableBorder);
        mat.setDrawer(drawer);
        mat.draw();
        System.out.println();
    }

    private void onClick(IMatrix<Integer> matrix) {
        boolean enableBorder = jCheckBox.isSelected();
        printMatrixOnConsole((AbstractMatrix<Integer>) matrix, enableBorder);
        printMatrixOnSwingWindow((AbstractMatrix<Integer>) matrix, enableBorder);
        rootContainer.revalidate();
        rootContainer.repaint();
        pack();
    }

    class GenerateNormalHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            initNormalMatrix();
            onClick(normalMatrix);
        }
    }

    class GenerateSparseHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            initSparseMatrix();
            onClick(sparseMatrix);
        }
    }
}
