package aiki.gui.components.editor;

import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerIntRel;

public final class SelectOrDeselectNextPtEvent implements AbsMouseListenerIntRel {
    private final ContentComponentModelLevelLeagueLinks contentComponentModelDualFight;
    public SelectOrDeselectNextPtEvent(ContentComponentModelLevelLeagueLinks _c) {
        contentComponentModelDualFight = _c;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        contentComponentModelDualFight.selectOrDeselect(_location.getXcoord(), _location.getYcoord());
    }
}
