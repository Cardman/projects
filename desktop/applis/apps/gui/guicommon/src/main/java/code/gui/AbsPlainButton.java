package code.gui;

import code.gui.events.AbsActionListener;

public interface AbsPlainButton extends AbsCustComponent {
    String getText();

    void setText(String _value);

    boolean isEnabled();

    void setEnabled(boolean _value);

    void addActionListener(AbsActionListener _list);
}
