package org.cloud.tutorials.writer.impl;

import org.cloud.tutorials.graph.Grid;
import org.cloud.tutorials.graph.Node;
import org.cloud.tutorials.graph.Type;
import org.cloud.tutorials.writer.TextWriter;

import java.io.FileWriter;
import java.io.IOException;

public class TextWriterImpl implements TextWriter {
    @Override
    public void writeGridToText(String outputFilePath, Grid grid) throws IOException {
        FileWriter myWriter = new FileWriter(outputFilePath);

        try {
            Node[][] nodes = grid.getNodes();
            int row = nodes.length;
            int col = nodes[0].length;

            for (int r = 0; r < row; r++) {
                StringBuilder rowValues = new StringBuilder();
                // for each cell in this row
                for (int c = 0; c < col; c++) {
                    Node node = nodes[r][0];
                    StringBuilder cellValue = getCellOutput(node, grid.getColumnMaxWidth()[c]);
                    if (c==0) {
                        rowValues.append(cellValue);
                    }
                    rowValues.append("|").append(cellValue);
                }
                myWriter.write(rowValues.toString() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.err.println("An error occurred.");
            e.printStackTrace();
            throw e;
        }
    }

    // todo: finish this... damn load-shedding
    private StringBuilder getCellOutput(Node cell, int maxWidth) {
        StringBuilder value;
        if (cell == null) {
            value = new StringBuilder();
            for (int i = 0; i < maxWidth; i++) {
                value.append(" ");
            }
        }
        else if (cell.getType() == Type.STRING) {
            value = new StringBuilder(cell.getValue().getInput());
            for (int i = 0; i < maxWidth - value.length(); i++) {
                value.append(" ");
            }
        }
        else if (cell.getValue().isCalculated()) {
            value = new StringBuilder(cell.getValue().getNumberResult().toString());
            StringBuilder dummy = new StringBuilder();
            for (int i = 0; i < maxWidth - value.length(); i++) {
                dummy.append(" ");
            }
            value = dummy.append(value);
        }
        else {
            // must be type line
            value = new StringBuilder();
            for (int i = 0; i < maxWidth; i++) {
                value.append("_");
            }
        }
        return value;
    }
}
