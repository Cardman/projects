package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.Struct;
import code.gui.*;
import code.gui.events.AbsActionListener;

public abstract class AbsMenuItemStruct extends AbsMenuStruct {
    public void addActionListener(Struct _struct) {
        if (_struct instanceof AbsActionListener) {
            EnabledMenu menu_ = getMenu();
            if (menu_ instanceof AbsMenuItem) {
                ((AbsMenuItem)menu_).addActionListener((AbsActionListener)_struct);
            } else if (menu_ instanceof AbsCheckBoxMenuItem) {
                ((AbsCheckBoxMenuItem)menu_).addActionListener((AbsActionListener)_struct);
            }
        }
    }
}
