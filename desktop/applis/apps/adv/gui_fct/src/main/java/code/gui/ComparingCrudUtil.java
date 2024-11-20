package code.gui;

import code.util.CustList;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

public final class ComparingCrudUtil<E> {
    private final Comparing<E> comparing;

    public ComparingCrudUtil(Comparing<E> _c) {
        this.comparing = _c;
    }

    public int shiftPair(CustList<E> _ls, int _index) {
        if (!_ls.isValidIndex(_index)) {
            possibleSort(_ls);
            return _index;
        }
        E e_ = _ls.get(_index);
        possibleSort(_ls);
        if (comparing == null) {
            return _index;
        }
        return indexPair(_ls, e_);
    }

    private void possibleSort(CustList<E> _ls) {
        if (comparing != null) {
            _ls.sortElts(comparing);
        }
    }

    public int indexPair(CustList<E> _ls, E _elt) {
        int len_ = _ls.size();
        for (int i = 0; i < len_; i++) {
            if (comparing.compare(_elt,_ls.get(i)) == SortConstants.EQ_CMP) {
                return i;
            }
        }
        return -1;
    }
}
