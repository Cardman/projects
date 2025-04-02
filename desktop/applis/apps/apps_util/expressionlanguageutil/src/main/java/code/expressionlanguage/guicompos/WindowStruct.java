package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.gui.*;
import code.gui.events.AbsWindowListener;
import code.gui.events.AbsWindowListenerClosing;
import code.util.CustList;

public abstract class WindowStruct extends WithoutParentIdStruct {

    private Struct panel = NullStruct.NULL_VALUE;
    private Struct menuBar = NullStruct.NULL_VALUE;
    private final WithListener container;

    protected WindowStruct(WithListener _c) {
        this.container = _c;
    }

    public Struct getMenuBar() {
        return menuBar;
    }

    public void setMenuBar(Struct _arg) {
        if (_arg instanceof MenuBarStruct) {
            menuBar = _arg;
            getAbstractWindow().setJMenuBar(((MenuBarStruct)_arg).getMenuBar());
        }
    }
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
    public WithListener getAbstractWindow(){
        return container;
    }

    public void pack() {
        if (getAbstractWindow() instanceof Packable) {
            ((Packable) getAbstractWindow()).pack();
        } else {
            recalculate();
        }
    }
    public void setLocationRelativeTo(Struct _c){
        WithListener abs_ = getAbstractWindow();
        if (_c instanceof CustComponentStruct) {
            abs_.setLocationRelativeTo(((CustComponentStruct)_c).getComponent());
        } else {
            if (_c instanceof WindowStruct) {
                WithListener w_ = ((WindowStruct) _c).getAbstractWindow();
                if (w_ instanceof AbsOtherDialog) {
                    abs_.setLocationRelativeTo((AbsOtherDialog) w_);
                }
                if (w_ instanceof AbsOtherFrame) {
                    abs_.setLocationRelativeTo((AbsOtherFrame) w_);
                }
            } else {
                abs_.setLocationRelativeToNull();
            }
        }
    }
    public void recalculate() {
        if (panel instanceof CustComponentStruct) {
            recalculate((CustComponentStruct) panel);
        }
    }
    public static void recalculate(CustComponentStruct _compo) {
        _compo.setSize(_compo.getPreferredSize());
        CustComponentStruct curr_ = _compo;
        while(curr_ != null) {
            curr_.recalculate();
            if (curr_ instanceof ScrollPaneStruct) {
                ((ScrollPaneStruct)curr_).getScrollPane().recalculateViewport();
            }

            CustComponentStruct child_ = childAt(curr_, 0);
            if (child_ != null) {
                curr_ = child_;
            } else {
                while(curr_ != null) {
                    CustComponentStruct par_ = parentOrNull(curr_);
                    int index_ = indexOf(par_, curr_);
                    CustComponentStruct next_ = childAt(par_, index_ + 1);
                    if (next_ != null) {
                        curr_ = next_;
                        break;
                    }

                    curr_ = parent(par_,_compo);
                }
            }
        }
    }

    private static CustComponentStruct parentOrNull(CustComponentStruct _par) {
        Struct p_ = _par.getParentComponent();
        if (p_ instanceof CustComponentStruct) {
            return (CustComponentStruct) p_;
        }
        return null;
    }

    private static CustComponentStruct parent(CustComponentStruct _par, Struct _compo) {
        if (_par == _compo) {
            return null;
        }
        return _par;
    }

    private static CustComponentStruct childAt(CustComponentStruct _elt, int _index) {
        if (_elt == null) {
            return null;
        }
        CustList<CustComponentStruct> children_ = _elt.getChildren();
        if (!children_.isValidIndex(_index)) {
            return null;
        }
        return children_.get(_index);
    }

    private static int indexOf(CustComponentStruct _par, CustComponentStruct _elt) {
        if (_par == null) {
            return indexOf(new CustList<CustComponentStruct>(), _elt);
        }
        return indexOf(_par.getChildren(), _elt);
    }

    public static int indexOf(CustList<CustComponentStruct> _list, CustComponentStruct _elt) {
        int len_ = _list.size();

        for(int i = 0; i < len_; i++) {
            CustComponentStruct c = _list.get(i);
            if (c == _elt) {
                return i;
            }
        }

        return -1;
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
}
