package org.jugru.minijunit.incorrectusage;

import org.jugru.minijunit.Test;

public class IncorrectMethods {

    @Test
    public void withArgumentsTest(Object o){

    }

    @Test
    private void privateTest(){

    }

    @Test
    private Object withReturnTypeTest(){
        return null;
    }
}
