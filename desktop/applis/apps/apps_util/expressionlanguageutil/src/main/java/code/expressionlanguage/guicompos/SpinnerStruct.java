package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsCustComponent;
import code.gui.AbsSpinner;
import code.gui.events.AbsChangeListener;
import code.gui.initialize.AbsCompoFactory;

public final class SpinnerStruct extends InputStruct {
    private AbsSpinner spinner;

    public SpinnerStruct(String _className, Struct _value, Struct _min, Struct _max, Struct _step, AbsCompoFactory _compoFactory) {
        super(_className);
        spinner = _compoFactory.newSpinner(((NumberStruct)_value).intStruct(),((NumberStruct)_min).intStruct(),((NumberStruct)_max).intStruct(),((NumberStruct)_step).intStruct());
    }

    @Override
    public Struct isEnabled() {
        return BooleanStruct.of(spinner.isEnabled());
    }

    @Override
    public void setEnabled(Struct _enabled) {
        spinner.setEnabled(BooleanStruct.isTrue(_enabled));
    }

    public void addChangeListener(Struct _l) {
        if (_l instanceof AbsChangeListener) {
            spinner.addChangeListener((AbsChangeListener) _l);
        }
    }

    public void setRange(Struct _min, Struct _max) {
        spinner.range(((NumberStruct)_min).intStruct(),((NumberStruct)_max).intStruct());
    }

    public void setRangeValue(Struct _value, Struct _min, Struct _max) {
        spinner.setRangeValue(((NumberStruct)_value).intStruct(),((NumberStruct)_min).intStruct(),((NumberStruct)_max).intStruct());
    }

    public Struct getMin() {
        return new IntStruct(spinner.getMin());
    }

    public void setMin(Struct _min) {
        spinner.setMin(((NumberStruct)_min).intStruct());
    }

    public Struct getMax() {
        return new IntStruct(spinner.getMax());
    }

    public void setMax(Struct _max) {
        spinner.setMax(((NumberStruct)_max).intStruct());
    }

    public Struct getStep() {
        return new IntStruct(spinner.getStep());
    }

    public void setStep(Struct _step) {
        spinner.setStep(((NumberStruct)_step).intStruct());
    }

    public Struct getValue() {
        return new IntStruct(spinner.getValue());
    }

    public void setValue(Struct _step) {
        spinner.setValue(((NumberStruct)_step).intStruct());
    }


    @Override
    protected AbsCustComponent getComponent() {
        return spinner;
    }
}
