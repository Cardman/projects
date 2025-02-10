package aiki.beans;

import code.util.*;

public final class BeanDisplayList<E> {
    private final BeanDisplayElt<E> display;

    public BeanDisplayList(BeanDisplayElt<E> _d) {
        this.display = _d;
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
