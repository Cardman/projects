package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsCustComponent;
import code.gui.AbsPanel;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;

public class PanelStruct extends CustComponentStruct implements ContainerStruct{
    private final AbsPanel panel;
    private PanelStruct(String _className, AbsCompoFactory _compoFactory) {
        super(_className);
        panel = _compoFactory.newLineBox();
    }
    PanelStruct(String _className,AbsPanel _panel) {
        super(_className);
        panel = _panel;
    }

    public static PanelStruct newFlow(String _className, AbsCompoFactory _compoFactory) {
        return new PanelStruct(_className,_compoFactory);
    }

    public static PanelStruct newAbsolute(String _className, AbsCompoFactory _compoFactory) {
        return new PanelStruct(_className,_compoFactory.newAbsolute());
    }

    public static PanelStruct newGrid(String _className,int _row,int _col, AbsCompoFactory _compoFactory) {
        int r_ = _row;
        int c_ = _col;
        if (r_ == 0 && c_ == 0) {
            c_ = 1;
        }
        return new PanelStruct(_className,_compoFactory.newGrid(r_,c_));
    }

    public static PanelStruct newPageBox(String _className, AbsCompoFactory _compoFactory) {
        return new PanelStruct(_className,_compoFactory.newPageBox());
    }

    public int getComponentCount() {
        return panel.getComponentCount();
    }

    public Struct getComponent(int _n) {
        CustList<CustComponentStruct> ch_ = getChildren();
        if (!ch_.isValidIndex(_n)) {
            return NullStruct.NULL_VALUE;
        }
        return ch_.get(_n);
    }

    public void add(CustComponentStruct _comp) {
        if (kept(_comp)) {
            return;
        }
        _comp.setParentComponent(this);
        getChildren().add(_comp);
        panel.add(_comp.getComponent());
    }

    public void add(CustComponentStruct _comp, int _index) {
        if (kept(_comp)) {
            return;
        }
        _comp.setParentComponent(this);
        getChildren().add(_index,_comp);
        panel.add(_comp.getComponent(), _index);
    }

    public void removeAll() {
        CustList<CustComponentStruct> ch_ = getChildren();
        for (CustComponentStruct c: ch_) {
            c.setNullParentComponent();
        }
        ch_.clear();
        panel.removeAll();
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

    @Override
    public void move(CustComponentStruct _compo) {
        remove(getChildren().indexOfObj(_compo));
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
    public void validate() {
        panel.validate();
    }
    @Override
    protected AbsCustComponent getComponent() {
        return panel;
    }

    public AbsPanel getPanel() {
        return panel;
    }
}
