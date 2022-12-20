package com.thekan.check;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckPatternTest {

    @BeforeEach
    void setUp() {
        CheckPattern.widthOfCheckInChar = 20;
        CheckPattern.patternBody = "%-5s%-5.5s%-5s%5s";
        CheckPattern.patternInfoOneColumn = "%-20s";
        CheckPattern.patternInfoTwoColumn = "%-10s%10s";
        CheckPattern.patternForSeparators = "%-20s";
    }

    @Test
    void patternBody() {
        assertEquals("a    b    c        d", CheckPattern.patternBody("a", "b", "c", "d"));
        assertEquals("a    12345c        d", CheckPattern.patternBody("a", "12345678", "c", "d"));
    }

    @Test
    void patternInfoTest1() {
        assertEquals("ab                  ", CheckPattern.patternInfo("ab"));
        assertEquals("1234567891011121314 ", CheckPattern.patternInfo("1234567891011121314"));
        CheckPattern.patternInfoOneColumn = "* %-20s *";
        assertEquals("* ab                   *", CheckPattern.patternInfo("ab"));
    }

    @Test
    void patternInfoTest2() {
        assertEquals("ab                ab", CheckPattern.patternInfo("ab", "ab"));
        CheckPattern.patternInfoTwoColumn = "* %-10s%10s *";
        assertEquals("* ab                ab *", CheckPattern.patternInfo("ab", "ab"));
    }

    @Test
    void patternInfoCenter() {
        assertEquals("         a          ", CheckPattern.patternInfoCenter("a"));
        CheckPattern.widthOfCheckInChar = 5;
        CheckPattern.patternInfoOneColumn = "%-5s";
        assertEquals("  a  ", CheckPattern.patternInfoCenter("a"));
    }

    @Test
    void patternSeparators() {
        assertEquals("a                   ", CheckPattern.patternSeparators("a"));
        assertEquals("********************", CheckPattern.patternSeparators("********************"));
    }

    @Test
    void center() {
        assertEquals("    a", CheckPattern.center("a", 10));
        assertEquals(" a", CheckPattern.center("a", 3));
        assertEquals("a", CheckPattern.center("a", 2));
    }
}