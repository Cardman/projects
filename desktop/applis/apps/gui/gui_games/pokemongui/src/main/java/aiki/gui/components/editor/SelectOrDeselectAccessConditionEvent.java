package aiki.gui.components.editor;

import code.gui.*;
import code.gui.events.*;

public final class SelectOrDeselectAccessConditionEvent implements AbsMouseListenerIntRel {
    private final ContentComponentModelAccessCondition contentComponentModelDualFight;
    private final FormLevelGridLink grid;
    public SelectOrDeselectAccessConditionEvent(ContentComponentModelAccessCondition _c, FormLevelGridLink _g) {
        contentComponentModelDualFight = _c;
        grid = _g;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        contentComponentModelDualFight.selectOrDeselectAccess(grid,_location.getXcoord(), _location.getYcoord());
    }
}
