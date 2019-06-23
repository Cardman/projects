package code.util.ints;
import code.util.CustList;
import code.util.*;

public interface Listable<T> extends Iterable<T>,SimpleIterable , Countable, SimpleList {

    Iterable<T> getList();
    CustList<T> getReverse();

    void add(T _e);

    void addAllElts(Listable<T> _c);

    void clear();

    boolean isValidIndex(int _index);

    void set(int _index, T _element);

    void add(int _index, T _element);

    void removeAt(int _index);

    void remove(int _index);

    T first();

    T last();

    CustList<T> sub(int _from, int _to);

    void swapIndexes(int _i, int _j);
}
