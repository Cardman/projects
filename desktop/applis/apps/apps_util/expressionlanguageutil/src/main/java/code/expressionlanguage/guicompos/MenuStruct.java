package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsMenu;
import code.gui.EnabledMenu;

import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;

public final class MenuStruct extends AbsMenuStruct {
    private final AbsMenu menu;
    private final CustList<AbsMenuStruct> menus = new CustList<AbsMenuStruct>();
    public MenuStruct(AbsCompoFactory _compo) {
        menu = _compo.newMenu();
    }
    public MenuStruct(Struct _str,AbsCompoFactory _compo) {
        menu = _compo.newMenu(getValue(_str));
    }

    public void add(Struct _c) {
        if (_c instanceof AbsMenuStruct) {
            if (((AbsMenuStruct) _c).getParentMenu() != NullStruct.NULL_VALUE) {
                return;
            }
            ((AbsMenuStruct) _c).setParentMenu(this);
            if (_c instanceof MenuStruct) {
                menu.addMenuItem(((MenuStruct)_c).getComponent());
                menus.add((AbsMenuStruct) _c);
            } else if (_c instanceof MenuItemStruct) {
                menu.addMenuItem(((MenuItemStruct)_c).getComponent());
                menus.add((AbsMenuStruct) _c);
            } else if (_c instanceof MenuItemCheckStruct) {
                menu.addMenuItem(((MenuItemCheckStruct)_c).getComponent());
                menus.add((AbsMenuStruct) _c);
            }
        }
    }

    public void remove(Struct _c) {
        if (_c instanceof AbsMenuStruct) {
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
            ((AbsMenuStruct) _c).setParentMenu(NullStruct.NULL_VALUE);
            if (_c instanceof MenuStruct) {
                menu.removeMenuItem(((MenuStruct)_c).getComponent());
                menus.remove(index_);
            } else if (_c instanceof MenuItemStruct) {
                menu.removeMenuItem(((MenuItemStruct)_c).getComponent());
                menus.remove(index_);
            } else if (_c instanceof MenuItemCheckStruct) {
                menu.removeMenuItem(((MenuItemCheckStruct)_c).getComponent());
                menus.remove(index_);
            }
        }
    }

    public Struct getMenu(Struct _index) {
        if (!menus.isValidIndex(((NumberStruct)_index).intStruct())) {
            return NullStruct.NULL_VALUE;
        }
        return menus.get(((NumberStruct)_index).intStruct());
    }

    public IntStruct getMenuCount() {
        return new IntStruct(menu.getItemCount());
    }
    @Override
    EnabledMenu getMenu() {
        return getComponent();
    }

    AbsMenu getComponent() {
        return menu;
    }
    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesGui) _contextEl.getStandards()).getGuiAliases().getAliasMenu();
    }

    public void addSeparator() {
        menu.addSeparator();
    }
}
