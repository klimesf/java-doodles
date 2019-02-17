package cz.filipklimes.search.skiplist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NaiveSkipListTest {

    @Test
    public void test() {
        NaiveSkipList skipList = new NaiveSkipList(4, 0.5f);

        skipList.insert(1);
        skipList.insert(50);
        skipList.insert(10);
        skipList.insert(20);
        skipList.insert(5);
        skipList.insert(31);
        skipList.insert(76);
        skipList.insert(49);

        skipList.delete(5);
        skipList.delete(49);

        assertAll(
            () -> assertTrue(skipList.find(1)),
            () -> assertTrue(skipList.find(50)),
            () -> assertTrue(skipList.find(10)),
            () -> assertTrue(skipList.find(20)),
            () -> assertFalse(skipList.find(5)),
            () -> assertTrue(skipList.find(31)),
            () -> assertTrue(skipList.find(76)),
            () -> assertFalse(skipList.find(49))
        );
    }

}
