package org.jugru.readfiles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FileReadingExamplesTest {

    private final String filename = "test.txt";

    private final String fileContent = "Lorem ipsum dolor sit amet,\n" +
            " consectetur adipiscing elit,\n" +
            "  sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";

    @Test
    public void readUsingBufferedReaderTest(){
        String answer = FileReadingExamples.readUsingBufferedReader(filename);
        Assertions.assertEquals(fileContent, answer);

    }

    @Test
    public void readUsingFileReaderTest(){
        String answer = FileReadingExamples.readUsingFileReader(filename);
        Assertions.assertEquals(fileContent, answer);

    }

    @Test
    public void readUsingLinesTest(){
        String answer = FileReadingExamples.readUsingLines(filename);
        Assertions.assertEquals(fileContent, answer);

    }
}
