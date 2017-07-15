package code.util.ints;
import java.util.ListIterator;

public interface MidListable<T extends Equallable<T>> extends Iterable<T> {

    int size();

    boolean isEmpty();

    boolean contains(T _o);

    Object[] toArray();

    void add(T _e);

    void remove(T _o);

//    boolean containsAll(MidListable<? extends T> _c);
//
//    boolean addAll(MidListable<? extends T> _c);
//
//    boolean removeAll(MidListable<? extends T> _c);

    void clear();

    T get(int _index);

    void set(int _index, T _element);

//    T remove(int _index);
    void remove(int _index);

//    int indexOf(Object _o);

//    int lastIndexOf(Object _o);

    ListIterator<T> listIterator();

    ListIterator<T> listIterator(int _index);

//    Listable<T> subList(int _fromIndex, int _toIndex);

    Listable<T> sub(int _from, int _to);
}
