package aiki.beans;

import code.util.*;

public final class BeanDisplayList<E> {
    private final BeanDisplayElt<E> display;

    public BeanDisplayList(BeanDisplayElt<E> _d) {
        this.display = _d;
    }

    public void display(CommonBean _rend, CustList<E> _info, int _cond, String _file, String _key, String... _values) {
        _rend.display(_file,cond(_info,_cond),_key,_values);
        display(_rend,_info,_cond);
    }

    public void display(CommonBean _rend, CustList<E> _info, String _file, String _key, String... _values) {
        _rend.display(_file,_info,_key,_values);
        display(_rend,_info);
    }

    public void displayGrid(CommonBean _rend, CustList<E> _info, String _file, String _key, String... _cols) {
        _rend.displayHead(_info,_file,_key,_cols);
        for (E e: cond(_info,CommonBean.TRUE_VALUE)) {
            display.displayElt(_rend, e);
        }
        _rend.feedParents();
    }
    public void display(CommonBean _rend, CustList<E> _info) {
        display(_rend,_info, CommonBean.TRUE_VALUE);
    }

    public void display(CommonBean _rend, CustList<E> _info, int _enable) {
        _rend.initPage();
        for (E e: cond(_info,_enable)) {
            _rend.initLine();
            _rend.paintMetaLabelDisk();
            display.displayElt(_rend, e);
            _rend.feedParents();
        }
        _rend.feedParents();
    }
    private CustList<E> cond(CustList<E> _in, int _enable) {
        int en_;
        if (_in.isEmpty()) {
            en_ = CommonBean.FALSE_VALUE;
        } else {
            en_ = _enable;
        }
        if (en_ == CommonBean.TRUE_VALUE) {
            return _in;
        }
        return new CustList<E>();
    }
}
