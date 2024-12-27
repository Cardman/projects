package aiki.gui.components.editor;

import code.gui.*;
import code.gui.events.*;

public final class SelectOrDeselectPtEvent implements AbsMouseListenerIntRel {
    private final ContentComponentModelDualFight contentComponentModelDualFight;
    public SelectOrDeselectPtEvent(ContentComponentModelDualFight _c) {
        contentComponentModelDualFight = _c;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        contentComponentModelDualFight.selectOrDeselect(_location.getXcoord(), _location.getYcoord());
    }
}
