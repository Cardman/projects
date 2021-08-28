package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsCustComponent;
import code.gui.AbsSlider;
import code.gui.events.AbsChangeListener;
import code.gui.initialize.AbsCompoFactory;

public final class SliderStruct extends InputStruct {
    private final AbsSlider slider;
    protected SliderStruct(String _className, AbsCompoFactory _comp) {
        super(_className);
        slider = _comp.newAbsSlider();
    }
    protected SliderStruct(String _className,Struct _o, AbsCompoFactory _comp) {
        super(_className);
        slider = _comp.newAbsSlider(((NumberStruct)_o).intStruct());
    }
    protected SliderStruct(String _className,Struct _min,Struct _max, AbsCompoFactory _comp) {
        super(_className);
        slider = _comp.newAbsSlider(((NumberStruct)_min).intStruct(),((NumberStruct)_max).intStruct());
    }
    protected SliderStruct(String _className,Struct _min,Struct _max, Struct _value, AbsCompoFactory _comp) {
        super(_className);
        slider = _comp.newAbsSlider(((NumberStruct)_min).intStruct(),((NumberStruct)_max).intStruct(),((NumberStruct)_value).intStruct());
    }
    protected SliderStruct(String _className,Struct _o,Struct _min,Struct _max, Struct _value, AbsCompoFactory _comp) {
        super(_className);
        slider = _comp.newAbsSlider(((NumberStruct)_o).intStruct(),((NumberStruct)_min).intStruct(),((NumberStruct)_max).intStruct(),((NumberStruct)_value).intStruct());
    }

    public void addChangeListener(Struct _l) {
        if (_l instanceof AbsChangeListener) {
            slider.addChangeListener((AbsChangeListener) _l);
        }
    }

    public Struct getValue() {
        return new IntStruct(slider.getValue());
    }

    public void setValue(Struct _value) {
        slider.setValue(((NumberStruct)_value).intStruct());
    }

    public Struct getMin() {
        return new IntStruct(slider.getMinimum());
    }

    public void setMin(Struct _min) {
        slider.setMinimum(((NumberStruct)_min).intStruct());
    }

    public Struct getMax() {
        return new IntStruct(slider.getMaximum());
    }

    public void setMax(Struct _max) {
        slider.setMaximum(((NumberStruct)_max).intStruct());
    }

    public Struct getOrientation() {
        return new IntStruct(slider.getOrientation());
    }

    public void setOrientation(Struct _orientation) {
        slider.setOrientation(((NumberStruct)_orientation).intStruct());
    }
    @Override
    public Struct isEnabled() {
        return BooleanStruct.of(slider.isEnabled());
    }

    @Override
    public void setEnabled(Struct _enabled) {
        slider.setEnabled(BooleanStruct.isTrue(_enabled));
    }

    @Override
    protected AbsCustComponent getComponent() {
        return slider;
    }
}
