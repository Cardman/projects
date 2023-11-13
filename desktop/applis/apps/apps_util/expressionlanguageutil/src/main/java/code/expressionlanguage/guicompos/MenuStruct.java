package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsButton;
import code.gui.AbsCustComponent;
import code.gui.EnabledMenu;
import code.gui.MenuItemUtils;
import code.gui.initialize.AbsCompoFactory;

public final class MenuStruct extends AbsButtonStruct {
    private final EnabledMenu menu;
    public MenuStruct(String _className, EnabledMenu _en) {
        super(_className);
        menu = _en;
    }

    public void setDeepEnabled(Struct _str) {
        MenuItemUtils.setEnabledMenu(menu,BooleanStruct.isTrue(_str));
    }

    public Struct isSelected() {
        return BooleanStruct.of(menu.isSelected());
    }
    public void setSelected(Struct _struct) {
        menu.setSelected(BooleanStruct.isTrue(_struct));
    }

    public void addSeparator(String _aliasSep, AbsCompoFactory _ex) {
        add(new SeparatorStruct(_aliasSep,_ex));
    }

    public void add(Struct _c) {
        if (_c instanceof CustComponentStruct) {
            if (((CustComponentStruct) _c).getParentComponent() != NullStruct.NULL_VALUE) {
                return;
            }
            ((CustComponentStruct) _c).setParentComponent(this);
            if (_c instanceof AbsButtonStruct) {
                ((AbsButtonStruct) _c).setParentMenu(this);
            }
            AbsCustComponent b_ = ((CustComponentStruct) _c).getComponent();
            if (b_ instanceof EnabledMenu) {
                ((EnabledMenu)b_).setParentMenu(menu);
            }
            menu.addMenuItem(b_);
            getMenus().add(_c);
        }
    }

    public void remove(Struct _c) {
        if (_c instanceof CustComponentStruct) {
            int i_ = 0;
            int index_ = -1;
            for (Struct a: getMenus()) {
                if (a.sameReference(_c)) {
                    index_ = i_;
                    break;
                }
                i_++;
            }
            if (index_ < 0) {
                return;
            }
            if (_c instanceof AbsButtonStruct) {
                ((AbsButtonStruct) _c).setParentMenu(NullStruct.NULL_VALUE);
            }
            ((CustComponentStruct)_c).setNullParentComponent();
            AbsCustComponent b_ = ((CustComponentStruct) _c).getComponent();
            if (b_ instanceof EnabledMenu) {
                ((EnabledMenu)b_).setParentMenu(null);
            }
            menu.removeMenuItem(b_);
            getMenus().remove(index_);
        }
    }

    @Override
    public AbsButton but() {
        return menu;
    }
}
