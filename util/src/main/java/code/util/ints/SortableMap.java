package code.util.ints;
import code.util.EntryCust;


public interface SortableMap<K, V> extends ListableEntries<K, V>, HasComparator<K> {

    V getValue(int _index);

    K getKey(int _index);

    K firstKey();

    K lastKey();

    EntryCust<K, V> lowerEntry(K _key);

    K lowerKey(K _key);

    EntryCust<K, V> floorEntry(K _key);

    K floorKey(K _key);

    EntryCust<K, V> ceilingEntry(K _key);

    K ceilingKey(K _key);

    EntryCust<K, V> higherEntry(K _key);

    K higherKey(K _key);

    EntryCust<K, V> firstEntry();

    EntryCust<K, V> lastEntry();
}
