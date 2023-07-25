import org.cloud.tutorials.graph.Grid;
import org.cloud.tutorials.reader.CSVReader;
import org.cloud.tutorials.writer.TextWriter;
import org.cloud.tutorials.reader.impl.CSVReaderImpl;
import org.cloud.tutorials.writer.impl.TextWriterImpl;

import java.io.IOException;

public class Driver {

    public static void main(String ... args) throws IOException {
        // String inputFilePath = args[0];
        // String outputFilePath = args[1];
        String inputFilePath = "input.csv";
        String outputFilePath = "output.txt";

        // Map to CSV input to Graph
        CSVReader csvReader = new CSVReaderImpl();
        Grid grid = csvReader.mapToGrid(inputFilePath);
        grid.calculate();

        // output
        TextWriter textWriter = new TextWriterImpl();
        textWriter.writeGridToText(outputFilePath, grid);
    }

}
