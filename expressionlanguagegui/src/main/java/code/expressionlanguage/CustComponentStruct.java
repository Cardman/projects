package code.expressionlanguage;

import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.CustComponent;
import code.util.CustList;

import javax.swing.*;

public abstract class CustComponentStruct implements Struct {

    private Struct parentComponent = NullStruct.NULL_VALUE;
    private CustList<CustComponentStruct> children = new CustList<CustComponentStruct>();
    private String className;

    protected CustComponentStruct(String _className) {
        className = _className;
    }
    public static void invokeLater(RunnableStruct _r) {
        SwingUtilities.invokeLater(_r);
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

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof CustComponentStruct)) {
            return false;
        }
        return getComponent() == ((CustComponentStruct)_other).getComponent();
    }
}
