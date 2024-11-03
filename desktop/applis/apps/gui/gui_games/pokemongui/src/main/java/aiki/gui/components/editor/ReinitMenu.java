package aiki.gui.components.editor;

import code.gui.EnabledMenu;
import code.gui.events.AbsWindowListenerClosing;
import code.util.IdList;

public final class ReinitMenu implements AbsWindowListenerClosing {
    private final EnabledMenu menu;
    private final IdList<SubscribedTranslation> opened;
    public ReinitMenu(EnabledMenu _m, IdList<SubscribedTranslation> _subs) {
        menu = _m;
        opened = _subs;
    }

    @Override
    public void windowClosing() {
        menu.setEnabled(true);
        opened.clear();
    }
}
