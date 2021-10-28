package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsMenuItem;
import code.gui.EnabledMenu;
import code.gui.initialize.AbsCompoFactory;

public final class MenuItemStruct extends AbsMenuItemStruct {
    private AbsMenuItem menuItem;
    public MenuItemStruct(AbsCompoFactory _compo) {
        menuItem = _compo.newMenuItem();
    }
    public MenuItemStruct(Struct _str,AbsCompoFactory _compo) {
        menuItem = _compo.newMenuItem(getValue(_str));
    }
    @Override
    EnabledMenu getMenu() {
        return menuItem;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesGui) _contextEl.getStandards()).getGuiAliases().getAliasMenuItem();
    }

    public AbsMenuItem getComponent() {
        return menuItem;
    }
}
