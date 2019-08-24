package org.jugru.minijunit.correctusage;

import org.jugru.minijunit.Assertions;
import org.jugru.minijunit.Test;

public class CorrectUsageTest {


    public CorrectUsageTest() {
    }

    @Test
    public void successTest(){
        Assertions.assertEquals("str", "str");
    }

    @Test
    public void failTest(){
        Assertions.assertTrue(false);
    }

    @Test
    public void exceptionTest(){
        throw new RuntimeException("Exception message");
    }
}
