package code.gui;

import code.gui.events.AbsAdvActionListener;

public interface AbsTextField extends AbsTxtComponent {

    void addActionListener(AbsAdvActionListener _list);
    void addActionListenerMap(AbsAdvActionListener _list);
    void removeActionListener(AbsAdvActionListener _list);
    void removeActionListenerMap(AbsAdvActionListener _list);


    void setEditable(boolean _value);
}
