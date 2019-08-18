package org.jugru.quicksort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

public class QuickSorterTest {



    @Test
    public void randomTest() {
        Random random = new Random();
        long[] originalArray = new long[500];
        for (int i = 0; i < originalArray.length; i++) {
            originalArray[i] = random.nextLong();
        }
        long[] answer = new QuickSorter().sort(originalArray);

        Arrays.sort(originalArray);

        Assertions.assertArrayEquals(originalArray, answer);

    }
}
