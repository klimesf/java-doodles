package cz.filipklimes.tree;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static cz.filipklimes.tree.BinaryTreeNode.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryTreeNodeTest {

    @Nested
    public class InOrderIterator {

        @Test
        public void testEasy() {
            BinaryTreeNode root = node(
                1,
                node(2, leaf(4), leaf(5)),
                leaf(3)
            );

            Iterator<BinaryTreeNode> inOrderIterator = root.inOrderIterator();

            assertEquals(4, inOrderIterator.next().getValue());
            assertEquals(2, inOrderIterator.next().getValue());
            assertEquals(5, inOrderIterator.next().getValue());
            assertEquals(1, inOrderIterator.next().getValue());
            assertEquals(3, inOrderIterator.next().getValue());
            assertFalse(inOrderIterator.hasNext());
        }

        @Test
        public void testHard() {
            BinaryTreeNode root = node(
                25,
                node(
                    15,
                    node(10, leaf(4), leaf(12)),
                    node(22, leaf(18), leaf(24))
                ),
                node(
                    50,
                    node(35, leaf(31), leaf(44)),
                    node(70, leaf(66), leaf(90))
                )
            );

            Iterator<BinaryTreeNode> inOrderIterator = root.inOrderIterator();

            assertEquals(4, inOrderIterator.next().getValue());
            assertEquals(10, inOrderIterator.next().getValue());
            assertEquals(12, inOrderIterator.next().getValue());
            assertEquals(15, inOrderIterator.next().getValue());
            assertEquals(18, inOrderIterator.next().getValue());
            assertEquals(22, inOrderIterator.next().getValue());
            assertEquals(24, inOrderIterator.next().getValue());
            assertEquals(25, inOrderIterator.next().getValue());
            assertEquals(31, inOrderIterator.next().getValue());
            assertEquals(35, inOrderIterator.next().getValue());
            assertEquals(44, inOrderIterator.next().getValue());
            assertEquals(50, inOrderIterator.next().getValue());
            assertEquals(66, inOrderIterator.next().getValue());
            assertEquals(70, inOrderIterator.next().getValue());
            assertEquals(90, inOrderIterator.next().getValue());
            assertFalse(inOrderIterator.hasNext());
        }

        @Test
        public void testAllLeft() {
            BinaryTreeNode root = node(5, node(4, node(3, node(2, leaf(1), empty()), empty()), empty()), empty());

            Iterator<BinaryTreeNode> inOrderIterator = root.inOrderIterator();

            assertEquals(1, inOrderIterator.next().getValue());
            assertEquals(2, inOrderIterator.next().getValue());
            assertEquals(3, inOrderIterator.next().getValue());
            assertEquals(4, inOrderIterator.next().getValue());
            assertEquals(5, inOrderIterator.next().getValue());
            assertFalse(inOrderIterator.hasNext());
        }

        @Test
        public void testAllRight() {
            BinaryTreeNode root = node(5, empty(), node(4, empty(), node(3, empty(), node(2, empty(), leaf(1)))));

            Iterator<BinaryTreeNode> inOrderIterator = root.inOrderIterator();

            assertEquals(5, inOrderIterator.next().getValue());
            assertEquals(4, inOrderIterator.next().getValue());
            assertEquals(3, inOrderIterator.next().getValue());
            assertEquals(2, inOrderIterator.next().getValue());
            assertEquals(1, inOrderIterator.next().getValue());
            assertFalse(inOrderIterator.hasNext());
        }

    }

    @Nested
    public class PreOrderIterator {

        @Test
        public void testEasy() {
            BinaryTreeNode root = node(
                1,
                node(2, leaf(4), leaf(5)),
                leaf(3)
            );

            Iterator<BinaryTreeNode> preOrderIterator = root.preOrderIterator();

            assertEquals(1, preOrderIterator.next().getValue());
            assertEquals(2, preOrderIterator.next().getValue());
            assertEquals(4, preOrderIterator.next().getValue());
            assertEquals(5, preOrderIterator.next().getValue());
            assertEquals(3, preOrderIterator.next().getValue());
            assertFalse(preOrderIterator.hasNext());
        }

        @Test
        public void testHard() {
            BinaryTreeNode root = node(
                25,
                node(
                    15,
                    node(10, leaf(4), leaf(12)),
                    node(22, leaf(18), leaf(24))
                ),
                node(
                    50,
                    node(35, leaf(31), leaf(44)),
                    node(70, leaf(66), leaf(90))
                )
            );

            Iterator<BinaryTreeNode> preOrderIterator = root.preOrderIterator();

            assertEquals(25, preOrderIterator.next().getValue());
            assertEquals(15, preOrderIterator.next().getValue());
            assertEquals(10, preOrderIterator.next().getValue());
            assertEquals(4, preOrderIterator.next().getValue());
            assertEquals(12, preOrderIterator.next().getValue());
            assertEquals(22, preOrderIterator.next().getValue());
            assertEquals(18, preOrderIterator.next().getValue());
            assertEquals(24, preOrderIterator.next().getValue());
            assertEquals(50, preOrderIterator.next().getValue());
            assertEquals(35, preOrderIterator.next().getValue());
            assertEquals(31, preOrderIterator.next().getValue());
            assertEquals(44, preOrderIterator.next().getValue());
            assertEquals(70, preOrderIterator.next().getValue());
            assertEquals(66, preOrderIterator.next().getValue());
            assertEquals(90, preOrderIterator.next().getValue());
            assertFalse(preOrderIterator.hasNext());
        }

        @Test
        public void testAllLeft() {
            BinaryTreeNode root = node(5, node(4, node(3, node(2, leaf(1), empty()), empty()), empty()), empty());

            Iterator<BinaryTreeNode> preOrderIterator = root.preOrderIterator();

            assertEquals(5, preOrderIterator.next().getValue());
            assertEquals(4, preOrderIterator.next().getValue());
            assertEquals(3, preOrderIterator.next().getValue());
            assertEquals(2, preOrderIterator.next().getValue());
            assertEquals(1, preOrderIterator.next().getValue());
            assertFalse(preOrderIterator.hasNext());
        }

        @Test
        public void testAllRight() {
            BinaryTreeNode root = node(5, empty(), node(4, empty(), node(3, empty(), node(2, empty(), leaf(1)))));

            Iterator<BinaryTreeNode> preOrderIterator = root.preOrderIterator();

            assertEquals(5, preOrderIterator.next().getValue());
            assertEquals(4, preOrderIterator.next().getValue());
            assertEquals(3, preOrderIterator.next().getValue());
            assertEquals(2, preOrderIterator.next().getValue());
            assertEquals(1, preOrderIterator.next().getValue());
            assertFalse(preOrderIterator.hasNext());
        }

    }

    @Nested
    public class PostOrderIterator {

        @Test
        public void testEasy() {
            BinaryTreeNode root = node(
                1,
                node(2, leaf(4), leaf(5)),
                leaf(3)
            );

            Iterator<BinaryTreeNode> postOrderIterator = root.postOrderIterator();

            assertEquals(4, postOrderIterator.next().getValue());
            assertEquals(5, postOrderIterator.next().getValue());
            assertEquals(2, postOrderIterator.next().getValue());
            assertEquals(3, postOrderIterator.next().getValue());
            assertEquals(1, postOrderIterator.next().getValue());
            assertFalse(postOrderIterator.hasNext());
        }

        @Test
        public void testHard() {
            BinaryTreeNode root = node(
                25,
                node(
                    15,
                    node(10, leaf(4), leaf(12)),
                    node(22, leaf(18), leaf(24))
                ),
                node(
                    50,
                    node(35, leaf(31), leaf(44)),
                    node(70, leaf(66), leaf(90))
                )
            );

            Iterator<BinaryTreeNode> postOrderIterator = root.postOrderIterator();

            assertEquals(4, postOrderIterator.next().getValue());
            assertEquals(12, postOrderIterator.next().getValue());
            assertEquals(10, postOrderIterator.next().getValue());
            assertEquals(18, postOrderIterator.next().getValue());
            assertEquals(24, postOrderIterator.next().getValue());
            assertEquals(22, postOrderIterator.next().getValue());
            assertEquals(15, postOrderIterator.next().getValue());
            assertEquals(31, postOrderIterator.next().getValue());
            assertEquals(44, postOrderIterator.next().getValue());
            assertEquals(35, postOrderIterator.next().getValue());
            assertEquals(66, postOrderIterator.next().getValue());
            assertEquals(90, postOrderIterator.next().getValue());
            assertEquals(70, postOrderIterator.next().getValue());
            assertEquals(50, postOrderIterator.next().getValue());
            assertEquals(25, postOrderIterator.next().getValue());
            assertFalse(postOrderIterator.hasNext());
        }

        @Test
        public void testAllLeft() {
            BinaryTreeNode root = node(5, node(4, node(3, node(2, leaf(1), empty()), empty()), empty()), empty());

            Iterator<BinaryTreeNode> postOrderIterator = root.postOrderIterator();

            assertEquals(1, postOrderIterator.next().getValue());
            assertEquals(2, postOrderIterator.next().getValue());
            assertEquals(3, postOrderIterator.next().getValue());
            assertEquals(4, postOrderIterator.next().getValue());
            assertEquals(5, postOrderIterator.next().getValue());
            assertFalse(postOrderIterator.hasNext());
        }

        @Test
        public void testAllRight() {
            BinaryTreeNode root = node(5, empty(), node(4, empty(), node(3, empty(), node(2, empty(), leaf(1)))));

            Iterator<BinaryTreeNode> postOrderIterator = root.postOrderIterator();

            assertEquals(1, postOrderIterator.next().getValue());
            assertEquals(2, postOrderIterator.next().getValue());
            assertEquals(3, postOrderIterator.next().getValue());
            assertEquals(4, postOrderIterator.next().getValue());
            assertEquals(5, postOrderIterator.next().getValue());

            assertFalse(postOrderIterator.hasNext());
        }

    }

}
