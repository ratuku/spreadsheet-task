package org.cloud.tutorials.graph;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NodeTest {

    @Test
    @DisplayName("Test that the Node populates its value field as expected ")
    public void testNodeValue() {
        String value = "JRandomump 3.2" ;
        Node node = new Node(value);

        assertEquals(value, node.getValue().getInput());
    }

    @Test
    @DisplayName("Test that the Node is of Type String")
    public void testNodeTypeOfString() {
        Node node = new Node("Jump 3.2");
        assertEquals(Type.STRING, node.getType());
    }

    @Test
    @DisplayName("Test that the Node is of Type Line")
    public void testNodeTypeLine() {
        Node node = new Node("#hl");
        assertEquals(Type.LINE, node.getType());
    }

    @Test
    @DisplayName("Test that the Node is of Type Sum")
    public void testNodeTypeOfSum() {
        Node node = new Node("#(sum A6 A7 B6)");
        assertEquals(Type.SUM, node.getType());
    }

    @Test
    @DisplayName("Test that the Node is of Type Prod")
    public void testNodeTypeOfProd() {
        Node node = new Node("#(prod A6 A7 B6)");
        assertEquals(Type.PRODUCT, node.getType());
    }

    @Test
    @DisplayName("Test that the Node populates its fields as expected and of Type String")
    public void testNodeTypeString() {
        String value = "6.2" ;
        Node node = new Node(value);

        assertEquals(Type.NUMBER, node.getType());
    }

    @Test
    @DisplayName("Test that the Node populates its numberResult field as expected when type id Number")
    public void testNodeNumberResult() {
        String value = "9.0" ;
        Node node = new Node(value);

        assertEquals(9.0d, node.getValue().getNumberResult());
    }

}