package code.gui;

import code.gui.events.AbsChangeListener;
import code.util.CustList;

public interface AbsSlider extends AbsCustComponent {
    void addChangeListener(AbsChangeListener _l);
    void addChangeListenerMap(AbsChangeListener _l);
    void removeChangeListener(AbsChangeListener _l);
    void removeChangeListenerMap(AbsChangeListener _l);
    CustList<AbsChangeListener> getChangeListeners();

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
    void properties(int _min, int _max, int _value);
}
