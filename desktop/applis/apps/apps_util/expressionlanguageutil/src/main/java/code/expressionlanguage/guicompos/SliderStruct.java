package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsCustComponent;
import code.gui.AbsSlider;
import code.gui.events.AbsChangeListener;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;

public final class SliderStruct extends CustComponentStruct {
    private final AbsSlider slider;
    public SliderStruct(String _className, AbsCompoFactory _comp) {
        super(_className);
        slider = _comp.newAbsSlider();
    }
    public SliderStruct(String _className,Struct _o, AbsCompoFactory _comp) {
        super(_className);
        slider = _comp.newAbsSlider(((NumberStruct)_o).intStruct());
    }
    public SliderStruct(String _className,Struct _min,Struct _max, AbsCompoFactory _comp) {
        super(_className);
        slider = _comp.newAbsSlider(((NumberStruct)_min).intStruct(),((NumberStruct)_max).intStruct());
    }
    public SliderStruct(String _className,Struct _min,Struct _max, Struct _value, AbsCompoFactory _comp) {
        super(_className);
        slider = _comp.newAbsSlider(((NumberStruct)_min).intStruct(),((NumberStruct)_max).intStruct(),((NumberStruct)_value).intStruct());
    }
    public SliderStruct(String _className,Struct _o,Struct _min,Struct _max, Struct _value, AbsCompoFactory _comp) {
        super(_className);
        slider = _comp.newAbsSlider(((NumberStruct)_o).intStruct(),((NumberStruct)_min).intStruct(),((NumberStruct)_max).intStruct(),((NumberStruct)_value).intStruct());
    }

    public void addChangeListener(Struct _l, StackCall _stackCall) {
        if (_l instanceof AbsChangeListener) {
            if (_stackCall.getStopper().getLogger() != null) {
                slider.addChangeListenerMap((AbsChangeListener) _l);
            } else {
                slider.addChangeListener((AbsChangeListener) _l);
            }
        }
    }


    public void removeChangeListener(Struct _l, StackCall _stackCall) {
        if (_l instanceof AbsChangeListener) {
            if (_stackCall.getStopper().getLogger() != null) {
                slider.removeChangeListenerMap((AbsChangeListener) _l);
            } else {
                slider.removeChangeListener((AbsChangeListener) _l);
            }
        }
    }

    public ArrayStruct getChange(ContextEl _ctx) {
        String aliasChgListener_ = ((LgNamesGui) _ctx.getStandards()).getGuiAliases().getAliasChangeListener();
        CustList<AbsChangeListener> listSel_ = slider.getChangeListeners();
        CustList<Struct> res_ = new CustList<Struct>();
        int lenBase_ = listSel_.size();
        for (int i = 0; i < lenBase_; i++) {
            if (listSel_.get(i) instanceof Struct) {
                res_.add((Struct)listSel_.get(i));
            }
        }
        return nulls(aliasChgListener_, res_);
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
    protected AbsCustComponent getComponent() {
        return getSlider();
    }

    public AbsSlider getSlider() {
        return slider;
    }
}
