package org.cloud.tutorials.graph;


/**
 * This node class represents a cell in the CSV input file
 *
 */
public class Node  {
    private Type type;
    private Value value;

    public Node(String value) {
        this.setValue(value);
        this.setType(value);
        if (type == Type.NUMBER) {
            this.value.setNumberResult
                    (Double.valueOf(value));
        }
    }

    public Value getValue() {
        return value;
    }

    private void setValue(String  value) {
        this.value = new Value(value);
    }

    public Type getType() {
        return type;
    }

    private void setType(String value) {
        if (value.contains("#hl")) {
            this.type = Type.LINE;
        }
        else if (value.contains("#(prod")) {
            this.type = Type.PRODUCT;
        }
        else if (value.contains("#(sum")) {
            this.type = Type.SUM;
        }
        else if (value.matches("[0-9.]+")) {
            this.type = Type.NUMBER;
        }
        else {
            this.type = Type.STRING;
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "type=" + type +
                ", value=" + value +
                '}';
    }
}
