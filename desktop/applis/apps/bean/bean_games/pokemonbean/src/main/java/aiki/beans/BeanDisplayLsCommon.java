package aiki.beans;

import code.util.CustList;

public class BeanDisplayLsCommon<E> {

    protected CustList<E> cond(CustList<E> _in, int _enable) {
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
