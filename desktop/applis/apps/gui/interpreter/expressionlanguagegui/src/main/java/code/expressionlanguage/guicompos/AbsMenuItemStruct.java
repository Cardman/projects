package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.Struct;
import code.gui.events.AbsActionListener;
import code.gui.CheckBoxMenuItem;
import code.gui.EnabledMenu;
import code.gui.MenuItem;

public abstract class AbsMenuItemStruct extends AbsMenuStruct {
    public void addActionListener(Struct _struct) {
        if (_struct instanceof AbsActionListener) {
            EnabledMenu menu_ = getMenu();
            if (menu_ instanceof MenuItem) {
                ((MenuItem)menu_).addActionListener((AbsActionListener)_struct);
            } else if (menu_ instanceof CheckBoxMenuItem) {
                ((CheckBoxMenuItem)menu_).addActionListener((AbsActionListener)_struct);
            }
        }
    }
}
