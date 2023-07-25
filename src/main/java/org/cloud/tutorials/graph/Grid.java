package org.cloud.tutorials.graph;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.lang.Double.parseDouble;

/**
 * This grid class represents the data received and stores it in an efficient way.
 * It also efficiently calculates and formats data based on the rule received
 *
 * It should be able to perform calculations like (sum or prod) in constant time
 * We populate the grid once. Takes O(row * col)
 * We traverse the grid once more to calculate and update grid values
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
    }

    public Node[][] getNodes() {
        return nodes;
    }

    public void populate(Map<Integer, String[]> map) {
        if (populated) return;
        int row = nodes.length;

        for (int r = 0; r < row; r++) {
            String[] rowValues = map.get(r + 1); // keys are row index plus 1
            int currentRowSize = rowValues.length;
            if (rowValues.length == 0) continue;
            for (int c = 0; c < currentRowSize; c++) {
                Node node = new Node(rowValues[c]);
                System.out.print(" r: " + r + " c: " + c);
                System.out.print(" value: " + node.getValue().getInput() + " type " + node.getType().name());
                columnMaxWidth[c] = Math.max(columnMaxWidth[c], node.getValue().getInput().length());
                nodes[r][c] = node;
            }
            System.out.println();
        }
        for (int i = 0; i < columnMaxWidth.length; i++) {
            System.out.println("col: " + i + " " + columnMaxWidth[i]);
        }
        populated = true;
    }

    public void calculate() {
        if (calculated) return;
        int row = nodes.length;

        for (int r = 0; r < row; r++) {
            int col = nodes[r].length;
            for (int c = 0; c < col; c++) {
                if (nodes[r][c] == null) continue;
                Node node = nodes[r][c];
                System.out.println("Calculate cell " + r + "-" + c + " Input: " + node.getValue().getInput() +
                        " cal:" + node.getValue().getNumberResult());
                if ((node.getType() == Type.PRODUCT || node.getType() == Type.SUM)
                        && !node.getValue().isCalculated()) {
                    calculateNode(node, c);
                }
                System.out.println("cell " + r + "-" + c + " Input: " + node.getValue().getInput() +
                        " cal:" + node.getValue().getNumberResult());
            }
            calculated = true;
        }
    }

    public void calculateNode(Node node, int col) {
        // Have we been here before
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
        System.out.println("node >> " + node);
        columnMaxWidth[col] = Math.max(columnMaxWidth[col], node.getValue().getNumberResult().toString().length());
    }

    public void calculateSum(Node node, int col) {
        System.out.println("calculateSum: " + node.getValue());
        double result = 0;
        String[] cells = node.getValue().getInput().split(" ");
        for (int i = 1; i < cells.length; i++) {
            System.out.println("cell: " + cells[i]);
            int colIndex = (int) cells[i].charAt(0) - 65;
            int rowIndex =  Integer.parseInt(cells[i].substring(1, 2))-1;
            System.out.println("rowIndex: " + rowIndex + " colIndex: " + colIndex);
            Node _node = nodes[rowIndex][colIndex];
            if (_node.getType() != Type.NUMBER) {
                calculateNode(_node, col); // here we are assuming prod or sum type
            }
            result += _node.getValue().getNumberResult();
        }
        node.getValue().setNumberResult(result);
        System.out.println("result of prod: " + node.getValue());
    }

    public void calculateProduct(Node node, int col) {
        System.out.println("calculateProduct: " + node.getValue());
        double result = 1;
        String[] cells = node.getValue().getInput().split(" ");
        for (int i = 1; i < cells.length; i++) {
            System.out.println("cell: " + cells[i]);
            int colIndex = (int) cells[i].charAt(0) - 65;
            int rowIndex =  Integer.parseInt(cells[i].substring(1, 2))-1;
            System.out.println("rowIndex: " + rowIndex + " colIndex: " + colIndex);
            Node _node = nodes[rowIndex][colIndex];
            if (_node.getType() != Type.NUMBER) {
                calculateNode(_node, col); // here we are assuming type prod sum
            }
            result *= _node.getValue().getNumberResult();
        }
        node.getValue().setNumberResult(result);
        System.out.println("result of prod: " + node.getValue());
    }
}
