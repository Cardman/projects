package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;
import code.gui.EnabledMenu;
import code.gui.MenuItem;

public final class MenuItemStruct extends AbsMenuItemStruct {
    private MenuItem menuItem;
    MenuItemStruct() {
        menuItem = new MenuItem();
    }
    MenuItemStruct(Struct _str) {
        menuItem = new MenuItem(getValue(_str));
    }
    @Override
    EnabledMenu getMenu() {
        return menuItem;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesGui) _contextEl.getStandards()).getAliasMenuItem();
    }

    public MenuItem getComponent() {
        return menuItem;
    }
}
