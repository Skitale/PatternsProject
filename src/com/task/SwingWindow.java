package com.task;

import com.task.commands.CommandManager;
import com.task.commands.impl.InitMatrix;
import com.task.commands.impl.MatrixSet;
import com.task.composites.HorizontalMatrixGroup;
import com.task.composites.VerticalMatrixGroup;
import com.task.decorators.RenumberingDecorator;
import com.task.drawers.ConsoleDrawer;
import com.task.drawers.IDrawer;
import com.task.drawers.SwingDrawer;
import com.task.matrixes.*;
import com.task.utils.InitiatorMatrix;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.task.utils.SwingUtils.deleteAll;

public class SwingWindow extends JFrame {
    private JButton generateSimpMatrixButton = new JButton("Generate the simple matrixes");
    private JButton generateSparMatrixButton = new JButton("Generate the sparse matrixes");
    private JButton generateVertGroupMatrixesButton = new JButton("Generate the V group");
    private JButton restoreButton = new JButton("Restore");
    private JButton renumberButton = new JButton("Renumber");
    private JButton showCommandMatrixButton = new JButton("Show command matrix");
    private JButton changeRandomCelButton = new JButton("Change random cel");
    private JButton undoButton = new JButton("Undo");
    private JCheckBox jCheckBox = new JCheckBox("enable border", true);
    private JComboBox<Pair<Integer, Integer>> comboBox = new JComboBox<>();
    private NormalMatrix<Integer> normalMatrix;
    private SparseMatrix<Integer> sparseMatrix;
    private VerticalMatrixGroup vGroup;
    private AMatrixBridge<Integer> lastMatrixDraw;
    private RenumberingDecorator decorator;
    private static NormalMatrix<Integer> matrixForUndoOperations;
    private int rows = 20;
    private int cols = 20;
    private Container gridContainer;
    private Container rootContainer;

    static {
        initMatrixForUndoOperation();
        CommandManager.getInstance().registryCommand(new InitMatrix(matrixForUndoOperations, 10, 10));
    }

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
        panel.add(generateVertGroupMatrixesButton);
        panel.add(restoreButton);
        panel.add(renumberButton);
        panel.add(comboBox);
        panel.add(jCheckBox);
        rootContainer.add(panel);
        rootContainer.add(Box.createVerticalStrut(5));
        addAllListeners();

        Box downPanel = Box.createHorizontalBox();
        downPanel.add(showCommandMatrixButton); downPanel.add(changeRandomCelButton); downPanel.add(undoButton);
        gridContainer = new Panel();
        rootContainer.add(gridContainer);
        rootContainer.add(downPanel);
    }

    private void addAllListeners(){
        generateSimpMatrixButton.addMouseListener(new GenerateNormalHandler());
        generateSparMatrixButton.addMouseListener(new GenerateSparseHandler());
        generateVertGroupMatrixesButton.addMouseListener(new GenerateVGroupHandler());
        renumberButton.addMouseListener(new RenumberMatrixHandler());
        restoreButton.addMouseListener(new RestoreFromRenumberingMatrixHandler());
        showCommandMatrixButton.addMouseListener(new ShowCommandMatrixHandler());
        changeRandomCelButton.addMouseListener(new ChangeRandomCelMatrixHandler());
        undoButton.addMouseListener(new UndoMatrixHandler());
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

    private static void initMatrixForUndoOperation() {
        matrixForUndoOperations = new NormalMatrix<>(10, 10, 0);
    }

    private void initSparseMatrix() {
        updateRowsCols();
        sparseMatrix = new SparseMatrix<>(rows, cols, 0);
        InitiatorMatrix.randomFillMatrix(sparseMatrix, rows * 2, 1100);
    }

    private void initVertGroupMatrix(){
        updateRowsCols();
        vGroup = new VerticalMatrixGroup(null);
        List<Integer> partsW = divideSizeIntoParts(cols, 3);
        List<Integer> partsH = divideSizeIntoParts(rows, 3);
        HorizontalMatrixGroup wGroup = new HorizontalMatrixGroup(null);

        AMatrixBridge<Integer> m1W = new SparseMatrix<>(partsH.get(0), partsW.get(0), 0);
        InitiatorMatrix.randomFillMatrix(m1W, partsH.get(0) * 2, 1100);

        AMatrixBridge<Integer> m2W = new NormalMatrix<>(partsH.get(0), partsW.get(1), 0);
        InitiatorMatrix.randomFillMatrix(m2W, partsH.get(0) * 2, 1100);

        AMatrixBridge<Integer> m3W = new SparseMatrix<>(partsH.get(0), partsW.get(2), 0);
        InitiatorMatrix.randomFillMatrix(m3W, partsH.get(0) * 2, 1100);

        wGroup.add(m1W); wGroup.add(m2W); wGroup.add(m3W);

        AMatrixBridge<Integer> m2H = new SparseMatrix<>(partsH.get(1), partsW.get(0) + partsW.get(1), 0);
        InitiatorMatrix.randomFillMatrix(m2H, partsH.get(1) * 2, 1100);

        AMatrixBridge<Integer> m3H = new NormalMatrix<>(partsH.get(2), partsW.get(0) + partsW.get(2), 0);
        InitiatorMatrix.randomFillMatrix(m3H, partsH.get(2) * 2, 1100);

        vGroup.add(wGroup); vGroup.add(m2H); vGroup.add(m3H);
    }

    private List<Integer> divideSizeIntoParts(int size, int numParts){
        List<Integer> sizeParts = new ArrayList<>();
        if(numParts > size) {
            sizeParts.add(size);
            return sizeParts;
        }
        int tmpSize = 0;
        double div = new BigDecimal(size).divide(new BigDecimal(numParts), BigDecimal.ROUND_HALF_UP).doubleValue();
        int divI = (int) div;
        for(int i = 0; i < numParts; i++){
            if(tmpSize + divI <= size){
                tmpSize += divI;
                sizeParts.add(divI);
            } else if(i == numParts - 1){
                sizeParts.add(size - tmpSize);
                tmpSize += size - tmpSize;
            }
        }

        if(size > tmpSize){
            int lastSize = sizeParts.get(sizeParts.size() - 1);
            int delta = size - tmpSize;
            sizeParts.set(sizeParts.size() - 1, lastSize + delta);
        }

        int sum = 0;
        for(Integer i : sizeParts){
            sum += i;
        }

        if(sum != size){
            throw new UnsupportedOperationException("check divide size into parts");
        } else if(sizeParts.size() != numParts) {
            throw new UnsupportedOperationException("return wrong num parts");
        }
        return sizeParts;
    }

    public void printMatrixOnSwingWindow(AMatrixBridge<Integer> mat, boolean enableBorder) {
        deleteAll(gridContainer);
        IDrawer<Integer> drawer = new SwingDrawer<>(gridContainer, enableBorder);
        mat.setDrawer(drawer);
        mat.draw();
    }

    public void printMatrixOnConsole(AMatrixBridge<Integer> mat, boolean enableBorder) {
        IDrawer<Integer> drawer = new ConsoleDrawer<>(enableBorder);
        mat.setDrawer(drawer);
        mat.draw();
        System.out.println();
    }

    private void onClick(IMatrix<Integer> matrix) {
        boolean enableBorder = jCheckBox.isSelected();
        printMatrixOnConsole((AMatrixBridge<Integer>) matrix, enableBorder);
        printMatrixOnSwingWindow((AMatrixBridge<Integer>) matrix, enableBorder);
        lastMatrixDraw = (AMatrixBridge<Integer>) matrix;
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

    class GenerateVGroupHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            initVertGroupMatrix();
            onClick(vGroup);
        }
    }

    class RenumberMatrixHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(lastMatrixDraw == null) return;
            int i1 = (int) (Math.random() * lastMatrixDraw.getRows());
            int i2 = (int) (Math.random() * lastMatrixDraw.getRows());
            int j1 = (int) (Math.random() * lastMatrixDraw.getCols());
            int j2 = (int) (Math.random() * lastMatrixDraw.getCols());
            int rowsOrCols = (int) (Math.random() * 2);
            if(rowsOrCols == 0){
                decorator = new RenumberingDecorator(lastMatrixDraw, i1, i2, true);
            } else {
                decorator = new RenumberingDecorator(lastMatrixDraw, j1, j2, false);
            }
            onClick(decorator);
        }
    }

    class RestoreFromRenumberingMatrixHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(decorator == null) return;
            AMatrixBridge<Integer> sourceMat = decorator.getSourceObject();
            onClick(sourceMat);
            decorator = null;
        }
    }

    class ShowCommandMatrixHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            if(matrixForUndoOperations == null) return;
            onClick(matrixForUndoOperations);
        }
    }

    class ChangeRandomCelMatrixHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            int i = (int) (Math.random() * matrixForUndoOperations.getRows());
            int j = (int) (Math.random() * matrixForUndoOperations.getCols());
            int value = (int) (Math.random() * 1100);
            MatrixSet msCommand = new MatrixSet(matrixForUndoOperations, i, j, value);
            msCommand.execute();
            onClick(matrixForUndoOperations);
        }
    }

    class UndoMatrixHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            CommandManager.getInstance().undo();
            onClick(matrixForUndoOperations);
        }
    }
}
