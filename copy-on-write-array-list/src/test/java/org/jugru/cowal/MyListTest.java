package org.jugru.cowal;

import com.google.common.collect.testing.ListTestSuiteBuilder;
import com.google.common.collect.testing.SpliteratorTester;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import com.google.common.collect.testing.features.ListFeature;
import junit.framework.Test;
import junit.framework.TestSuite;

public class MyListTest {

    public static Test suite() {
        TestSuite suite =
                new TestSuite("name");
        suite.addTest(new MyListTest().testForOneToWayUseMyList());
        return suite;
    }


    private Test testForOneToWayUseMyList() {
        return ListTestSuiteBuilder
                .using(new MyListGenerator())
                .named("one way to use MyList")
                .withFeatures(
                        CollectionSize.ANY,
                        CollectionFeature.ALLOWS_NULL_VALUES,
                        CollectionFeature.SUPPORTS_ADD,
                        CollectionFeature.SUPPORTS_REMOVE,
                        CollectionFeature.SUPPORTS_ADD,
                        ListFeature.SUPPORTS_SET)
                .createTestSuite();
    }

}