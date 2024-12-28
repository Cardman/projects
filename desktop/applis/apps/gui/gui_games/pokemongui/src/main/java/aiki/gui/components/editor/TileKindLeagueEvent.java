package aiki.gui.components.editor;

import code.gui.*;
import code.gui.events.*;

public final class TileKindLeagueEvent implements AbsMouseListenerIntRel {
    private final ContentComponentModelLevelLeague wild;
    public TileKindLeagueEvent(ContentComponentModelLevelLeague _g) {
        wild = _g;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        wild.viewForeground(_location.getXcoord(),_location.getYcoord());
    }
}
