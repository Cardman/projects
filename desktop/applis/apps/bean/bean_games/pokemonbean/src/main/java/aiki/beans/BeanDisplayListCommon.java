package aiki.beans;

import code.util.CustList;

public abstract class BeanDisplayListCommon<E> extends BeanDisplayLsCommon<E> {

    protected BeanDisplayListCommon() {
    }

    public void display(CommonBean _rend, CustList<E> _info, int _cond, String _file, String _key, String... _values) {
        _rend.display(cond(_info,_cond), _file, _key,_values);
        display(_rend,_info,_cond);
    }

    public void displayHead(CommonBean _rend, CustList<E> _info, int _cond, String _file, String _key, String... _values) {
        _rend.display(cond(_info,_cond), _file, _key,_values);
    }

    public void display(CommonBean _rend, CustList<E> _info, String _file, String _key, String... _values) {
        _rend.display(_info, _file, _key,_values);
        display(_rend,_info);
    }

    public void display(CommonBean _rend, CustList<E> _info) {
        display(_rend,_info, CommonBean.TRUE_VALUE);
    }

    public abstract void display(CommonBean _rend, CustList<E> _info, int _enable);
}
