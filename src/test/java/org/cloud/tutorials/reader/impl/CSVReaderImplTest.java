package org.cloud.tutorials.reader.impl;

import org.cloud.tutorials.graph.Grid;
import org.cloud.tutorials.graph.Type;
import org.cloud.tutorials.reader.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.cloud.tutorials.CommonConstants.TEST_INPUT_FILE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CSVReaderImplTest {

    @Test
    @DisplayName("Test that this class can read from scanner and populate grid correctly")
    void mapToGrid() throws FileNotFoundException {
        CSVReader csvReader = new CSVReaderImpl();
        Grid grid = csvReader.mapToGrid(TEST_INPUT_FILE);
        assertEquals(8, grid.getNodes().length);
        assertEquals(4, grid.getNodes()[0].length);
        assertEquals(Type.NUMBER, grid.getNodes()[7][0].getType());
    }
}