package aiki.gui.components.editor;

import code.gui.*;
import code.gui.events.*;

public final class SelectOrDeselectBeginLeagueEvent implements AbsMouseListenerIntRel {
    private final ContentComponentModelLeagueLinks contentComponentModelDualFight;
    public SelectOrDeselectBeginLeagueEvent(ContentComponentModelLeagueLinks _c) {
        contentComponentModelDualFight = _c;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        contentComponentModelDualFight.selectOrDeselectBeginLeague(_location.getXcoord(), _location.getYcoord());
    }
}
