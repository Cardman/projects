package code.expressionlanguage;

import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.CustComponent;
import code.util.CustList;

import javax.swing.SwingUtilities;

public abstract class CustComponentStruct implements Struct {

    private Struct parentComponent = NullStruct.NULL_VALUE;
    private CustList<CustComponentStruct> children = new CustList<CustComponentStruct>();
    private String className;
    private Struct paintEvent = NullStruct.NULL_VALUE;

    protected CustComponentStruct(String _className) {
        className = _className;
    }
    public static void invokeLater(Struct _r) {
        if (_r instanceof EventStruct) {
            SwingUtilities.invokeLater((EventStruct) _r);
        }
    }
    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return className;
    }
    protected Struct getParentComponent() {
        return parentComponent;
    }
    protected void setParentComponent(CustComponentStruct _parent) {
        parentComponent = _parent;
    }
    protected void setNullParentComponent() {
        parentComponent = NullStruct.NULL_VALUE;
    }

    public void setToolTipText(String _title) {
        getComponent().setToolTipText(_title);
    }

    public FontStruct getFont() {
        return new FontStruct(getComponent().getFont());
    }
    public int getWidth() {
        return getComponent().getWidth();
    }
    public int getHeight() {
        return getComponent().getHeight();
    }
    protected abstract CustComponent getComponent();

    public CustList<CustComponentStruct> getChildren() {
        return children;
    }

    public Struct getPaintEvent() {
        return paintEvent;
    }
    public Struct getNext() {
        Struct parent_ = getParentComponent();
        if (!(parent_ instanceof PanelStruct)) {
            return NullStruct.NULL_VALUE;
        }
        CustList<CustComponentStruct> children_ = ((PanelStruct)parent_).getChildren();
        int count_ = children_.size();
        int i_ = 0;
        while (true) {
            if (children_.get(i_) == this) {
                if (i_ + 1 >= count_) {
                    return NullStruct.NULL_VALUE;
                }
                return children_.get(i_ + 1);
            }
            i_++;
        }
    }
    public void setPaintEvent(Struct paintEvent) {
        this.paintEvent = paintEvent;
    }

    public BooleanStruct isAutoscrolls(){
        return new BooleanStruct(getComponent().isAutoscrolls());
    }
    public void setAutoscrolls(Struct _autoscrolls) {
        getComponent().setAutoscrolls(((BooleanStruct)_autoscrolls).getInstance());
    }
    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof CustComponentStruct)) {
            return false;
        }
        return getComponent() == ((CustComponentStruct)_other).getComponent();
    }
}
