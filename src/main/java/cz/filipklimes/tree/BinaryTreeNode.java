package cz.filipklimes.tree;

import java.util.Iterator;
import java.util.Stack;

public class BinaryTreeNode {

    private static final BinaryTreeNode EMPTY = new BinaryTreeNode(-1, null, null);

    private final BinaryTreeNode left;
    private final BinaryTreeNode right;
    private final int value;

    private BinaryTreeNode(int value, BinaryTreeNode left, BinaryTreeNode right) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public int getValue() {
        return value;
    }

    public Iterator<BinaryTreeNode> inOrderIterator() {
        return new InOrderIterator(this);
    }

    public Iterator<BinaryTreeNode> preOrderIterator() {
        return new PreOrderIterator(this);
    }

    public Iterator<BinaryTreeNode> postOrderIterator() {
        return new PostOrderIterator(this);
    }

    public static BinaryTreeNode node(int value, BinaryTreeNode left, BinaryTreeNode right) {
        return new BinaryTreeNode(value, left, right);
    }

    public static BinaryTreeNode leaf(int value) {
        return new BinaryTreeNode(value, EMPTY, EMPTY);
    }

    public static BinaryTreeNode empty() {
        return EMPTY;
    }

    private final static class InOrderIterator implements Iterator<BinaryTreeNode> {

        private BinaryTreeNode next;
        private Stack<BinaryTreeNode> stack;

        private InOrderIterator(BinaryTreeNode root) {
            this.next = root;
            this.stack = new Stack<>();
            bubbleLeft();
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public BinaryTreeNode next() {
            BinaryTreeNode result = next;
            bubbleRight();
            return result;
        }

        private void bubbleLeft() {
            while (next.getLeft() != EMPTY) {
                stack.push(next);
                next = next.getLeft();
            }
        }

        private void bubbleRight() {
            if (next.getRight() != EMPTY) {
                stack.push(next);
                next = next.getRight();
                bubbleLeft();
                return;
            }

            bubbleUp();
        }

        private void bubbleUp() {
            if (stack.isEmpty()) {
                next = null;
                return;
            }

            BinaryTreeNode parent = stack.pop();

            if (parent.getLeft() == next) {
                next = parent;
                return;
            }

            next = parent;
            bubbleUp();
        }

    }

    private final static class PreOrderIterator implements Iterator<BinaryTreeNode> {

        private BinaryTreeNode next;
        private Stack<BinaryTreeNode> stack;

        private PreOrderIterator(BinaryTreeNode root) {
            this.next = root;
            this.stack = new Stack<>();
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public BinaryTreeNode next() {
            BinaryTreeNode result = next;
            bubbleDown();
            return result;
        }

        private void bubbleDown() {
            if (next.getLeft() != EMPTY) {
                stack.push(next);
                next = next.getLeft();
                return;
            }

            if (next.getRight() != EMPTY) {
                stack.push(next);
                next = next.getRight();
                return;
            }

            bubbleUp();
        }

        private void bubbleUp() {
            if (stack.isEmpty()) {
                next = null;
                return;
            }

            BinaryTreeNode parent = stack.pop();
            if (parent.getLeft() == next && parent.getRight() != EMPTY) {
                next = parent.getRight();
                stack.push(parent);
                return;
            }

            next = parent;
            bubbleUp();
        }

    }

    private final static class PostOrderIterator implements Iterator<BinaryTreeNode> {

        private BinaryTreeNode next;
        private Stack<BinaryTreeNode> stack;

        private PostOrderIterator(BinaryTreeNode root) {
            this.next = root;
            this.stack = new Stack<>();
            bubbleDown();
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public BinaryTreeNode next() {
            BinaryTreeNode result = next;
            bubbleUp();
            return result;
        }

        private void bubbleDown() {
            while (next.getLeft() != EMPTY) {
                stack.push(next);
                next = next.getLeft();
            }

            while (next.getRight() != EMPTY) {
                stack.push(next);
                next = next.getRight();
            }
        }

        private void bubbleUp() {
            if (stack.isEmpty()) {
                next = null;
                return;
            }

            BinaryTreeNode parent = stack.pop();
            if (parent.getLeft() == next && parent.getRight() != EMPTY) {
                stack.push(parent);
                next = parent.getRight();
                bubbleDown();
                return;
            }

            next = parent;
        }

    }

}
