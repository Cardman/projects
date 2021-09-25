package code.vi.sys.impl.gui;

import code.gui.AbsSpinner;
import code.gui.FrameUtil;
import code.gui.events.AbsChangeListener;
import code.vi.sys.impl.gui.events.WrChangeListener;

import javax.swing.*;

public final class Spinner extends CustComponent implements AbsSpinner {

    private final JSpinner sp;

    private int min;
    private int max;
    private int step;

    public Spinner(int _value,int _min, int _max, int _step) {
        min = _min;
        max = _max;
        step = _step;
        sp = new JSpinner();
        model(_value, _min, _max, _step);
    }

    private void model(int _value, int _min, int _max, int _step) {
        FrameUtil.initModel(this, _value, _min, _max, _step);
    }

    public void defValues() {
        min = Integer.MIN_VALUE;
        max = Integer.MAX_VALUE;
        step = 1;
    }

    public void mod(int _value, int _min, int _max, int _step) {
        SpinnerNumberModel model_ = new SpinnerNumberModel(_value, _min, _max, _step);
        sp.setModel(model_);
    }

    public int getValue() {
        return (Integer) sp.getValue();
    }

    public void addChangeListener(AbsChangeListener _listener) {
        sp.addChangeListener(new WrChangeListener(_listener));
    }

    public boolean isEnabled() {
        return sp.isEnabled();
    }

    public void setEnabled(boolean _enabled) {
        sp.setEnabled(_enabled);
    }

    @Override
    public JComponent getNatComponent() {
        return sp;
    }

    public void setRange(int _min, int _max) {
        FrameUtil.rg(this,_min, _max);
    }

    public void range(int _min, int _max) {
        max = _max;
        min = _min;
        updateModel();
    }

    public void setRangeValue(int _value,int _min, int _max) {
        FrameUtil.rgValue(this,_value, _min, _max);
    }

    public void rangeValue(int _value, int _min, int _max) {
        max = _max;
        min = _min;
        updateModel(_value);
    }

    public int getMin() {
        return min;
    }
    public void setMin(int _min) {
        FrameUtil.mn(this,_min);
    }

    public void min(int _min) {
        min = _min;
        updateModel();
    }

    public int getMax() {
        return max;
    }
    public void setMax(int _max) {
        FrameUtil.mx(this,_max);
    }

    public void max(int _max) {
        max = _max;
        updateModel();
    }

    public void setValue(int _value) {
        value(_value);
    }

    public void value(int _value) {
        FrameUtil.vl(this,_value);
    }

    public int getStep() {
        return step;
    }
    public void setStep(int _step) {
        step = _step;
        updateModel();
    }
    public void updateModel() {
        mod(getValue(), min, max, step);
    }
    public void updateModel(int _value) {
        mod(_value, min, max, step);
    }

}
