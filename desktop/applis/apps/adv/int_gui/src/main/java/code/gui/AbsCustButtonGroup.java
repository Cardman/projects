package code.gui;

import code.util.CustList;
import code.util.StringList;

public interface AbsCustButtonGroup {

    void add(AbsRadioButton _b);

    void add(AbsRadioButton _b, String _value);
    CustList<AbsRadioButton> getGroup();

    StringList getValues();

    AbsRadioButton getSelected();

    void clearSelection();

    int getButtonCount();
}
