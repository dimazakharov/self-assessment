package org.jugru.pim;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Array;
import java.util.*;

public class PersistentImmutableMap<K, V> extends AbstractMap<K, V> implements Map<K, V> {

    private final Map<K, V> map;


    private PersistentImmutableMap() {
        map = Collections.emptyMap();
    }


    private PersistentImmutableMap(Map<? extends K, ? extends V> map) {
        this.map = Map.copyOf(map);
    }

    private PersistentImmutableMap(Entry<? extends K, ? extends V>... entries) {
        this.map = Map.ofEntries(entries);
    }


    public static <K, V> PersistentImmutableMap<K, V> empty() {
        return new PersistentImmutableMap<>();

    }

    public static <K, V> PersistentImmutableMap<K, V> from(Map<? extends K, ? extends V> map) {
        return new PersistentImmutableMap<>(map);

    }

    public static <K, V> PersistentImmutableMap<K, V> from(Entry<? extends K, ? extends V>... entries) {
        return new PersistentImmutableMap<>(entries);

    }


    public PersistentImmutableMap<K, V> with(K key, V value) {
        Entry<K, V>[] entries = map.entrySet().toArray(new Entry[0]);
        return PersistentImmutableMap.from(ArrayUtils.add(entries, Map.entry(key, value)));
    }

    public PersistentImmutableMap<K, V> withAll(Map<? extends K, ? extends V> map) {
        Entry<K, V>[] originalEntries = this.map.entrySet().toArray(new Entry[0]);
        Entry<K, V>[] addedEntries = map.entrySet().toArray(new Entry[0]);
        return PersistentImmutableMap.from(ArrayUtils.addAll(originalEntries, addedEntries));
    }

    public PersistentImmutableMap<K, V> without(K key) {
        Set<Entry<K, V>> entries = new HashSet<>(entrySet());
        entries.removeIf(kvEntry -> kvEntry.getKey().equals(key));
        return PersistentImmutableMap.from(entries.toArray(new Entry[0]));

    }

    public PersistentImmutableMap<K, V> withoutAll(Collection<? extends K> keys) {
        Set<Entry<K, V>> entries = new HashSet<>(entrySet());
        entries.removeIf(kvEntry -> keys.contains(kvEntry.getKey()));
        return PersistentImmutableMap.from(entries.toArray(new Entry[0]));

    }


    @Override
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public V get(Object key) {
        return map.get(key);
    }

    @Override
    public V put(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() {
        return super.keySet();
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
