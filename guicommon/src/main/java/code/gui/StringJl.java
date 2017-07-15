package code.gui;
import javax.swing.JList;
import javax.swing.ListModel;

import code.util.StringList;

public final class StringJl extends JList {

    public StringJl() {
    }

    public StringJl(ListModel _dataModel) {
        super(_dataModel);
    }

    @Override
    public String getSelectedValue() {
        return (String) super.getSelectedValue();
    }

    public StringList getSelectedValuesLs() {
        StringList l_ = new StringList();
        for (Object o: getSelectedValues()) {
            l_.add((String)o);
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
