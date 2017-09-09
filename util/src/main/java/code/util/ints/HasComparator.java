package code.util.ints;
import java.util.Comparator;

public interface HasComparator<K> extends ChangeableMap {

    Comparator<K> comparator();

}
