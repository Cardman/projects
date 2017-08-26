package code.util.ints;
import java.util.ListIterator;

public interface MidListable<T extends Equallable<T>> extends Iterable<T> {

    int size();

    boolean isEmpty();

    boolean contains(T _o);

    Object[] toArray();

    void add(T _e);

    void remove(T _o);

    void clear();

    T get(int _index);

    void set(int _index, T _element);

    void remove(int _index);

    ListIterator<T> listIterator();

    ListIterator<T> listIterator(int _index);

    Listable<T> sub(int _from, int _to);
}
