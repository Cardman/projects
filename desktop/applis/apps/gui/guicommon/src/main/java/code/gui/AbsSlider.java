package code.gui;

import code.gui.events.AbsChangeListener;

public interface AbsSlider extends AbsCustComponent {
    void addChangeListener(AbsChangeListener _l);

    int getValue();

    void setValue(int _value);

    int getMinimum();

    void setMinimum(int _value);

    int getMaximum();

    void setMaximum(int _value);

    int getOrientation();

    void setOrientation(int _value);
    void setHorizontal();
    void setVertical();
    boolean isEnabled();

    void setEnabled(boolean _value);
}
