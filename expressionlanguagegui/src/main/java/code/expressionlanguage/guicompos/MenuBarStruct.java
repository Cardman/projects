package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.*;
import code.gui.MenuBar;
import code.util.CustList;

public final class MenuBarStruct extends WithoutParentIdStruct implements Struct {
    private MenuBar menuBar = new MenuBar();
    private CustList<MenuStruct> menus = new CustList<MenuStruct>();

    MenuBar getMenuBar() {
        return menuBar;
    }

    public void add(Struct _c) {
        if (_c instanceof MenuStruct) {
            for (AbsMenuStruct a: menus) {
                if (a.sameReference(_c)) {
                    return;
                }
            }
            menuBar.add(((MenuStruct)_c).getComponent());
            menus.add((MenuStruct) _c);
        }
    }

    public void remove(Struct _c) {
        if (_c instanceof MenuStruct) {
            int i_ = 0;
            int index_ = -1;
            for (AbsMenuStruct a: menus) {
                if (a.sameReference(_c)) {
                    index_ = i_;
                    break;
                }
                i_++;
            }
            if (index_ < 0) {
                return;
            }
            menuBar.remove(((MenuStruct)_c).getComponent());
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
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesGui) _contextEl.getStandards()).getAliasMenuBar();
    }

}
