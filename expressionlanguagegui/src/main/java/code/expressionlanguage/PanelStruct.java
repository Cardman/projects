package code.expressionlanguage;

import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.CustComponent;
import code.gui.Panel;
import code.util.CustList;

public class PanelStruct extends CustComponentStruct {
    private Panel panel;
    private PanelStruct(String _className) {
        super(_className);
        panel = new Panel();
    }
    public PanelStruct(Panel _plainButton, String _className) {
        super(_className);
        panel = _plainButton;
    }
    public static PanelStruct newFlow(String _className) {
        return new PanelStruct(_className);
    }

    public int getComponentCount() {
        return panel.getComponentCount();
    }

    public CustComponentStruct getComponent(int _n) {
        return getChildren().get(_n);
    }

    public void add(CustComponentStruct _comp) {
        if (_comp.getParentComponent() != NullStruct.NULL_VALUE) {
            return;
        }
        _comp.setParentComponent(this);
        getChildren().add(_comp);
        panel.add(_comp.getComponent());
    }

    public void add(CustComponentStruct _comp, int _index) {
        if (_comp.getParentComponent() != NullStruct.NULL_VALUE) {
            return;
        }
        _comp.setParentComponent(this);
        getChildren().add(_index,_comp);
        panel.add(_comp.getComponent(), _index);
    }

    public Struct remove(int _index) {
        CustList<CustComponentStruct> ch_ = getChildren();
        if (!ch_.isValidIndex(_index)) {
            return NullStruct.NULL_VALUE;
        }
        CustComponentStruct c_ = ch_.get(_index);
        c_.setNullParentComponent();
        ch_.remove(_index);
        panel.remove(_index);
        return c_;
    }

    public IntStruct remove(Struct _cust) {
        if (!(_cust instanceof CustComponentStruct)) {
            return new IntStruct(-2);
        }
        CustComponentStruct a_ = (CustComponentStruct) _cust;
        int i_ = 0;
        int index_ = -1;
        CustList<CustComponentStruct> rem_ = new CustList<CustComponentStruct>();
        for (CustComponentStruct c: getChildren()) {
            if (c.getComponent() == a_.getComponent()) {
                c.setNullParentComponent();
                a_.setNullParentComponent();
                index_ = i_;
            } else {
                rem_.add(c);
            }
            i_++;
        }
        getChildren().clear();
        panel.removeAll();
        for (CustComponentStruct c: rem_) {
            getChildren().add(c);
            panel.add(c.getComponent());
        }
        return new IntStruct(index_);
    }
    @Override
    protected CustComponent getComponent() {
        return panel;
    }

    public Panel getPanel() {
        return panel;
    }
}
