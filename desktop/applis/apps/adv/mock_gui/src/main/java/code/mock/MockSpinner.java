package code.mock;

import code.gui.AbsSpinner;
import code.gui.events.AbsChangeListener;
import code.util.CustList;

public final class MockSpinner extends MockInput implements AbsSpinner {
    private long min;
    private long max;
    private final CustList<AbsChangeListener> changeListeners = new CustList<AbsChangeListener>();
    private long value;
    private long step;

    public MockSpinner(long _value,long _min, long _max, long _step) {
        min = _min;
        max = _max;
        step = _step;
        mod(_value, _min, _max, _step);
    }

    private void val(long _value, long _min, long _max, long _step) {
        min = _min;
        max = _max;
        step = _step;
        value = _value;
    }

    public static boolean invalidSpinner(long _value, long _min, long _max) {
        if (_value < _min) {
            return true;
        } else {
            return _value > _max;
        }
    }
    @Override
    public void updateModel() {
        mod(valueLong(), min, max, step);
    }
    @Override
    public void updateModel(long _value) {
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
    public void setRange(long _min, long _max) {
        mod(valueLong(), _min, _max, stepLong());
    }

    @Override
    public void range(long _min, long _max) {
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
    public void mod(long _value, long _min, long _max, long _step) {
        if (invalidSpinner(_value, _min, _max)) {
            defValues();
        } else {
            val(_value, _min, _max, _step);
        }
    }

    @Override
    public void setRangeValue(long _value,long _min, long _max) {
        mod(_value,_min,_max,stepLong());
    }

    @Override
    public void rangeValue(long _value, long _min, long _max) {
        range(_min, _max);
        updateModel(_value);
    }

    @Override
    public void min(long _m) {
        if (_m != min) {
            min = _m;
            fireEvent();
        }
    }

    @Override
    public void max(long _m) {
        if (_m != max) {
            max = _m;
            fireEvent();
        }
    }

    @Override
    public int getMin() {
        return (int) minLong();
    }

    @Override
    public long minLong() {
        return min;
    }

    @Override
    public void setMin(long _m) {
        if (!invalidSpinner(valueLong(), _m, maxLong())) {
            min(_m);
        }
    }

    @Override
    public int getMax() {
        return (int) maxLong();
    }

    @Override
    public long maxLong() {
        return max;
    }

    @Override
    public void setMax(long _m) {
        if (!invalidSpinner(valueLong(), minLong(), _m)) {
            max(_m);
        }
    }

    @Override
    public int getStep() {
        return (int) stepLong();
    }

    @Override
    public long stepLong() {
        return step;
    }

    @Override
    public void setStep(long _s) {
        if (_s != step) {
            step = _s;
            fireEvent();
        }
    }

    @Override
    public int getValue() {
        return (int) valueLong();
    }

    @Override
    public long valueLong() {
        return value;
    }

    @Override
    public void setValue(long _v) {
        if (!invalidSpinner(_v, minLong(), maxLong()) && _v != value) {
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
