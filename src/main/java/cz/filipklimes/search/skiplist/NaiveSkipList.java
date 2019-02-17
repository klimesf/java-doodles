package cz.filipklimes.search.skiplist;

import java.util.Random;

public class NaiveSkipList implements SkipList {

    private final int maxLevel;
    private final float probability;
    private final Random rand = new Random();
    private Element[] start;

    public NaiveSkipList(int maxLevel, float probability) {
        if (maxLevel < 1) {
            throw new IllegalArgumentException("maxLevel must be a positive number");
        }

        if (probability < 0 || probability > 1) {
            throw new IllegalArgumentException("probability must be between 0 and 1");
        }

        this.maxLevel = maxLevel;
        this.probability = probability;

        createSentinels(maxLevel);
    }

    @Override
    public boolean find(int value) {
        Element[] levels = skipTo(value);
        return containsValue(levels, value);
    }

    @Override
    public void insert(int value) {
        Element[] levels = skipTo(value);
        if (containsValue(levels, value)) {
            return;
        }

        int level = 1;
        while(level < maxLevel) {
            if (rand.nextFloat() > probability) {
                break;
            }
            level++;
        };

        Element inserted = new Element(value, level);

        for (int i = level; i > 0; i--) {
            Element next = levels[i - 1].getNext(i);
            levels[i - 1].setNext(i, inserted);
            inserted.setNext(i, next);
        }
    }

    @Override
    public void delete(int value) {
        Element[] levels = skipTo(value);
        if (!containsValue(levels, value)) {
            return;
        }

        Element deleted = levels[0].getNext(1);

        for (int i = 1; i <= deleted.getLevel(); i++) {
            levels[i - 1].setNext(i, deleted.getNext(i));
        }
    }

    private Element[] skipTo(int value) {
        Element[] levels = new Element[maxLevel];
        Element e = start[maxLevel - 1];
        for (int i = maxLevel; i > 0; i--) {
            while (e.getNext(i).getValue() < value) {
                e = e.getNext(i);
            }
            levels[i - 1] = e;
        }

        return levels;
    }

    private boolean containsValue(Element[] levels, int value) {
        return levels[0].getNext(1).getValue() == value;
    }

    private void createSentinels(int maxLevel) {
        start = new Element[maxLevel];

        Element leftSentinel = new Element(Integer.MIN_VALUE, maxLevel);
        Element rightSentinel = new Element(Integer.MAX_VALUE, maxLevel);

        for (int i = 1; i <= maxLevel; i++) {
            start[i - 1] = leftSentinel;
            leftSentinel.setNext(i, rightSentinel);
            rightSentinel.setNext(i, leftSentinel);
        }
    }

    private static class Element {

        private final int value;
        private final int level;
        private Element[] next;

        public Element(int value, int level) {
            this.value = value;
            this.level = level;
            next = new Element[level];
        }

        public int getValue() {
            return value;
        }

        public int getLevel() {
            return level;
        }

        public Element getNext(int level) {
            if (level > this.level) {
                throw new IllegalArgumentException("level too high");
            }

            return next[level - 1];
        }

        public void setNext(int level, Element element) {
            if (level > this.level) {
                throw new IllegalArgumentException("level too high");
            }

            next[level - 1] = element;
        }

    }

}
