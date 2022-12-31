package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.gui.WithListener;
import code.gui.events.AbsWindowListener;
import code.gui.events.AbsWindowListenerClosing;
import code.util.CustList;

public abstract class WindowStruct extends WithoutParentIdStruct {

    private Struct panel = NullStruct.NULL_VALUE;
    public void addWindowListener(AbsWindowListener _l) {
        getAbstractWindow().addWindowListener(_l);
    }

    public void addWindowListener(AbsWindowListenerClosing _l) {
        getAbstractWindow().addWindowListener(_l);
    }

    public void removeWindowListener(AbsWindowListener _l) {
        getAbstractWindow().removeWindowListener(_l);
    }

    public void removeWindowListener(AbsWindowListenerClosing _l) {
        getAbstractWindow().removeWindowListener(_l);
    }

    public CustList<AbsWindowListener> getWindowListeners() {
        return getAbstractWindow().getWindowListeners();
    }
    protected abstract WithListener getAbstractWindow();

    public abstract void pack();
    public void setLocationRelativeTo(Struct _c){
        WithListener abs_ = getAbstractWindow();
        if (_c instanceof CustComponentStruct) {
            abs_.setLocationRelativeTo(((CustComponentStruct)_c).getComponent());
        } else if (_c instanceof DialogStruct) {
            abs_.setLocationRelativeTo(((DialogStruct)_c).getDialog());
        } else if (_c instanceof FrameStruct) {
            abs_.setLocationRelativeTo(((FrameStruct)_c).getCommonFrame());
        } else {
            abs_.setLocationRelativeToNull();
        }
    }
    public boolean isVisible() {
        return getAbstractWindow().isVisible();
    }

    public void setVisible(boolean _v) {
        getAbstractWindow().setVisible(_v);
    }

    public Struct getContentPane() {
        return panel;
    }

    public void setContentPane(PanelStruct _panel) {
        getAbstractWindow().setContentPane(_panel.getPanel());
        panel = _panel;
    }
    public void dispose() {
        getAbstractWindow().dispose();
    }
    public abstract void setMenuBar(Struct _arg);

    public abstract Struct getMenuBar();
}
