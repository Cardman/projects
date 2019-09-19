package code.expressionlanguage;

import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.CustComponent;
import code.gui.Panel;
import code.gui.WithListener;

import java.awt.event.WindowListener;

public abstract class WindowStruct implements Struct {
    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

    public void addWindowListener(WindowListener _l) {
        getAbstractWindow().addWindowListener(_l);
    }

    public void removeWindowListener(WindowListener _l) {
        getAbstractWindow().removeWindowListener(_l);
    }

    public WindowListener[] getWindowListeners() {
        return getAbstractWindow().getWindowListeners();
    }
    protected abstract WithListener getAbstractWindow();

    public abstract void pack();
    void setLocationRelativeTo(Struct _c){
        if (_c instanceof CustComponentStruct) {
            getAbstractWindow().setLocationRelativeTo(((CustComponentStruct)_c).getComponent());
        } else if (_c instanceof WindowStruct) {
            getAbstractWindow().setLocationRelativeTo(((WindowStruct)_c).getAbstractWindow());
        } else {
            getAbstractWindow().setLocationRelativeToNull();
        }
    }
    public boolean isVisible() {
        return getAbstractWindow().isVisible();
    }

    public void setVisible(boolean _v) {
        getAbstractWindow().setVisible(_v);
    }

    public void setContentPane(Panel _panel) {
        getAbstractWindow().setContentPane(_panel);
    }

    public abstract void setMenuBar(Struct _arg);

    public abstract Struct getMenuBar();
}
