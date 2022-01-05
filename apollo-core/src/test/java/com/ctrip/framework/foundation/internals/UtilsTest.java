package com.ctrip.framework.foundation.internals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Josh Knopp(https://github.com/joshknopp)
 */
class UtilsTest {
    @Nested
    class IsBlank {
        @Test
        void trueGivenNull() {
            assertTrue(Utils.isBlank(null));
        }

        @Test
        void trueGivenEmpty() {
            assertTrue(Utils.isBlank(""));
        }

        @Test
        void trueGivenWhitespace() {
            assertTrue(Utils.isBlank("   "));
        }

        @Test
        void falseGivenLoremIpsum() {
            String value = "Lorem Ipsum";
            assertFalse(Utils.isBlank("Lorem Ipsum"));
        }

        @Test
        void falseGivenWhitepsacePadded() {
            assertFalse(Utils.isBlank("   Lorem Ipsum   "));
        }
    }

    @Nested
    class IsOsWindows {
        private String actualOsName;

        @BeforeEach
        void setUp() {
            actualOsName = System.getProperty("os.name");
        }

        @AfterEach
        void tearDown() {
            System.setProperty("os.name", actualOsName);
        }

        @Test
        void trueGivenWindows10() {
            System.setProperty("os.name", "Windows 10");
            assertTrue(Utils.isOSWindows());
        }

        @Test
        void falseGivenMacOsX() {
            System.setProperty("os.name", "Mac OS X");
            assertFalse(Utils.isOSWindows());
        }

        @Test
        void falseGivenBlank() {
            System.setProperty("os.name", "");
            assertFalse(Utils.isOSWindows());
        }

        // Explicitly calling out case sensitivity; author @nobodyiam should confirm whether this is intentional
        @Test
        void falseGivenAllUppercaseWindows() {
            System.setProperty("os.name", "WINDOWS 10");
            assertFalse(Utils.isOSWindows());
        }

        @Test
        void falseGivenAllLowercaseWindows() {
            System.setProperty("os.name", "windows 10");
            assertFalse(Utils.isOSWindows());
        }
    }
}
