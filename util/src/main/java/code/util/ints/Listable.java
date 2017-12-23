package code.util.ints;
import java.util.ListIterator;

import code.util.Numbers;

public interface Listable<T> extends IterableList<T>, Countable, SimpleList {

    Listable<T> getReverse();

    void add(T _e);

    void addAllElts(Listable<T> _c);

    void clear();

    boolean isValidIndex(int _index);

    T get(int _index);

    void set(int _index, T _element);

    void add(int _index, T _element);

    void removeAt(Number _index);

    void remove(int _index);

    ListIterator<T> listIterator();

    ListIterator<T> listIterator(int _index);

    T first();

    T last();

    boolean containsNull();

    void removeNull();

    int indexOfNull();
    int indexOfNull(int _from);

    int lastIndexOfNull();

    Numbers<Integer> indexesOfNull();

    Listable<T> sub(int _from, int _to);

    void swapIndexes(int _i, int _j);
}
