package org.jugru.minijunit;

public class AssertionError extends Error {
    public AssertionError(Object expected, Object actual) {
        super("Expected - " + expected + ", but was - " + actual);
    }
}
