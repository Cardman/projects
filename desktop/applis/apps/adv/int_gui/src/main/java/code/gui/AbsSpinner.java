package code.gui;

import code.gui.events.AbsChangeListener;

public interface AbsSpinner extends AbsCustComponent {
    void updateModel();
    void updateModel(long _value);

    void addChangeListener(AbsChangeListener _list);

    void setRange(long _a, long _b);
    void range(long _min, long _max);
    void defValues();
    void mod(long _value, long _min, long _max, long _step);
    void setRangeValue(long _a, long _b, long _c);
    void rangeValue(long _value, long _min, long _max);

    void min(long _min);
    void max(long _max);
    int getMin();
    long minLong();

    void setMin(long _value);

    int getMax();
    long maxLong();

    void setMax(long _value);

    int getStep();
    long stepLong();

    void setStep(long _value);

    int getValue();
    long valueLong();

    void setValue(long _value);
}
