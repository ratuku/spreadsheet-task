package org.cloud.tutorials.reader.impl;

import org.cloud.tutorials.graph.Grid;
import org.cloud.tutorials.graph.Node;
import org.cloud.tutorials.reader.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CSVReaderImpl implements CSVReader {

    @Override
    public Grid mapToGraph(String inputFilePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(inputFilePath)).useDelimiter(",");

        int row = 0;
        int col = 0;
        Map<Integer, String[]> integerMap = new HashMap<>();
        while (scanner.hasNextLine()) {
            row++;
            String line = scanner.nextLine();
            String[] cells = line.split(",");
            integerMap.put(row, cells);
            if (cells.length > col) col = cells.length;
        }
        scanner.close();

        Grid grid = new Grid(row, col);
        grid.populate(integerMap);
        return grid;
    }
}