package code.util.ints;
import code.util.CustList;

public interface Listable<T> extends Iterable<T>, Countable {

    Iterable<T> getList();
    CustList<T> getReverse();

    void add(T _e);

    void addAllElts(Listable<T> _c);

    void clear();

    boolean isValidIndex(int _index);

    void set(int _index, T _element);

    void add(int _index, T _element);

    void remove(int _index);

    T first();

    T last();

    CustList<T> sub(int _from, int _to);

    void swapIndexes(int _i, int _j);
}
