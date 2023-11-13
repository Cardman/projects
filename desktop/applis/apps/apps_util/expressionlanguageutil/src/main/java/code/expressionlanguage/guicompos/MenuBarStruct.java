package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsButton;
import code.gui.AbsCustComponent;
import code.gui.EnabledMenu;
import code.gui.AbsMenuBar;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;

public final class MenuBarStruct extends CustComponentStruct implements Struct {
    private final AbsMenuBar menuBar;
    private final CustList<AbsButtonStruct> menus = new CustList<AbsButtonStruct>();

    public MenuBarStruct(AbsCompoFactory _compo, String _cl) {
        super(_cl);
        menuBar = _compo.newMenuBar();
    }
    AbsMenuBar getMenuBar() {
        return menuBar;
    }

    public void add(Struct _c) {
        AbsButton button_;
        if (_c instanceof AbsButtonStruct) {
            for (AbsButtonStruct a: menus) {
                if (a.sameReference(_c)) {
                    return;
                }
            }
            button_ = ((AbsButtonStruct) _c).but();
        } else {
            button_ = null;
        }
        if (button_ instanceof EnabledMenu) {
            menuBar.add((EnabledMenu) button_);
            menus.add((AbsButtonStruct) _c);
        }
    }

    public void remove(Struct _c) {
        int i_ = 0;
        int index_ = -1;
        for (AbsButtonStruct a: menus) {
            if (a.sameReference(_c)) {
                index_ = i_;
                break;
            }
            i_++;
        }
        AbsButton button_;
        if (_c instanceof AbsButtonStruct) {
            button_ = ((AbsButtonStruct) _c).but();
        } else {
            button_ = null;
        }
        if (button_ instanceof EnabledMenu) {
            if (index_ < 0) {
                return;
            }
            menuBar.remove((EnabledMenu) button_);
            menus.remove(index_);
        }
    }

    public Struct getMenu(Struct _index) {
        if (!menus.isValidIndex(((NumberStruct)_index).intStruct())) {
            return NullStruct.NULL_VALUE;
        }
        return menus.get(((NumberStruct)_index).intStruct());
    }

    public IntStruct getMenuCount() {
        return new IntStruct(menuBar.getMenuCount());
    }

    @Override
    protected AbsCustComponent getComponent() {
        return menuBar;
    }

}
