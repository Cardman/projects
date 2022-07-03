package code.mock;

import code.gui.AbsSlider;
import code.gui.events.AbsChangeListener;
import code.util.CustList;

public final class MockSlider extends MockInput implements AbsSlider {
    private int orientation;
    private int value;
    private final CustList<AbsChangeListener> changeListeners = new CustList<AbsChangeListener>();
    private int minimum;
    private int maximum;
    public MockSlider() {
        this(0,100);
    }
    public MockSlider(int _o) {
        this(0,100);
        setOrientation(_o);
    }
    public MockSlider(int _min,int _max) {
        this(_min,_max,(_min+_max)/2);
    }
    public MockSlider(int _min,int _max,int _v) {
        minimum = _min;
        maximum = _max;
        value = _v;
    }
    public MockSlider(int _o,int _min,int _max,int _v) {
        minimum = _min;
        maximum = _max;
        value = _v;
        setOrientation(_o);
    }

    @Override
    public int getOrientation() {
        return orientation;
    }

    @Override
    public void setOrientation(int _or) {
        if (_or == 0) {
            setHorizontal();
        } else {
            setVertical();
        }
    }

    @Override
    public void setHorizontal() {
        orientation = 0;
    }

    @Override
    public void setVertical() {
        orientation = 1;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int _max) {
        if (_max != maximum) {
            this.maximum = _max;
            fireEvent();
        }
    }

    @Override
    public void addChangeListener(AbsChangeListener _l) {
        changeListeners.add(_l);
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int _min) {
        if (_min != minimum) {
            this.minimum = _min;
            fireEvent();
        }
    }

    public CustList<AbsChangeListener> getChangeListeners() {
        return changeListeners;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int _val) {
        if (_val != value) {
            this.value = _val;
            fireEvent();
        }
    }

    private void fireEvent() {
        for (AbsChangeListener a: getChangeListeners()) {
            a.stateChanged();
        }
    }
}
