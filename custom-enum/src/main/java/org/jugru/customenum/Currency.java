package org.jugru.customenum;

import java.util.HashMap;

public class Currency implements Comparable<Currency> {
    private static HashMap<String, Currency> all = new HashMap<>();
    private static int ordinalCounter = 0;

    public static Currency RUB = new Currency("RUB");
    public static Currency USD = new Currency("USD");
    public static Currency EUR = new Currency("EUR"){

    };


    private final String name;
    private final int ordinal;



    private Currency(String name) {
        this.name = name;
        this.ordinal = ordinalCounter++;

        if(name == null)
            throw new IllegalArgumentException("Name can not be null");
        if(all.containsKey(name))
            throw new IllegalArgumentException("Duplicate name - " + name);
        all.put(this.name, this);
    }

    public final String name() {
        return name;
    }

    public final int ordinal() {
        return ordinal;
    }

    @Override
    public int compareTo(Currency o) {
        return ordinal - o.ordinal;
    }

    public final Class<Currency> getDeclaringClass() {
        Class<?> clazz = getClass();
        Class<?> zuper = clazz.getSuperclass();
        return (zuper == Enum.class) ? (Class<Currency>)clazz : (Class<Currency>)zuper;
    }


    public static Currency valueOf(String name) throws IllegalArgumentException {
        Currency currency = all.get(name);
        if (currency == null)
            throw new IllegalArgumentException("No currency with name - " + name);
        return currency;
    }

    public static Currency[] values() {
        return all.values().toArray(new Currency[all.values().size()]);
    }
}
