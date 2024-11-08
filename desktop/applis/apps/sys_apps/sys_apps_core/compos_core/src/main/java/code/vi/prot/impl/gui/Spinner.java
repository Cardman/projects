package code.vi.prot.impl.gui;

import code.gui.AbsSpinner;
import code.gui.events.AbsChangeListener;
import code.vi.prot.impl.gui.events.WrChangeListener;

import javax.swing.*;
import java.awt.*;

public final class Spinner extends CustComponent implements AbsSpinner {

    private final JSpinner sp;
    private SpinnerNumberModel model;

    private long min;
    private long max;
    private long step;

    public Spinner(long _value,long _min, long _max, long _step) {
        min = _min;
        max = _max;
        step = _step;
        model = new SpinnerNumberModel();
        sp = new JSpinner(model);
        mod(_value, _min, _max, _step);
        sp.setMaximumSize(new Dimension(Integer.MAX_VALUE, sp.getPreferredSize().height));
    }

    public void defValues() {
        min = Integer.MIN_VALUE;
        max = Integer.MAX_VALUE;
        step = 1;
    }

    @Override
    public void mod(long _value, long _min, long _max, long _step) {
        modLong(_value,_min,_max,_step);
    }

    public void modLong(Long _value, Long _min, Long _max, Long _step) {
        try {
            SpinnerNumberModel model_ = new SpinnerNumberModel(_value, _min, _max, _step);
            sp.setModel(model_);
            model = model_;
        } catch (Exception e) {
            model = new SpinnerNumberModel();
            defValues();
        }
    }

    public int getValue() {
        return model.getNumber().intValue();
    }

    @Override
    public long valueLong() {
        return model.getNumber().longValue();
    }

    public void addChangeListener(AbsChangeListener _listener) {
        sp.addChangeListener(new WrChangeListener(_listener));
    }

    @Override
    public JComponent getNatComponent() {
        return sp;
    }

    public void setRange(long _min, long _max) {
        range(_min, _max);
    }

    public void range(long _min, long _max) {
        max = _max;
        min = _min;
        updateModel();
    }

    public void setRangeValue(long _value,long _min, long _max) {
        rangeValue(_value, _min, _max);
    }

    public void rangeValue(long _value, long _min, long _max) {
        max = _max;
        min = _min;
        updateModel(_value);
    }

    public int getMin() {
        return (int) min;
    }

    @Override
    public long minLong() {
        return min;
    }

    public void setMin(long _min) {
        min(_min);
    }

    public void min(long _min) {
        min = _min;
        updateModel();
    }

    public int getMax() {
        return (int) max;
    }

    @Override
    public long maxLong() {
        return max;
    }

    public void setMax(long _max) {
        max(_max);
    }

    public void max(long _max) {
        max = _max;
        updateModel();
    }

    public void setValue(long _value) {
        value(_value);
    }

    public void value(long _value) {
        updateModel(_value);
    }

    public int getStep() {
        return (int) step;
    }

    @Override
    public long stepLong() {
        return step;
    }

    public void setStep(long _step) {
        step = _step;
        updateModel();
    }
    public void updateModel() {
        mod(getValue(), min, max, step);
    }
    public void updateModel(long _value) {
        mod(_value, min, max, step);
    }

}
