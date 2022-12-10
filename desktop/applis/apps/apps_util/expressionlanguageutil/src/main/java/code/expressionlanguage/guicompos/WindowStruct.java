package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.gui.AbsPanel;
import code.gui.PlacableWindow;
import code.gui.WithListener;
import code.gui.events.AbsWindowListener;
import code.gui.events.AbsWindowListenerClosing;
import code.util.CustList;

public abstract class WindowStruct extends WithoutParentIdStruct {

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
        if (!(abs_ instanceof PlacableWindow)) {
            return;
        }
        PlacableWindow pl_ = (PlacableWindow) abs_;
        if (_c instanceof CustComponentStruct) {
            pl_.setLocationRelativeTo(((CustComponentStruct)_c).getComponent());
        } else if (_c instanceof DialogStruct) {
            pl_.setLocationRelativeTo(((DialogStruct)_c).getDialog());
        } else if (_c instanceof FrameStruct) {
            pl_.setLocationRelativeTo(((FrameStruct)_c).getCommonFrame());
        } else {
            pl_.setLocationRelativeToNull();
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
