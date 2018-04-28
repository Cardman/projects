package code.util.ints;
import code.util.ints.Comparing;

public interface HasComparator<K> extends ChangeableMap {

    Comparing<K> comparator();

}
