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

    public Listable<Object> getSelectedValuesLs() {
        Listable<Object> l_ = new CustList<Object>();
        for (Object o: getSelectedValues()) {
            l_.add(o);
        }
        return l_;
    }
}
