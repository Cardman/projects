package code.gui;

import code.gui.events.AbsActionListener;
import code.gui.events.AbsAdvActionListener;

public interface AbsPlainButton extends AbsCustComponent {
    String getText();

    void setText(String _value);

    boolean isEnabled();

    void setEnabled(boolean _value);

    void addActionListener(AbsActionListener _list);
    void addActionListener(AbsAdvActionListener _list);
}
