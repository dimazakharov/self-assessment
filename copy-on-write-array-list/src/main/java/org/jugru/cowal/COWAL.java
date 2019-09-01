package org.jugru.cowal;

import org.apache.commons.collections4.iterators.ArrayIterator;
import org.apache.commons.collections4.iterators.ArrayListIterator;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class COWAL<E> implements List<E> {

    private transient List<E> list;

    private final Object lock = new Object();

    public COWAL() {
        this.list = new ArrayList<>();
    }

    public COWAL(List<E> list) {
        this.list = new ArrayList<>(list);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator(list.toArray());
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    @Override
    public boolean add(E e) {
        synchronized (lock){
            ArrayList<E> tmpList = new ArrayList<>(list);
            boolean answer = tmpList.add(e);
            list = new ArrayList<>(tmpList);
            return answer;
        }
    }

    @Override
    public boolean remove(Object o) {
        synchronized (lock){
            ArrayList<E> tmpList = new ArrayList<>(list);
            boolean answer = tmpList.remove(o);
            list = new ArrayList<>(tmpList);
            return answer;
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        synchronized (lock){
            ArrayList<E> tmpList = new ArrayList<>(list);
            boolean answer = tmpList.addAll(c);
            list = new ArrayList<>(tmpList);
            return answer;
        }
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        synchronized (lock){
            ArrayList<E> tmpList = new ArrayList<>(list);
            boolean answer = tmpList.addAll(index, c);
            list = new ArrayList<>(tmpList);
            return answer;
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        synchronized (lock){
            ArrayList<E> tmpList = new ArrayList<>(list);
            boolean answer = tmpList.removeAll(c);
            list = new ArrayList<>(tmpList);
            return answer;
        }
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        synchronized (lock){
            ArrayList<E> tmpList = new ArrayList<>(list);
            boolean answer = tmpList.retainAll(c);
            list = new ArrayList<>(tmpList);
            return answer;
        }
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        synchronized (lock){
            ArrayList<E> tmpList = new ArrayList<>(list);
            tmpList.replaceAll(operator);
            list = new ArrayList<>(tmpList);
        }
    }

    @Override
    public void sort(Comparator<? super E> c) {
        synchronized (lock){
            ArrayList<E> tmpList = new ArrayList<>(list);
            tmpList.sort(c);
            list = new ArrayList<>(tmpList);
        }
    }

    @Override
    public void clear() {
        synchronized (lock){
            list = new ArrayList<>();
        }
    }

    @Override
    public boolean equals(Object o) {
        return list.equals(o);
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        synchronized (lock){
            ArrayList<E> tmpList = new ArrayList<>(list);
            E answer = tmpList.set(index, element);
            list = new ArrayList<>(tmpList);
            return answer;
        }
    }

    @Override
    public void add(int index, E element) {
        synchronized (lock){
            ArrayList<E> tmpList = new ArrayList<>(list);
           tmpList.add(index , element);
            list = new ArrayList<>(tmpList);
        }
    }

    @Override
    public E remove(int index) {
        synchronized (lock){
            ArrayList<E> tmpList = new ArrayList<>(list);
            E answer = tmpList.remove(index);
            list = new ArrayList<>(tmpList);
            return answer;
        }
    }

    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ArrayListIterator<>(list.toArray());
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new ArrayListIterator<>(list.toArray(), index);

    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);//todo
    }

    @Override
    public Spliterator<E> spliterator() {
        return list.spliterator();
    }

    @Override
    public <T> T[] toArray(IntFunction<T[]> generator) {
        return list.toArray(generator);
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        synchronized (lock){
            ArrayList<E> tmpList = new ArrayList<>(list);
            boolean answer = tmpList.removeIf(filter);
            list = new ArrayList<>(tmpList);
            return answer;
        }
    }

    @Override
    public Stream<E> stream() {
        return list.stream();
    }

    @Override
    public Stream<E> parallelStream() {
        return list.parallelStream();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        list.forEach(action);
    }


    @Override
    public String toString() {
        return list.toString();
    }
}
