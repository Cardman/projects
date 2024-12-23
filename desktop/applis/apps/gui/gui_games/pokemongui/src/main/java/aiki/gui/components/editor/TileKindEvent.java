package aiki.gui.components.editor;

import code.gui.*;
import code.gui.events.*;

public final class TileKindEvent implements AbsMouseListenerIntRel {
    private final ContentComponentModelLevelWithWild wild;
    public TileKindEvent(ContentComponentModelLevelWithWild _g) {
        wild = _g;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        wild.viewForeground(_location.getXcoord(),_location.getYcoord());
    }
}
