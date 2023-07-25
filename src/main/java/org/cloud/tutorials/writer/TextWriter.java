package org.cloud.tutorials.writer;

import org.cloud.tutorials.graph.Grid;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface TextWriter {

    void writeGraphTotext(String outputFilePath, Grid grid) throws IOException;
}
