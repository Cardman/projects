package code.gui;

import code.gui.events.AbsChangeListener;
import code.gui.events.WrChangeListener;

import javax.swing.*;

public final class Spinner extends CustComponent {

    private JSpinner spinner;

    private int min;
    private int max;
    private int step;

    public Spinner(int _value,int _min, int _max, int _step) {
        min = _min;
        max = _max;
        step = _step;
        if (invalid(_value,_min,_max)) {
            min = Integer.MIN_VALUE;
            max = Integer.MAX_VALUE;
            step = 1;
            spinner = new JSpinner();
        } else {
            SpinnerNumberModel model_ = new SpinnerNumberModel(_value,_min,_max,_step);
            spinner = new JSpinner(model_);
        }
    }
    public int getValue() {
        return (Integer) spinner.getValue();
    }

    public void addChangeListener(AbsChangeListener _listener) {
        spinner.addChangeListener(new WrChangeListener(_listener));
    }

    public boolean isEnabled() {
        return spinner.isEnabled();
    }

    public void setEnabled(boolean _enabled) {
        spinner.setEnabled(_enabled);
    }

    @Override
    protected JComponent getNatComponent() {
        return spinner;
    }

    public void setRange(int _min, int _max) {
        if (invalid(getValue(), _min, _max)) {
            return;
        }
        max = _max;
        min = _min;
        updateModel();
    }

    public void setRangeValue(int _value,int _min, int _max) {
        if (invalid(_value, _min, _max)) {
            return;
        }
        max = _max;
        min = _min;
        updateModel(_value);
    }
    public int getMin() {
        return min;
    }
    public void setMin(int _min) {
        if (invalid(getValue(), _min, max)) {
            return;
        }
        min = _min;
        updateModel();
    }

    public int getMax() {
        return max;
    }
    public void setMax(int _max) {
        if (invalid(getValue(), min, _max)) {
            return;
        }
        max = _max;
        updateModel();
    }

    public void setValue(int _value) {
        if (invalid(_value, min, max)) {
            return;
        }
        updateModel(_value);
    }

    public int getStep() {
        return step;
    }
    public void setStep(int _step) {
        step = _step;
        updateModel();
    }
    private static boolean invalid(int _value, int _min, int _max) {
        if (_value < _min) {
            return true;
        }
        return _value > _max;
    }
    private void updateModel() {
        SpinnerNumberModel model_ = new SpinnerNumberModel(getValue(),min,max,step);
        spinner.setModel(model_);
    }
    private void updateModel(int _value) {
        SpinnerNumberModel model_ = new SpinnerNumberModel(_value,min,max,step);
        spinner.setModel(model_);
    }

}
