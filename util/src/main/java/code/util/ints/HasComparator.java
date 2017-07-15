package code.util.ints;
import java.util.Comparator;

public interface HasComparator<K> {

    Comparator<K> comparator();

    void applyChanges();
}
