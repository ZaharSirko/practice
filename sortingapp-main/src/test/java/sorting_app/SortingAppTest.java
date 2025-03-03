package sorting_app;

import org.example.sorting_app.SortingApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SortingAppTest {

    private final String[] input;
    private final String expectedOutput;

    public SortingAppTest(String[] input, String expectedOutput) {
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    @Parameterized.Parameters(name = "Test {index}: {0} -> {1}")
    public static Object[][] testCases() {
        return new Object[][]{
                {new String[]{"8", "3", "7", "1"}, "Sorted number: [1, 3, 7, 8]\n"},
                {new String[]{"5"}, "Please enter up to 10 and minimum 4 integer numbers\n"},
                {new String[]{"10", "2", "8", "6", "4", "3", "1", "7", "9", "5"}, "Sorted number: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]\n"},
                {new String[]{"-3", "-1", "-2","-4"}, "Sorted number: [-4, -3, -2, -1]\n"},
                {new String[]{}, "Please enter up to 10 and minimum 4 integer numbers\n"},
                {new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"}, "Entered more than 10 integer numbers\n"},
                {new String[]{"a", "1", "b","2.7"}, "Error: all number must be integer\n"},
        };
    }

    @Test
    public void testSortMethod() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        SortingApp.sort(input);

        String actualOutput = outputStream.toString().trim();
        assertEquals(expectedOutput.trim(), actualOutput);

        System.setOut(System.out);
    }
}
