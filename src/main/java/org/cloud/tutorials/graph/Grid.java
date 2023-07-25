package org.cloud.tutorials.graph;

import java.util.Map;

public class Grid {

    Node[][] nodes;
    int[] columnMaxWidth;
    boolean calculated;
    boolean populated;

    public Grid(int row, int col) {
        nodes = new Node[row][col];
        columnMaxWidth = new int[col];
    }

    public void calculate() {
        if (!calculated) {
            // calculate the grid
        }
        calculated = true;
    }

    public Node[][] getNodes() {
        return nodes;
    }

    public void populate(Map<Integer, String[]> map) {
        int row = nodes.length;
        int col = nodes[0].length;

        for (int r = 0; r < row; r++) {
            String[] rowValues = map.get(r+1);
            System.out.println("rowValues: " + rowValues.length);
            for (int c = 0; c < col; c++) {
                Node node = new Node();
                String inputValue = rowValues[c];
                if (inputValue != null) {
                    node.value.input = rowValues[c];
                    nodes[r][c] = node;
                }
            }
        }

        populated = true;
    }
}
