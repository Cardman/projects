package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.initialize.*;

public final class SpinnerStruct extends CustComponentStruct {
    private final AbsSpinner spinner;

    public SpinnerStruct(String _className, Struct _value, Struct _min, Struct _max, Struct _step, AbsCompoFactory _compoFactory) {
        super(_className);
        spinner = _compoFactory.newSpinner(((NumberStruct)_value).longStruct(),((NumberStruct)_min).longStruct(),((NumberStruct)_max).longStruct(),((NumberStruct)_step).longStruct());
    }

    public void addChangeListener(Struct _l) {
        if (_l instanceof AbsChangeListener) {
            spinner.addChangeListener((AbsChangeListener) _l);
        }
    }

    public void setRange(Struct _min, Struct _max) {
        spinner.range(((NumberStruct)_min).longStruct(),((NumberStruct)_max).longStruct());
    }

    public void setRangeValue(Struct _value, Struct _min, Struct _max) {
        spinner.setRangeValue(((NumberStruct)_value).longStruct(),((NumberStruct)_min).longStruct(),((NumberStruct)_max).longStruct());
    }

    public Struct getMin() {
        return new IntStruct(spinner.getMin());
    }
    public Struct getMinLong() {
        return new LongStruct(spinner.minLong());
    }

    public void setMin(Struct _min) {
        spinner.setMin(((NumberStruct)_min).longStruct());
    }

    public Struct getMax() {
        return new IntStruct(spinner.getMax());
    }
    public Struct getMaxLong() {
        return new LongStruct(spinner.maxLong());
    }
    public void setMax(Struct _max) {
        spinner.setMax(((NumberStruct)_max).longStruct());
    }

    public Struct getStep() {
        return new IntStruct(spinner.getStep());
    }
    public Struct getStepLong() {
        return new LongStruct(spinner.stepLong());
    }
    public void setStep(Struct _step) {
        spinner.setStep(((NumberStruct)_step).longStruct());
    }

    public Struct getValue() {
        return new IntStruct(spinner.getValue());
    }
    public Struct getValueLong() {
        return new LongStruct(spinner.valueLong());
    }
    public void setValue(Struct _step) {
        spinner.setValue(((NumberStruct)_step).longStruct());
    }


    @Override
    protected AbsCustComponent getComponent() {
        return spinner;
    }
}
