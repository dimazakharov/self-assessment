package org.jugru.pim;

import com.google.common.collect.testing.MapTestSuiteBuilder;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import junit.framework.TestSuite;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PersistentImmutableMapTest.GuavaTests.class,
        PersistentImmutableMapTest.Tests.class,
})
public class PersistentImmutableMapTest {


    public static class GuavaTests {

        public static TestSuite suite() {

            TestSuite suite =
                    new TestSuite("Guava map tests");
            suite.addTest(PersistentImmutableMapTest.PIMTest());
            return suite;

        }
    }

    public static class Tests {


        @Test
        public void withTest() {
            PersistentImmutableMap<String, String> map = PersistentImmutableMap.empty();
            map = map.with("A", "AA");
            assertEquals(1, map.size());
            assertEquals("AA", map.get("A"));
        }

        @Test
        public void withAllTest() {
            PersistentImmutableMap<String, String> map = PersistentImmutableMap.empty();
            map = map.withAll(Map.of("A", "AA", "B", "BB"));
            assertEquals(2, map.size());
            assertEquals("BB", map.get("B"));
        }

        @Test
        public void withoutTest() {
            PersistentImmutableMap<String, String> map = PersistentImmutableMap.from(Map.of("A", "AA", "B", "BB", "C", "CC"));
            map = map.without("B");
            assertEquals(2, map.size());
            assertFalse(map.containsKey("B"));
        }

        @Test
        public void withoutAllTest() {
            PersistentImmutableMap<String, String> map = PersistentImmutableMap.from(Map.of("A", "AA", "B", "BB", "C", "CC"));
            map = map.withoutAll(List.of("A", "B"));
            assertEquals(1, map.size());
            assertFalse(map.containsKey("B"));
            assertFalse(map.containsKey("A"));

        }
    }


    private static TestSuite PIMTest() {
        return MapTestSuiteBuilder
                .using(new PersistentImmutableMapGenerator())
                .named("Persistent immutable map test")
                .withFeatures(
                        CollectionSize.ANY,
                        CollectionFeature.NONE)
                .createTestSuite();
    }
}
