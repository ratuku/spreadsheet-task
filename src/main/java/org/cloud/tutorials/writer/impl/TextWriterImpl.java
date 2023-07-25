package org.cloud.tutorials.writer.impl;

import org.cloud.tutorials.graph.Grid;
import org.cloud.tutorials.graph.Node;
import org.cloud.tutorials.writer.TextWriter;

import java.io.FileWriter;
import java.io.IOException;

public class TextWriterImpl implements TextWriter {
    @Override
    public void writeGraphTotext(String outputFilePath, Grid grid) throws IOException {
        FileWriter myWriter = new FileWriter(outputFilePath);

        try {
            Node[][] nodes = grid.getNodes();
            int row = nodes.length;
            int col = nodes[0].length;

            for (int r = 0; r < row; r++) {
                StringBuffer rowValues = new StringBuffer();
                rowValues.append(nodes[r][0].value.input);
                for (int c = 1; c < col; c++) {
                    rowValues.append("|").append(nodes[r][c].value.input);
                }
                myWriter.write(rowValues.toString());
            }
            myWriter.close();
        } catch (IOException e) {
            System.err.println("An error occurred.");
            e.printStackTrace();
            throw e;
        }
    }
}
