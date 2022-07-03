package code.gui;

import code.gui.events.AbsActionListener;
import code.util.CustList;

public interface AbsCustCheckBox extends AbsCustComponent {
    void addActionListener(AbsActionListener _l);
    void removeActionListener(AbsActionListener _l);
    CustList<AbsActionListener> getActionListeners();

    void setSelected(boolean _value);

    boolean isSelected();

    void setText(String _txt);

    String getText();

    boolean isEnabled();

    void setEnabled(boolean _value);
}
