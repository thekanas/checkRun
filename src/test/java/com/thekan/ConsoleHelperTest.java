package com.thekan;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleHelperTest {

    @Test
    void print() {
        private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        private final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        System.out.print("hello");
        System.setOut(originalOut);
        
        assertEquals("hello", outContent.toString());
    }

    @Test
    void writeToFile() {
    }

    @Test
    void readToFile() {
    }
}
