package code.gui;

import code.gui.events.AbsActionListener;
import code.gui.events.AbsAdvActionListener;

public interface AbsPlainButton extends AbsButton {
    String getText();

    void setText(String _value);

    void addActionListener(AbsActionListener _list);
    void addActionListener(AbsAdvActionListener _list);
}
