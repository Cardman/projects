package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.Struct;
import code.gui.CheckBoxMenuItem;
import code.gui.EnabledMenu;
import code.gui.MenuItem;

import java.awt.event.ActionListener;

public abstract class AbsMenuItemStruct extends AbsMenuStruct {
    public void addActionListener(Struct _struct) {
        if (_struct instanceof ActionListener) {
            EnabledMenu menu_ = getMenu();
            if (menu_ instanceof MenuItem) {
                ((MenuItem)menu_).addActionListener((ActionListener) _struct);
            } else if (menu_ instanceof CheckBoxMenuItem) {
                ((CheckBoxMenuItem)menu_).addActionListener((ActionListener) _struct);
            }
        }
    }
}
