package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.CustComponent;
import code.gui.Slider;

import javax.swing.event.ChangeListener;

public final class SliderStruct extends InputStruct {
    private Slider slider;
    protected SliderStruct(String _className) {
        super(_className);
        slider = new Slider();
    }
    protected SliderStruct(String _className,Struct _o) {
        super(_className);
        slider = new Slider(((NumberStruct)_o).intStruct());
    }
    protected SliderStruct(String _className,Struct _min,Struct _max) {
        super(_className);
        slider = new Slider(((NumberStruct)_min).intStruct(),((NumberStruct)_max).intStruct());
    }
    protected SliderStruct(String _className,Struct _min,Struct _max, Struct _value) {
        super(_className);
        slider = new Slider(((NumberStruct)_min).intStruct(),((NumberStruct)_max).intStruct(),((NumberStruct)_value).intStruct());
    }
    protected SliderStruct(String _className,Struct _o,Struct _min,Struct _max, Struct _value) {
        super(_className);
        slider = new Slider(((NumberStruct)_o).intStruct(),((NumberStruct)_min).intStruct(),((NumberStruct)_max).intStruct(),((NumberStruct)_value).intStruct());
    }

    public void addChangeListener(Struct _l) {
        if (_l instanceof ChangeListener) {
            slider.addChangeListener((ChangeListener) _l);
        }
    }

    public void removeChangeListener(ChangeListener l) {
        slider.removeChangeListener(l);
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

    public void setOrientation(Struct orientation) {
        slider.setOrientation(((NumberStruct)orientation).intStruct());
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
    protected CustComponent getComponent() {
        return slider;
    }
}
