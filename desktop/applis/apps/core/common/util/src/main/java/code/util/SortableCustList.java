/**
    */
package code.util;
import code.util.comparators.NatComparator;
import code.util.ints.Cmp;
import code.util.ints.Equallable;
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

    public boolean eq(SortableCustList<T> _b) {
        int len_ = size();
        if (_b.size() != len_) {
            return false;
        }
        for (int i = FIRST_INDEX; i < len_; i++) {
            T e_ = get(i);
            T f_ = _b.get(i);
            if (!e_.eq(f_)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int indexOfObj(T _element, int _from) {
        int s_ = size();
        for (int i = _from; i < s_; i++) {
            T e_ = get(i);
            if (_element.eq(e_)) {
                return i;
            }
        }
        return INDEX_NOT_FOUND_ELT;
    }

}
