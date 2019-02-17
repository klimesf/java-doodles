package cz.filipklimes.search.skiplist;

public interface SkipList {

    boolean find(int value);

    void insert(int value);

    void delete(int value);

}
