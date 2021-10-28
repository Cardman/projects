package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.gui.AbsPanel;
import code.gui.WithListener;
import code.gui.events.AbsWindowListener;
import code.util.CustList;

public abstract class WindowStruct extends WithoutParentIdStruct {

    public void addWindowListener(AbsWindowListener _l) {
        getAbstractWindow().addWindowListener(_l);
    }

    public void removeWindowListener(AbsWindowListener _l) {
        getAbstractWindow().removeWindowListener(_l);
    }

    public CustList<AbsWindowListener> getWindowListeners() {
        return getAbstractWindow().getWindowListeners();
    }
    protected abstract WithListener getAbstractWindow();

    public abstract void pack();
    public void setLocationRelativeTo(Struct _c){
        if (_c instanceof CustComponentStruct) {
            getAbstractWindow().setLocationRelativeTo(((CustComponentStruct)_c).getComponent());
        } else if (_c instanceof DialogStruct) {
            getAbstractWindow().setLocationRelativeTo(((DialogStruct)_c).getDialog());
        } else if (_c instanceof FrameStruct) {
            getAbstractWindow().setLocationRelativeTo(((FrameStruct)_c).getCommonFrame());
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

    public void setContentPane(AbsPanel _panel) {
        getAbstractWindow().setContentPane(_panel);
    }
    public void dispose() {
        getAbstractWindow().dispose();
    }
    public abstract void setMenuBar(Struct _arg);

    public abstract Struct getMenuBar();
}
