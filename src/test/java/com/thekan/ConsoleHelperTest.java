package com.thekan;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleHelperTest {

    @Test
    void print() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        System.out.print("hello");
        System.setOut(originalOut);
        
        assertEquals("hello", outContent.toString());
    }

//    @Test
//    void writeToFile() {
//    }
//
//    @Test
//    void readToFile() {
//    }
}
