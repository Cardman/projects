package aiki.beans;

import code.util.CustList;

public final class BeanDisplayListSec<E> extends BeanDisplayListCommon<E> {
    private final BeanDisplayEltSec<E> display;

    public BeanDisplayListSec(BeanDisplayEltSec<E> _d) {
        this.display = _d;
    }

    public void display(CommonBean _rend, CustList<E> _info, int _enable) {
        _rend.initPage();
        for (E e: cond(_info,_enable)) {
            _rend.initLine();
            _rend.paintMetaLabelDisk();
            display.displayEltSec(_rend, e);
            _rend.feedParents();
        }
        _rend.feedParents();
    }
}
