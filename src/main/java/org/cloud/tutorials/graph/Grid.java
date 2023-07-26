package org.cloud.tutorials.graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This grid class represents the data received and stores it in an efficient way.
 * It also efficiently calculates and formats data based on the rule received
 * <p>
 * It should be able to perform calculations like (sum or prod) in constant time
 * We populate the grid once. Takes O(row * col)
 * We traverse the grid once more to calculate and update grid cell values
 *
 */
public class Grid {

    Node[][] nodes;

    int[] columnMaxWidth;
    boolean calculated;
    boolean populated;
    Set<Node> visitedNodes;

    public Grid(int row, int col) {
        nodes = new Node[row][col];
        columnMaxWidth = new int[col];
        visitedNodes = new HashSet<>();
        for (int i = 0; i < col ; i++) {
            // min cell width
            columnMaxWidth[i] = 10;
        }
    }

    public Node[][] getNodes() {
        return nodes;
    }

    public int[] getColumnMaxWidth() {
        return columnMaxWidth;
    }

    /**
     * This method populates the grid
     *
     * @param map This map's key is the row number from 1.
     * The value is an array of th cell values from the CSV input file
     */
    public void populate(Map<Integer, String[]> map) {
        if (populated) return;
        int row = nodes.length;

        try {
            for (int r = 0; r < row; r++) {
                String[] rowValues = map.get(r + 1);
                int currentRowSize = rowValues.length;
                if (rowValues.length == 0) continue;
                for (int c = 0; c < currentRowSize; c++) {
                    Node node = new Node(rowValues[c]);
                    if (node.getType() != Type.SUM && node.getType() != Type.PRODUCT) {
                        columnMaxWidth[c] = Math.max(columnMaxWidth[c], node.getValue().getInput().length());
                    }
                    nodes[r][c] = node;
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("[NumberFormatException] Cells are in an incorrect format. \nEnsure no spaces in front of '#' key for prod and sum cells.\n");
            throw e;
        }
        populated = true;
    }

    /**
     * This method calculates the result of all Product and Sum cells within the grid
     */
    public void calculate() {
        if (calculated) return;
        try {
            for (Node[] rowNodes : nodes) {
                int col = rowNodes.length;
                for (int c = 0; c < col; c++) {
                    if (rowNodes[c] == null) continue;
                    Node node = rowNodes[c];
                    if ((node.getType() == Type.PRODUCT || node.getType() == Type.SUM)
                            && !node.getValue().isCalculated()) {
                        calculateNode(node, c);
                    }
                }
                calculated = true;
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Index out of bounds. Please check that Prod and Sum cells depends on valid indices");
            throw e;
        }
    }

    /**
     * This method calculates the result of the Sum or Prod cell
     * This function throws a {@link RuntimeException} when there's cyclical dependency, aka
     * infinite loop. It also throws an exception when the dependent cell is of type Line or String.
     */
    public void calculateNode(Node node, int col) {
        if (node.getType() == Type.STRING || node.getType() == Type.LINE) {
            throw new RuntimeException("A prod or sum cell depends on non-numerical cell");
        }
        if (visitedNodes.contains(node) && !node.getValue().isCalculated()) {
            throw new RuntimeException("Infinite Loop. Prod or Sum cells depends on each other");
        }

        visitedNodes.add(node);
        if (node.getType() == Type.SUM) {
            calculateSum(node, col);
        }
        if (node.getType() == Type.PRODUCT) {
            calculateProduct(node, col);
        }
        columnMaxWidth[col] = Math.max(columnMaxWidth[col], node.getValue().getNumberResult().toString().length());
    }

    /**
     * This method performs the sum operation of this cell
     * And sets the result to the numberResult attribute
     *
     */
    public void calculateSum(Node node, int col) {
        double result = 0;
        String[] cells = node.getValue().getInput().split(" ");
        for (int i = 1; i < cells.length; i++) {
            result += getNode(cells[i], col).getValue().getNumberResult();
        }
        node.getValue().setNumberResult(result);
    }

    /**
     * This method performs the prod operation of this node
     * And sets the result to the numberResult attribute
     *
     */
    public void calculateProduct(Node node, int col) {
        double result = 1;
        String[] cells = node.getValue().getInput().split(" ");
        for (int i = 1; i < cells.length; i++) {
            result *= getNode(cells[i], col).getValue().getNumberResult();
        }
        node.getValue().setNumberResult(result);
    }

    /**
     *
     * This function takes in CSV cell and returns the node
     * E.g A8 to the node in the grid that represents A8
     * If the current node depends on another prod or sum node
     * Then we calculate that node first and then return to calculate
     * this current node
     *
     * @param cell e.g C7
     * @return {@link Node}
     */
    public Node getNode(String cell, int col) {
        int colIndex = (int) cell.charAt(0) - 65;
        int rowIndex =  Integer.parseInt(cell.substring(1, 2))-1;
        Node _node = nodes[rowIndex][colIndex];
        if (_node.getType() != Type.NUMBER) {
            calculateNode(_node, col); // here we are assuming type Prod or Sum
        }
        return _node;
    }

    @Override
    public String toString() {
        return "Grid{" +
                "nodes=" + Arrays.toString(nodes) +
                ", columnMaxWidth=" + Arrays.toString(columnMaxWidth) +
                '}';
    }
}
