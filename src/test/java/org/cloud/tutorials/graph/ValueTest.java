package org.cloud.tutorials.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ValueTest {

    @Test
    @DisplayName("Assert that calculated field is true whenever the numberResult is set")
    void setNumberResult() {
        Value value = new Value("3.4");
        value.setNumberResult(3.4d);

        Assertions.assertEquals(true, value.isCalculated());
    }
}