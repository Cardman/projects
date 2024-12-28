package aiki.gui.components.editor;

import code.gui.*;
import code.gui.events.*;

public final class SelectOrDeselectAccessLeagueEvent implements AbsMouseListenerIntRel {
    private final ContentComponentModelLeagueLinks contentComponentModelDualFight;
    private final FormLevelGridLink grid;
    public SelectOrDeselectAccessLeagueEvent(ContentComponentModelLeagueLinks _c, FormLevelGridLink _g) {
        contentComponentModelDualFight = _c;
        grid = _g;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        contentComponentModelDualFight.selectOrDeselectAccessLeague(grid,_location.getXcoord(), _location.getYcoord());
    }
}
