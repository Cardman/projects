package aiki.beans;

import code.util.*;

public final class BeanDisplayList<E> extends BeanDisplayListCommon<E> {
    private final BeanDisplayElt<E> display;

    public BeanDisplayList(BeanDisplayElt<E> _d) {
        this.display = _d;
    }

    public void display(CommonBean _rend, CustList<E> _info, int _enable) {
        _rend.initPage();
        for (E e: cond(_info,_enable)) {
            _rend.initLine();
            _rend.getBuilder().indent();
            _rend.paintMetaLabelDisk();
            display.displayElt(_rend, e);
            _rend.feedParents();
        }
        _rend.feedParents();
    }
}
