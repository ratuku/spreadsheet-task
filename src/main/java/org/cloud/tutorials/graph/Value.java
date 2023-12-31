package org.cloud.tutorials.graph;

public class Value {

    private final String input;
    private Double numberResult;
    private boolean calculated;

    public Value(String inputValue) {
        this.input = inputValue;
    }

    public String getInput() {
        return input;
    }

    public Double getNumberResult() {
        return numberResult;
    }

    /**
     * Have we calculated this value already?
     * Not important for cells of type String or Line
     *
     */
    public Boolean isCalculated() {
        return calculated;
    }

    public void setNumberResult(Double numberResult) {
        this.numberResult = numberResult;
        this.calculated = true;
    }

    @Override
    public String toString() {
        return "Value{" +
                "input='" + input + '\'' +
                ", numberResult=" + numberResult +
                ", calculated=" + calculated +
                '}';
    }
}
