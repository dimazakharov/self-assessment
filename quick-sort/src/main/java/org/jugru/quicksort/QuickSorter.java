package org.jugru.quicksort;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class QuickSorter {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    public long[] sort(long[] array) {
        if (array.length < 2)
            return array;

        long delimiter = array[((int) Math.round(Math.random() * (array.length - 1)))];

        long[] less = Arrays.stream(array).filter(value -> value < delimiter).toArray();
        long[] greater = Arrays.stream(array).filter(value -> value >= delimiter).toArray();

        Future<long[]> lessFuture = EXECUTOR_SERVICE.submit(() -> sort(less));
        Future<long[]> greaterFuture = EXECUTOR_SERVICE.submit(() -> sort(greater));


        try {
            return ArrayUtils.addAll(lessFuture.get(), greaterFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }


}
