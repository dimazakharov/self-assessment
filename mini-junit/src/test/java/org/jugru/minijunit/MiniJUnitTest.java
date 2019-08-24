package org.jugru.minijunit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MiniJUnitTest {

    @Test
    public void miniJUnitCorrectUsageTest() throws Exception {
        TestResult result = new MiniJUnit().test("org.jugru.minijunit.correctusage");
        assertEquals(1, result.getPassed());
        assertEquals(2, result.getFailed());

        assertTrue(result
                .getDetails()
                .contains(
                        new TestDetail("org.jugru.minijunit.correctusage.CorrectUsageTest",
                                "failTest",
                                false,
                                "Expected - true, but was - false")));


    }

    @Test
    public void miniJUnitIncorrectUsageTest() throws Exception {
        TestResult result = new MiniJUnit().test("org.jugru.minijunit.incorrectusage");
        assertEquals(0, result.getPassed());
        assertEquals(1, result.getFailed());

        assertTrue(result
                .getDetails()
                .contains(
                        new TestDetail("org.jugru.minijunit.incorrectusage.ErrorOnCreation",
                                "errorOnCreationTest",
                                false,
                                "Can't create object")));


    }
}
