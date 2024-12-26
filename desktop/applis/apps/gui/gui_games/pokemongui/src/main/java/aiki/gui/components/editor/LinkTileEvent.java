package aiki.gui.components.editor;

import code.gui.*;
import code.gui.events.*;

public final class LinkTileEvent implements AbsMouseListenerIntRel {
    private final ContentComponentModelLevelCaveLinks wild;
    private final int index;
    public LinkTileEvent(ContentComponentModelLevelCaveLinks _g, int _i) {
        wild = _g;
        this.index = _i;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        wild.viewForeground(index,_location.getXcoord(),_location.getYcoord());
    }
}
