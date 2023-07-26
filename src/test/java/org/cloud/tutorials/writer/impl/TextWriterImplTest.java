package org.cloud.tutorials.writer.impl;

import org.cloud.tutorials.graph.Grid;
import org.cloud.tutorials.writer.TextWriter;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.cloud.tutorials.CommonConstants.EXPECTED_OUTPUT_FILE;
import static org.cloud.tutorials.CommonConstants.TEST_WRITER_OUTPUT_FILE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TextWriterImplTest {

    private static Grid grid;
    private static Map<Integer, String[]> map;
    private TextWriter textWriter;

    @BeforeAll
    void setupGrid() {
        map = new HashMap<>();
        map.put(1, new String[] {
                "Values", "Factor","","#hl"
        });
        map.put(2, new String[] {
                "2", "1.5", "#(prod A2 B2)", ""
        });
        map.put(3, new String[] {
                "3.2","2","#(sum A2 C2)",""
        });
        map.put(4, new String[] {
                "4.5","2.5","","#(prod A3 B4)"
        });
        grid = new Grid(4, 4);
        grid.populate(map);
        grid.calculate();
    }

    @Test
    @DisplayName("Test that we are able to convert a grid that is calculated already to text. In the correct format")
    void writeGridToText() throws IOException {
        textWriter = new TextWriterImpl();
        textWriter.writeGridToText(TEST_WRITER_OUTPUT_FILE, grid);

        Scanner s1 = new Scanner(new File(EXPECTED_OUTPUT_FILE)).useDelimiter("\n");
        Scanner s2 = new Scanner(new File(TEST_WRITER_OUTPUT_FILE)).useDelimiter("\n");
        while (s1.hasNextLine() && s2.hasNextLine()) {
            assertEquals(s1.nextLine(), s2.nextLine());
        }
        s1.close();
        s2.close();
    }
}