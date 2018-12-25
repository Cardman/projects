package code.util.ints;
import code.util.Numbers;

public interface Listable<T> extends Iterable<T>,SimpleIterable , Countable, SimpleList {

    Iterable<T> getList();
    Listable<T> getReverse();

    void add(T _e);

    void addAllElts(Listable<T> _c);

    void clear();

    boolean isValidIndex(int _index);

    @Override
    T get(int _index);

    void set(int _index, T _element);

    void add(int _index, T _element);

    void removeAt(Number _index);

    void remove(int _index);

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
