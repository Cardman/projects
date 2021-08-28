package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsCheckBoxMenuItem;
import code.gui.EnabledMenu;
import code.gui.initialize.AbsCompoFactory;

public final class MenuItemCheckStruct extends AbsMenuItemStruct {
    private AbsCheckBoxMenuItem menuItem;
    MenuItemCheckStruct(AbsCompoFactory _compo) {
        menuItem = _compo.newCheckBoxMenuItem();
    }
    MenuItemCheckStruct(Struct _str,AbsCompoFactory _compo) {
        menuItem = _compo.newCheckBoxMenuItem(getValue(_str));
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

    public AbsCheckBoxMenuItem getComponent() {
        return menuItem;
    }
}
