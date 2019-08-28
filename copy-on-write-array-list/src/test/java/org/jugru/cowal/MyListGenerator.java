package org.jugru.cowal;

import com.google.common.collect.testing.SampleElements;
import com.google.common.collect.testing.TestListGenerator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MyListGenerator implements TestListGenerator<String> {

    @Override
    public SampleElements<String> samples() {
        return new SampleElements<>(
                "1",
                "2",
                "3",
                "4",
                "5"

        );
    }

    @Override
    public List<String> create(Object... elementsAsObjects) {
        String[] castedElements = new String[elementsAsObjects.length];
        int i = 0;
        for (Object elementAsObject : elementsAsObjects) {
            castedElements[i++] = (String) elementAsObject;
        }
        return new CopyOnWriteArrayList<>(Arrays.asList(castedElements));
    }

    @Override
    public String[] createArray(int length) {
        return new String[length];
    }

    @Override
    public Iterable<String> order(List<String> insertionOrder) {
        return new CopyOnWriteArrayList<>(insertionOrder);
    }
}
