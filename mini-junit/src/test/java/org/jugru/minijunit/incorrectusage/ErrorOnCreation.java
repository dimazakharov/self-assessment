package org.jugru.minijunit.incorrectusage;

import org.jugru.minijunit.Test;

public class ErrorOnCreation {
    public ErrorOnCreation() {
        throw new RuntimeException();
    }

    @Test
    public void errorOnCreationTest() {

    }
}
