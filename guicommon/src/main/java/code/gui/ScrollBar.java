package code.gui;

import javax.swing.*;

public final class ScrollBar extends CustComponent {
    private JScrollBar scrollBar;
    ScrollBar(JScrollBar _scroll) {
        scrollBar = _scroll;
    }
    public int getValue() {
        return scrollBar.getValue();
    }
    public void setValue(int _value) {
        scrollBar.setValue(_value);
    }
    @Override
    public JComponent getComponent() {
        return scrollBar;
    }
}
