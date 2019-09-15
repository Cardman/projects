package code.expressionlanguage;

import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.CheckBoxMenuItem;
import code.gui.EnabledMenu;
import code.gui.Menu;
import code.gui.MenuItem;

public final class MenuItemCheckStruct extends AbsMenuItemStruct {
    private CheckBoxMenuItem menuItem;
    MenuItemCheckStruct() {
        menuItem = new CheckBoxMenuItem();
    }
    MenuItemCheckStruct(Struct _str) {
        menuItem = new CheckBoxMenuItem(getValue(_str));
    }
    public Struct isSelected() {
        return new BooleanStruct(menuItem.isSelected());
    }
    public void setSelected(Struct _struct) {
        menuItem.setSelected(((BooleanStruct)_struct).getInstance());
    }
    @Override
    EnabledMenu getMenu() {
        return menuItem;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return ((LgNamesGui) _contextEl.getContextEl().getStandards()).getAliasMenuItemCheck();
    }

    public CheckBoxMenuItem getComponent() {
        return menuItem;
    }
}
