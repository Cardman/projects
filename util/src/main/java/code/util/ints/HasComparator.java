package code.util.ints;

public interface HasComparator<K> extends ChangeableMap {

    Comparing<K> comparator();

}
