package org.jugru.cowal;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListGenerator extends AbstractTestListGenerator  {
    @Override
    List<String> createListFromElements(List<String> elements) {
        return new CopyOnWriteArrayList<>(elements);
    }
}
