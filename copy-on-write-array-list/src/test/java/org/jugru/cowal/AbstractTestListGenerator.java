package org.jugru.cowal;

import com.google.common.collect.testing.SampleElements;
import com.google.common.collect.testing.TestListGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractTestListGenerator implements TestListGenerator<String> {

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
        return createListFromElements(Arrays.asList(castedElements));
    }

    abstract List<String> createListFromElements(List<String> elements);

    @Override
    public String[] createArray(int length) {
        return new String[length];
    }

    @Override
    public Iterable<String> order(List<String> insertionOrder) {
        return new CopyOnWriteArrayList<>(insertionOrder);
    }
}
