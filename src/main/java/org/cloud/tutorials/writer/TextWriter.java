package org.cloud.tutorials.writer;

import org.cloud.tutorials.graph.Grid;

import java.io.IOException;

/**
 * This interface is used to read the data in a grid and display it in the output file
 */
public interface TextWriter {
    
    void writeGridToText(String outputFilePath, Grid grid) throws IOException;
}
