package org.jugru.cowal;

import java.util.List;

public class COWArrayListGenerator extends AbstractTestListGenerator {
    @Override
    List<String> createListFromElements(List<String> elements) {
        return new COWAL<>(elements);
    }
}
