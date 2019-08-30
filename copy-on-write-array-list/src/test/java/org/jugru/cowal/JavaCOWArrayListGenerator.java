package org.jugru.cowal;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class JavaCOWArrayListGenerator extends AbstractTestListGenerator {

    @Override
    List<String> createListFromElements(List<String> elements) {
        return new CopyOnWriteArrayList<>(elements);
    }
}
