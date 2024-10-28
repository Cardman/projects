package code.util.ints;

public interface IntIndexOfEntry<K> {
    int indexOfEntry(K _key, int _from);

    K getKey(int _i);

    int size();
}
