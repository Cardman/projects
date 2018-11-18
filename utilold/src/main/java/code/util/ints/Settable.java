package code.util.ints;


public interface Settable<E> extends Listable<E> {

    boolean addEl(E _o);

    boolean containsObj(E _o);

    void removeObj(E _o);

    boolean containsAllObj(Listable<E> _list);

}
