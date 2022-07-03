package code.mock;

import code.gui.AbsProgressBar;

public final class MockProgressBar extends MockCustComponent implements AbsProgressBar {
    private boolean horizontal;
    private int value;
    private int minimum;
    private int maximum;

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean _horizontal) {
        if (_horizontal) {
            setHorizontal();
        } else {
            setVertical();
        }
    }

    @Override
    public void setHorizontal() {
        horizontal = true;
    }

    @Override
    public void setVertical() {
        horizontal = false;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int _m) {
        this.maximum = _m;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int _m) {
        this.minimum = _m;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int _v) {
        this.value = _v;
    }
}
