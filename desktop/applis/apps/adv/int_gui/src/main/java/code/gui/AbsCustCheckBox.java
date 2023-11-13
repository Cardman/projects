package code.gui;

import code.gui.events.AbsActionListener;
import code.gui.events.AbsAdvActionListener;
import code.util.CustList;

public interface AbsCustCheckBox extends AbsCustComponent {
    void addActionListener(AbsActionListener _l);
    void addActionListener(AbsAdvActionListener _l);
    void addActionListenerMap(AbsAdvActionListener _l);
    void removeActionListener(AbsActionListener _l);
    void removeActionListener(AbsAdvActionListener _l);
    void removeActionListenerMap(AbsAdvActionListener _l);
    CustList<AbsActionListener> getActionListeners();

    void setSelected(boolean _value);

    boolean isSelected();

    void setText(String _txt);

    String getText();

}
