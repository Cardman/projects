package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.Struct;
import code.gui.AbsMenuItem;
import code.gui.events.AbsAdvActionListener;

public abstract class AbsMenuItemStruct extends AbsMenuStruct {
    public void addActionListener(Struct _struct) {
        if (_struct instanceof AbsAdvActionListener) {
            element().addActionListener((AbsAdvActionListener)_struct);
        }
    }
    protected abstract AbsMenuItem element();
}
