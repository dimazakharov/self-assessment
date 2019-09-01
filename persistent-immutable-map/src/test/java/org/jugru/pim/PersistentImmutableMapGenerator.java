package org.jugru.pim;

import com.google.common.collect.testing.Helpers;
import com.google.common.collect.testing.SampleElements;
import com.google.common.collect.testing.TestMapGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersistentImmutableMapGenerator implements TestMapGenerator<String, String> {
    @Override
    public String[] createKeyArray(int length) {
        return new String[length];
    }

    @Override
    public String[] createValueArray(int length) {
        return new String[length];
    }

    @Override
    public SampleElements<Map.Entry<String, String>> samples() {
        return new SampleElements<>(
                Helpers.mapEntry("A", "AA"),
                Helpers.mapEntry("B", "BB"),
                Helpers.mapEntry("C", "CC"),
                Helpers.mapEntry("D", "DD"),
                Helpers.mapEntry("E", "EE")
        );
    }

    @Override
    public Map<String, String> create(Object... elements) {
        Map<String, String> map = new HashMap<>();
        for (Object element : elements) {
            @SuppressWarnings("unchecked")
            Map.Entry<String,String> entry = (Map.Entry<String, String>) element;
            map.put(entry.getKey(), entry.getValue());
        }
        return PersistentImmutableMap.from(map);
    }

    @Override
    public Map.Entry<String, String>[] createArray(int length) {
        return new Map.Entry[length];
    }

    @Override
    public Iterable<Map.Entry<String, String>> order(List<Map.Entry<String, String>> insertionOrder) {
        return insertionOrder;
    }
}
