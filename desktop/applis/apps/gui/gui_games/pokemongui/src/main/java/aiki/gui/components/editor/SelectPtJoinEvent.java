package aiki.gui.components.editor;

import code.gui.*;
import code.gui.events.*;

public final class SelectPtJoinEvent implements AbsMouseListenerIntRel {
    private final ContentComponentModelUniqLevelLinks grid;
    private final FormLevelGridLink form;
    private final boolean left;
    public SelectPtJoinEvent(ContentComponentModelUniqLevelLinks _g, FormLevelGridLink _f, boolean _l) {
        grid = _g;
        form = _f;
        left = _l;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        grid.selectOrDeselectSide(form,left, _location.getXcoord(), _location.getYcoord());
    }
}
