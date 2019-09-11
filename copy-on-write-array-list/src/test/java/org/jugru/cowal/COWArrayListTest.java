package org.jugru.cowal;

import com.google.common.collect.testing.ListTestSuiteBuilder;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import com.google.common.collect.testing.features.ListFeature;
import junit.framework.TestSuite;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//@RunWith(Suite.class)
//@Suite.SuiteClasses({
//        COWArrayListTest.GuavaTests.class,
//        COWArrayListTest.Tests.class,
//})
public class COWArrayListTest {


    public static class GuavaTests {
        public static TestSuite suite() {

            TestSuite suite =
                    new TestSuite("ListsTest");
            suite.addTest(COWArrayListTest.COWArrayListTest());
            return suite;

        }
    }

    public static class Tests {

        @Test
        public void concurrentModificationAddAndRemoveTest() throws Exception {
            List<String> testList = new COWAL<>();
            testList.add("a");
            testList.add("b");


            ExecutorService executorService = Executors.newCachedThreadPool();


            Set<Future<Void>> futures = IntStream.range(0, 1000).mapToObj(operand -> executorService.submit(new addAndRemoveTask(testList))).collect(Collectors.toSet());

            for (Future<Void> future : futures) {
                future.get();
            }

            Assert.assertEquals(2, testList.size());
        }


        @Test
        public void concurrentModificationSortTest() throws Exception {
            List<String> testList = new COWAL<>();
            ExecutorService executorService = Executors.newCachedThreadPool();
            Set<Future<Void>> futures = new HashSet<>();

            List<String> expected = new ArrayList<>();

            for (int i = 0; i < 1000; i++) {
                String generatedString = RandomStringUtils.random(10, false, false);
                Future<Void> submit = executorService.submit(new sortTask(testList, generatedString));
                futures.add(submit);
                expected.add(generatedString);
            }

            for (Future<Void> future : futures) {
                future.get();
            }

            expected.sort(Comparator.naturalOrder());
            Assert.assertArrayEquals(expected.toArray(), testList.toArray());
        }


    }

    public static class addAndRemoveTask implements Callable<Void> {

        private final List<String> testList;

        public addAndRemoveTask(List<String> testList) {
            this.testList = testList;
        }

        @Override
        public Void call() throws Exception {
            testList.add("a");
            testList.add(0,"b");
            testList.addAll(Arrays.asList("c", "d"));

            Assert.assertTrue(testList.remove("c"));
            Assert.assertTrue(testList.remove("a"));
            Assert.assertTrue(testList.remove("d"));
            Assert.assertTrue(testList.remove("b"));


            return null;
        }
    }

    public static class sortTask implements Callable<Void> {

        private final List<String> testList;
        private final String string;


        public sortTask(List<String> testList, String string) {
            this.testList = testList;
            this.string = string;
        }

        @Override
        public Void call() throws Exception {
            testList.add(string);
            testList.sort(Comparator.naturalOrder());
            return null;
        }
    }




    private static TestSuite COWArrayListTest() {
        return ListTestSuiteBuilder
                .using(new COWArrayListGenerator())
                .named("COWArrayList test")
                .withFeatures(
                        CollectionSize.ANY,
                        CollectionFeature.ALLOWS_NULL_VALUES,
                        CollectionFeature.SUPPORTS_ADD,
                        CollectionFeature.SUPPORTS_REMOVE,
                        ListFeature.SUPPORTS_SET)
                .createTestSuite();
    }

}