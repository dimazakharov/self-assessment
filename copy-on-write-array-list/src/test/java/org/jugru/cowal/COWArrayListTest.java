package org.jugru.cowal;

import com.google.common.collect.testing.ListTestSuiteBuilder;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import com.google.common.collect.testing.features.ListFeature;
import junit.framework.TestSuite;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        COWArrayListTest.GuavaTests.class,
        COWArrayListTest.Tests.class,
})
public class COWArrayListTest {




    public static  class GuavaTests {
        public static TestSuite suite() {

            TestSuite suite =
                    new TestSuite("ListsTest");
            suite.addTest(COWArrayListTest.COWArrayListTest());
            return suite;

        }
    }

    public static class Tests {

        @Test
        public void concurrentModificationTest() throws Exception {
            List<String> testList = new CopyOnWriteArrayList<>();

            ExecutorService executorService = Executors.newCachedThreadPool();


            Set<Future<Void>> futures = IntStream.range(0, 1).mapToObj(operand -> executorService.submit(new TestTask(testList))).collect(Collectors.toSet());

            for (Future<Void> future : futures) {
                future.get();
            }
            System.out.println("----");
            System.out.println(testList.size());
        }



    }
    public static class TestTask implements Callable<Void>{

        private final List<String> testList;

        public TestTask(List<String> testList) {
            this.testList = testList;
        }

        @Override
        public Void call() throws Exception {
            testList.add("a");
            testList.add("b");
            testList.addAll(Arrays.asList("c, d"));
            testList.remove("c");
            testList.remove("d");
            testList.remove("a");
            testList.remove("b");
            System.out.println(testList);
            System.out.println(testList.size());


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
//                        CollectionFeature.SUPPORTS_ITERATOR_REMOVE,
                        ListFeature.SUPPORTS_SET)
                .createTestSuite();
    }

}