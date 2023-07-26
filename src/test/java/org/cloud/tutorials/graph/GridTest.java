package org.cloud.tutorials.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GridTest {

    private static Grid grid;
    private static Map<Integer, String[]> map;

    @BeforeEach
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
    }

    @Test
    @DisplayName("Test that the Grid class can populate a grid correctly")
    void testPopulate() {
        // Grid is already populated in setup

        assertEquals(2,grid.getNodes()[1][0].getValue().getNumberResult());
        assertEquals("#hl",grid.getNodes()[0][3].getValue().getInput());
        assertEquals("#(sum A2 C2)",grid.getNodes()[2][2].getValue().getInput());
        assertEquals(Type.SUM,grid.getNodes()[2][2].getType());
        assertEquals(Type.STRING,grid.getNodes()[0][2].getType());
    }

    @Test
    @DisplayName("Test that grid can perform sum operation")
    void testSumOperation(){
        grid.calculate();
        assertEquals(5, grid.getNodes()[2][2].getValue().getNumberResult());
    }

    @Test
    @DisplayName("Test that grid can perform prod operation")
    void testProdOperation(){
        grid.calculate();
        assertEquals(3, grid.getNodes()[1][2].getValue().getNumberResult());
    }

    @Test
    @DisplayName("Test that the Grid throws IndexOutOfBoundsException when cells points to invalid cells")
    void testPopulateThrowsIndexOutOfBoundsException() {
        Grid _grid = new Grid(1,1);
        _grid.populate(Map.of(1, new String[]{"#(sum A0)"}));
        assertThrows( RuntimeException.class, _grid::calculate);
    }

    @Test
    @DisplayName("Test that Grid throws an exception when there's an infinite loop")
    void testInfiniteLoopScenario(){
        Grid _grid = new Grid(1,1);
        _grid.populate(Map.of(1, new String[]{"#(sum A1)"}));
        assertThrows( RuntimeException.class, _grid::calculate);
    }

    @Test
    @DisplayName("Test that Grid throws a RuntimeException when a Product or Sum cell depends on a cell of type String or Line")
    void testErrorOperationalCellDependsOnNonNumericCell(){
        Grid _grid = new Grid(1,2);
        _grid.populate(Map.of(1, new String[]{"#(sum B1)", "#hl"}));
        assertThrows( RuntimeException.class, _grid::calculate);
    }
}