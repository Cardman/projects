/**
    */
package code.util;
import code.util.comparators.NatComparator;
import code.util.ints.Cmp;
import code.util.ints.Listable;

/** Not compared in tests
    @author Cardman
 */
public final class SortableCustList<T extends Cmp<T>> extends AbEqList<T> {

    /**
    */
    public SortableCustList() {
    }

    /**
        @param _elements
    */
    public SortableCustList(T... _elements) {
        super(_elements);
    }

    /**
        @param _c
    */
    public SortableCustList(Listable<T> _c) {
        super(_c);
    }

    public void sort() {
        sortElts(new NatComparator<T>());
    }

    @Override
    public boolean match(T _one, T _two) {
        return _one.eq(_two);
    }
}
