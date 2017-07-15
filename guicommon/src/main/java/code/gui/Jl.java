package code.gui;
import javax.swing.JList;
import javax.swing.ListModel;

import code.util.CustList;
import code.util.ints.Listable;

public final class Jl<T> extends JList {

    public Jl() {
    }

    public Jl(ListModel _dataModel) {
        super(_dataModel);
    }
//
//    @Override
//    public T getSelectedValue() {
//        return (T) super.getSelectedValue();
//    }

    public Listable<Object> getSelectedValuesLs() {
        Listable<Object> l_ = new CustList<Object>();
        for (Object o: getSelectedValues()) {
            l_.add(o);
        }
        return l_;
    }
//    public Jl(Object[] _listData) {
//        super(_listData);
//    }
//
//    public Jl(Vector<?> _listData) {
//        super(_listData);
//    }

}
