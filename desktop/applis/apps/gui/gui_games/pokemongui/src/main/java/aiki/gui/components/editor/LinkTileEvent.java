package aiki.gui.components.editor;

import code.gui.*;
import code.gui.events.*;

public final class LinkTileEvent implements AbsMouseListenerIntRel {
    private final AbsContentComponentModelLevelLinks wild;
    private final FormLevelGridLink link;
    private final boolean left;
    public LinkTileEvent(AbsContentComponentModelLevelLinks _g, FormLevelGridLink _lk, boolean _l) {
        wild = _g;
        this.link = _lk;
        this.left = _l;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        wild.viewForeground(link, left, _location.getXcoord(),_location.getYcoord());
    }
}
