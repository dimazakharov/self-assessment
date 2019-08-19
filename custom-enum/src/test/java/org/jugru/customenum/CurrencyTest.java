package org.jugru.customenum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CurrencyTest {

    @Test
    public void NameTest() {
        assertEquals("RUB", Currency.RUB.name());
    }

    @Test
    public void ordinalTest() {
        assertEquals(1, Currency.USD.ordinal());
    }

    @Test
    public void declaringClassTest() {
        assertEquals(Currency.USD.getClass(), Currency.EUR.getDeclaringClass());
    }

    @Test
    public void valueOfTest() {
        Currency actual = Currency.valueOf("EUR");
        assertEquals(Currency.EUR, actual);
    }


    @Test
    public void valueOfFailTest() {
        assertThrows(IllegalArgumentException.class, () -> Currency.valueOf("___"));
    }
}
