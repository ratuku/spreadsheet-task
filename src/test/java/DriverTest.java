import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.cloud.tutorials.CommonConstants.TEST_INPUT_FILE;
class DriverTest {
    
    @Test
    @DisplayName("Test that when given a Test input file, the main method throws no exception")
    void testDriverMainMethod() throws IOException {
        
        Driver.main(TEST_INPUT_FILE,"DriverOutPut.txt");
    }
}