package org.cloud.tutorials.reader.impl;

import org.cloud.tutorials.graph.Grid;
import org.cloud.tutorials.reader.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CSVReaderImpl implements CSVReader {

    /**
     *
     * This implementation reads the CSV once and stores each row in a Map.
     * This map is passed to Grid and used to populate Grid
     * By going through the CSV here we will figure out the dimension of the grid row * col
     * Since each rows may have different number of columns, col here is the maximum number of columns found per row
     *
     * @param inputFilePath
     * @return
     * @throws FileNotFoundException
     */
    public Grid mapToGrid(String inputFilePath) throws FileNotFoundException {
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
