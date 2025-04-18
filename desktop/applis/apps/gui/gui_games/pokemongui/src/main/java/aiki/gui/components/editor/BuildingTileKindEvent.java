package aiki.gui.components.editor;


import code.gui.*;
import code.gui.events.*;

public final class BuildingTileKindEvent implements AbsMouseListenerIntRel {
    private final ContentComponentModelCity wild;
    public BuildingTileKindEvent(ContentComponentModelCity _g) {
        wild = _g;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        wild.viewForegroundBuilding(_location.getXcoord(),_location.getYcoord());
    }
}
