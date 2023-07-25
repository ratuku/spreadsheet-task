package org.cloud.tutorials.graph;

public class Value {

    private String input;
    private String stringResult;
    private Double numberResult;
    private boolean calculated;

    public Value (String inputValue) {
        this.input = inputValue;
    }

    public String getInput() {
        return input;
    }

    public Double getNumberResult() {
        return numberResult;
    }

    /**
     *  This property is only important for value of type prod or sum
     * @return
     */
    public Boolean isCalculated() {
        return calculated;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void setNumberResult(Double numberResult) {
        this.numberResult = numberResult;
        this.calculated = true;
    }

    @Override
    public String toString() {
        return "Value{" +
                "input='" + input + '\'' +
                ", stringResult='" + stringResult + '\'' +
                ", numberResult=" + numberResult +
                ", calculated=" + calculated +
                '}';
    }
}
