package code.gui;

import code.gui.events.AbsActionListener;
import code.gui.events.AbsAdvActionListener;

public interface AbsTextField extends AbsTxtComponent {

    void addActionListener(AbsActionListener _list);

    void addActionListener(AbsAdvActionListener _list);
    void addActionListenerMap(AbsAdvActionListener _list);


    void setEditable(boolean _value);
}
