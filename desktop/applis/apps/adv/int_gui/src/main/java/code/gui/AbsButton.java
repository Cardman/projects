package code.gui;

import code.gui.events.AbsActionListener;
import code.gui.events.AbsAdvActionListener;
import code.util.CustList;

public interface AbsButton extends AbsCustComponent {

    String getText();

    void setText(String _value);

    CustList<AbsActionListener> getActionListeners();
    void addActionListener(AbsActionListener _list);
    void addActionListenerMap(AbsAdvActionListener _list);
    void removeActionListener(AbsActionListener _list);
    void removeActionListener(AbsAdvActionListener _list);
    void removeActionListenerMap(AbsAdvActionListener _list);
    void addActionListener(AbsAdvActionListener _list);
}
