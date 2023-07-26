package org.cloud.tutorials.writer.impl;

import org.cloud.tutorials.graph.Grid;
import org.cloud.tutorials.graph.Node;
import org.cloud.tutorials.graph.Type;
import org.cloud.tutorials.writer.TextWriter;

import java.io.FileWriter;
import java.io.IOException;

public class TextWriterImpl implements TextWriter {

    @Override
    public void writeGridToText(String outputFile, Grid grid) throws IOException {
        FileWriter myWriter = new FileWriter(outputFile);

        try {
            Node[][] nodes = grid.getNodes();
            int col = nodes[0].length;

            for (Node[] rowNodes : nodes) {
                StringBuilder rowValues = new StringBuilder();
                for (int c = 0; c < col; c++) {
                    Node node = rowNodes[c];
                    StringBuilder cellValue = getCellOutput(node, grid.getColumnMaxWidth()[c]);
                    if (c == 0) {
                        rowValues.append(cellValue);
                    } else {
                        rowValues.append("|").append(cellValue);
                    }
                }
                myWriter.write(rowValues + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.err.println("An error occurred while trying to write to the output file");
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * This method returns the formatted output representation of the node
     * This will be written on the output text file
     *
     */
    private StringBuilder getCellOutput(Node cell, int maxWidth) {
        StringBuilder value;
        if (cell == null) {
            value = new StringBuilder();
            value.append(" ".repeat(Math.max(0, maxWidth)));
        } else if (cell.getType() == Type.STRING) {
            value = new StringBuilder(cell.getValue().getInput());
            int valueSize = value.length();
            value.append(" ".repeat(Math.max(0, maxWidth - valueSize)));
        } else if (cell.getValue().isCalculated()) {
            value = new StringBuilder(cell.getValue().getNumberResult().toString());
            StringBuilder dummy = new StringBuilder();
            dummy.append(" ".repeat(Math.max(0, maxWidth - value.length())));
            value = dummy.append(value);
        } else {
            // must be type line
            value = new StringBuilder();
            value.append("-".repeat(Math.max(0, maxWidth)));
        }
        return value;
    }
}
