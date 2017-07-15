package code.util.ints;


public interface Settable<E> extends Listable<E> {

//    int size();

//    boolean isEmpty();

    boolean addEl(E _o);

    boolean containsObj(E _o);

//    Object[] toArray();

//    <T> T[] toArray(T[] _a);

//    boolean add(E _e);

    void removeObj(E _o);

//    boolean containsAll(Collection<?> _c);

//    boolean addAll(Collection<? extends E> _c);

//    boolean retainAll(Collection<?> _c);

//    void retainAllElements(Listable<? extends E> _c);
//    boolean removeAll(Collection<?> _c);
//    void removeAllElements(Listable<? extends E> _c);

    boolean containsAllObj(Listable<? extends E> _list);

//    void clear();

}
