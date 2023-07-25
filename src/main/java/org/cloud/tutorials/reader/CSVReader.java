package org.cloud.tutorials.reader;

import org.cloud.tutorials.graph.Grid;

import java.io.FileNotFoundException;

/**
 * This class is used to read data in the CSV file and store it in a grid
 */
public interface CSVReader {
    Grid mapToGrid(String inputFileName) throws FileNotFoundException;
}
