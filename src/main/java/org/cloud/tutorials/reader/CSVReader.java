package org.cloud.tutorials.reader;

import org.cloud.tutorials.graph.Grid;

import java.io.FileNotFoundException;

public interface CSVReader {
    Grid mapToGraph(String inputFileName) throws FileNotFoundException;
}
