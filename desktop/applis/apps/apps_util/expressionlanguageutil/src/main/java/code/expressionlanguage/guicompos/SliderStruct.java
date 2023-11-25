package code.expressionlanguage.guicompos;

import code.expressionlanguage.AfterChangingSliderSelectState;
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
import code.util.core.NumberUtil;

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

    public void setValue(Struct _value, StackCall _stackCall, String _intro) {
        if (_stackCall.getStopper().getLogger() != null) {
            int v_ = ((NumberStruct)_value).intStruct();
            if (v_ == slider.getValue()) {
                return;
            }
            int newValue_ = NumberUtil.min(NumberUtil.max(v_, slider.getMinimum()), slider.getMaximum());
            boolean ch_ = change(newValue_, slider.getMinimum(), slider.getMaximum());
            slider.setValue(v_);
            notif(_stackCall,this,ch_, _intro);
            return;
        }
        slider.setValue(((NumberStruct)_value).intStruct());
    }

    public Struct getMin() {
        return new IntStruct(slider.getMinimum());
    }

    public void setMin(Struct _min, StackCall _stackCall, String _intro) {
        if (_stackCall.getStopper().getLogger() != null) {
            int m_ = ((NumberStruct) _min).intStruct();
            int newMax_ = NumberUtil.max(m_, slider.getMaximum());
            int newValue_ = NumberUtil.max(m_, slider.getValue());
            int newMin_  = NumberUtil.min(m_,newMax_);
            newMax_ = NumberUtil.max(newMax_, slider.getValue());
            newMin_ = NumberUtil.min(newMin_,slider.getValue());
            boolean ch_ = change(newValue_, newMin_, newMax_);
            slider.setMinimum(m_);
            notif(_stackCall,this,ch_, _intro);
            return;
        }
        slider.setMinimum(((NumberStruct)_min).intStruct());
    }

    public Struct getMax() {
        return new IntStruct(slider.getMaximum());
    }

    public void setMax(Struct _max, StackCall _stackCall, String _intro) {
        if (_stackCall.getStopper().getLogger() != null) {
            int m_ = ((NumberStruct) _max).intStruct();
            int newMin_  = NumberUtil.min(m_,slider.getMinimum());
            int newValue_ = NumberUtil.min(m_, slider.getValue());
            int newMax_ = NumberUtil.max(m_, slider.getMaximum());
            newMin_ = NumberUtil.min(newMin_,slider.getValue());
            boolean ch_ = change(newValue_, newMin_, newMax_);
            slider.setMaximum(m_);
            notif(_stackCall,this,ch_, _intro);
            return;
        }
        slider.setMaximum(((NumberStruct)_max).intStruct());
    }

    public void properties(Struct _min,Struct _max,Struct _value, StackCall _stackCall, String _intro) {
        int min_ = ((NumberStruct) _min).intStruct();
        int max_ = ((NumberStruct) _max).intStruct();
        int value_ = ((NumberStruct) _value).intStruct();
        if (_stackCall.getStopper().getLogger() != null) {
            int newMin_ = NumberUtil.min(min_,max_);
            int newMax_ = NumberUtil.max(value_,max_);
            newMin_ = NumberUtil.min(newMin_,value_);
            boolean ch_ = change(value_, newMin_, newMax_);
            slider.properties(min_,max_,value_);
            notif(_stackCall,this,ch_, _intro);
            return;
        }
        slider.properties(min_,max_,value_);
    }
    private boolean change(int _newValue, int _newMin, int _newMax) {
        return _newValue != slider.getValue() ||
                _newMin != slider.getMinimum() ||
                _newMax != slider.getMaximum();
    }


    static void notif(StackCall _stackCall, SliderStruct _inst, boolean _chg, String _intro) {
        if (_chg) {
            _stackCall.getStopper().getLogger().log(_intro);
            _stackCall.setCallingState(new AfterChangingSliderSelectState(_inst));
        }
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
