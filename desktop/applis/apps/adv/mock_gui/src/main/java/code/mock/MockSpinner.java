package code.mock;

import code.gui.AbsSpinner;
import code.gui.events.AbsChangeListener;
import code.util.CustList;

public final class MockSpinner extends MockInput implements AbsSpinner {
    private int min;
    private int max;
    private final CustList<AbsChangeListener> changeListeners = new CustList<AbsChangeListener>();
    private int value;
    private int step;

    public MockSpinner(int _value,int _min, int _max, int _step) {
        min = _min;
        max = _max;
        step = _step;
        mod(_value, _min, _max, _step);
    }

    private void val(int _value, int _min, int _max, int _step) {
        min = _min;
        max = _max;
        step = _step;
        value = _value;
    }

    public static boolean invalidSpinner(int _value, int _min, int _max) {
        if (_value < _min) {
            return true;
        } else {
            return _value > _max;
        }
    }
    @Override
    public void updateModel() {
        mod(getValue(), min, max, step);
    }
    @Override
    public void updateModel(int _value) {
        mod(_value, min, max, step);
    }

    public CustList<AbsChangeListener> getChangeListeners() {
        return changeListeners;
    }

    @Override
    public void addChangeListener(AbsChangeListener _l) {
        changeListeners.add(_l);
    }

    @Override
    public void setRange(int _min, int _max) {
        mod(getValue(), _min, _max, getStep());
    }

    @Override
    public void range(int _min, int _max) {
        max = _max;
        min = _min;
    }

    @Override
    public void defValues() {
        min = Integer.MIN_VALUE;
        max = Integer.MAX_VALUE;
        step = 1;
    }

    @Override
    public void mod(int _value, int _min, int _max, int _step) {
        if (invalidSpinner(_value, _min, _max)) {
            defValues();
        } else {
            val(_value, _min, _max, _step);
        }
    }

    @Override
    public void setRangeValue(int _value,int _min, int _max) {
        mod(_value,_min,_max,getStep());
    }

    @Override
    public void rangeValue(int _value, int _min, int _max) {
        range(_min, _max);
        updateModel(_value);
    }

    @Override
    public void min(int _m) {
        if (_m != min) {
            min = _m;
            fireEvent();
        }
    }

    @Override
    public void max(int _m) {
        if (_m != max) {
            max = _m;
            fireEvent();
        }
    }

    @Override
    public int getMin() {
        return min;
    }

    @Override
    public void setMin(int _m) {
        if (!invalidSpinner(getValue(), _m, getMax())) {
            min(_m);
        }
    }

    @Override
    public int getMax() {
        return max;
    }

    @Override
    public void setMax(int _m) {
        if (!invalidSpinner(getValue(), getMin(), _m)) {
            max(_m);
        }
    }

    @Override
    public int getStep() {
        return step;
    }

    @Override
    public void setStep(int _s) {
        if (_s != step) {
            step = _s;
            fireEvent();
        }
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(int _v) {
        if (!invalidSpinner(_v, getMin(), getMax()) && _v != value) {
            value = _v;
            fireEvent();
        }

    }

    private void fireEvent() {
        for (AbsChangeListener a: getChangeListeners()) {
            a.stateChanged();
        }
    }
}
