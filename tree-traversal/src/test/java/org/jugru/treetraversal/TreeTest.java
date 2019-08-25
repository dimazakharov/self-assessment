package org.jugru.treetraversal;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TreeTest {

    @Test
    public void sorted() {
        List<Integer> input = List.of(2, 3, 5, 7, 11, 13);

        Tree tree = new Tree();
        input.forEach(tree::add);
        /**
         *          2
         *           \
         *            3
         *             \
         *              5
         *               \
         *                7
         *                 \
         *                  11
         *                   \
         *                    13
         */

        List<Integer> result = tree.getAll();
        List<Integer> expected = input;
        assertEquals(expected, result);


    }

    @Test
    public void empty() {

        Tree tree = new Tree();

        List<Integer> result = tree.getAll();

        assertNotNull(result);
        assertEquals(0, result.size());

    }

    @Test
    public void withDuplicates() {
        List<Integer> input = List.of(11, 3 , 13, 23, 2, 7, 3);

        Tree tree = new Tree();
        input.forEach(tree::add);
        /**
         *          11
         *         /  \
         *       3,3   13
         *       / \     \
         *      2   7     23
         */

        List<Integer> result = tree.getAll();
        List<Integer> expected = List.of(11, 3 , 3, 13, 2, 7, 23);
        assertEquals(expected, result);
    }
}
