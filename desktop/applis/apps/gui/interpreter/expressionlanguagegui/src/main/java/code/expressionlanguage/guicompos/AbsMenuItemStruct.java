package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.Struct;
import code.gui.AbsCheckBoxMenuItem;
import code.gui.AbsMenuItem;
import code.gui.EnabledMenu;
import code.gui.events.AbsAdvActionListener;

public abstract class AbsMenuItemStruct extends AbsMenuStruct {
    public void addActionListener(Struct _struct) {
        if (_struct instanceof AbsAdvActionListener) {
            EnabledMenu menu_ = getMenu();
            if (menu_ instanceof AbsMenuItem) {
                ((AbsMenuItem)menu_).addActionListener((AbsAdvActionListener)_struct);
            } else if (menu_ instanceof AbsCheckBoxMenuItem) {
                ((AbsCheckBoxMenuItem)menu_).addActionListener((AbsAdvActionListener)_struct);
            }
        }
    }
}
