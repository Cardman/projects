package code.expressionlanguage.guicompos;

import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.MenuBar;
import code.util.CustList;

public final class MenuBarStruct implements Struct {
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
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return ((LgNamesGui) _contextEl.getContextEl().getStandards()).getAliasMenuBar();
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }
}
