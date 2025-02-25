package aiki.beans;

import code.util.*;

public final class BeanDisplayListGrid<E> extends BeanDisplayLsCommon<E> {
    private final BeanDisplayEltGrid<E> display;

    public BeanDisplayListGrid(BeanDisplayEltGrid<E> _d) {
        this.display = _d;
    }
    public void displayGridParam(CommonBean _rend, CustList<E> _info, String[] _values, String _file, String _keyTitle, String... _cols) {
        _rend.displayHeadParam(_info, _values, _file, _keyTitle, _cols);
        for (E e: cond(_info,CommonBean.TRUE_VALUE)) {
            display.displayEltGrid(_rend, e);
        }
        _rend.feedParents();
    }

    public void displayGrid(CommonBean _rend, CustList<E> _info, String _file, String _key, String... _cols) {
        _rend.displayHead(_info,_file,_key,_cols);
        for (E e: cond(_info,CommonBean.TRUE_VALUE)) {
            display.displayEltGrid(_rend, e);
        }
        _rend.feedParents();
    }

}
