package aiki.beans;

import code.util.*;

public final class BeanDisplayList<E> {
    private final BeanDisplayElt<E> display;

    public BeanDisplayList(BeanDisplayElt<E> _d) {
        this.display = _d;
    }

    public void display(CommonBean _rend, CustList<E> _info, String _file, String _key, String... _values) {
        _rend.display(_file,_info,_key,_values);
        display(_rend,_info);
    }

    public void displayGrid(CommonBean _rend, CustList<E> _info, String _file, String _key, String... _cols) {
        _rend.displayHead(_info,_file,_key,_cols);
        for (E e: _info) {
            display.displayElt(_rend, e);
        }
        _rend.feedParents();
    }
    public void display(CommonBean _rend, CustList<E> _info) {
        _rend.initPage();
        for (E e: _info) {
            _rend.initLine();
            _rend.paintMetaLabelDisk();
            display.displayElt(_rend, e);
            _rend.feedParents();
        }
        _rend.feedParents();
    }
}
