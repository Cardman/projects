package code.expressionlanguage.guicompos;

import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.*;
import code.gui.EnabledMenu;
import code.gui.MenuItemUtils;

public abstract class AbsMenuStruct extends WithoutParentIdStruct implements Struct {
    private Struct parentMenu = NullStruct.NULL_VALUE;

    public Struct getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(Struct _parentMenu) {
        parentMenu = _parentMenu;
    }

    public Struct isEnabled() {
        return BooleanStruct.of(getMenu().isEnabled());
    }

    public void setEnabled(Struct _str) {
        getMenu().setEnabled(BooleanStruct.isTrue(_str));
    }

    public void setDeepEnabled(Struct _str) {
        MenuItemUtils.setEnabledMenu(getMenu(),BooleanStruct.isTrue(_str));
    }
    public Struct getText() {
        String txt_ = getMenu().getText();
        return new StringStruct(txt_);
    }
    public void setText(Struct _str) {
        getMenu().setText(getValue(_str));
    }

    static String getValue(Struct _str) {
        return NumParsers.getString(_str).getInstance();
    }
    abstract EnabledMenu getMenu();

}
