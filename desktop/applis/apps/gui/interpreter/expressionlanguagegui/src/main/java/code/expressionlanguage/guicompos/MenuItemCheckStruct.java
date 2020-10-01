package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.CheckBoxMenuItem;
import code.gui.EnabledMenu;

public final class MenuItemCheckStruct extends AbsMenuItemStruct {
    private CheckBoxMenuItem menuItem;
    MenuItemCheckStruct() {
        menuItem = new CheckBoxMenuItem();
    }
    MenuItemCheckStruct(Struct _str) {
        menuItem = new CheckBoxMenuItem(getValue(_str));
    }
    public Struct isSelected() {
        return BooleanStruct.of(menuItem.isSelected());
    }
    public void setSelected(Struct _struct) {
        menuItem.setSelected(BooleanStruct.isTrue(_struct));
    }
    @Override
    EnabledMenu getMenu() {
        return menuItem;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesGui) _contextEl.getStandards()).getGuiAliases().getAliasMenuItemCheck();
    }

    public CheckBoxMenuItem getComponent() {
        return menuItem;
    }
}
