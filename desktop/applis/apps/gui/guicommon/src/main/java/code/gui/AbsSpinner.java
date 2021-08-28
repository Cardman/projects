package code.gui;

import code.gui.events.AbsChangeListener;

public interface AbsSpinner extends AbsCustComponent {
    void updateModel();
    void updateModel(int _value);

    void addChangeListener(AbsChangeListener _list);

    boolean isEnabled();

    void setEnabled(boolean _value);

    void setRange(int _a, int _b);
    void range(int _min, int _max);
    void defValues();
    void mod(int _value, int _min, int _max, int _step);
    void setRangeValue(int _a, int _b, int _c);
    void rangeValue(int _value, int _min, int _max);

    void min(int _min);
    void max(int _max);
    int getMin();

    void setMin(int _value);

    int getMax();

    void setMax(int _value);

    int getStep();

    void setStep(int _value);

    int getValue();

    void setValue(int _value);
}
