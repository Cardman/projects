package code.expressionlanguage.guicompos;

import code.expressionlanguage.ExecutableCode;
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
    public String getClassName(ExecutableCode _contextEl) {
        return ((LgNamesGui) _contextEl.getContextEl().getStandards()).getAliasMenuItem();
    }

    public MenuItem getComponent() {
        return menuItem;
    }
}
