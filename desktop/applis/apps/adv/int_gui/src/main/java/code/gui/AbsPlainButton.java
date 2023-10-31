package code.gui;

import code.gui.events.AbsActionListener;
import code.gui.events.AbsAdvActionListener;
import code.util.CustList;

public interface AbsPlainButton extends AbsButton {
    String getText();

    void setText(String _value);

    CustList<AbsActionListener> getActionListeners();
    void addActionListener(AbsActionListener _list);
    void addActionListenerMap(AbsActionListener _list);
    void removeActionListener(AbsActionListener _list);
    void removeActionListenerMap(AbsActionListener _list);
    void addActionListener(AbsAdvActionListener _list);
}
