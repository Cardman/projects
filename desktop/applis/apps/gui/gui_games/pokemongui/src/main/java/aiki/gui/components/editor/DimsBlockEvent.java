package aiki.gui.components.editor;

import code.gui.*;
import code.gui.events.*;

public class DimsBlockEvent implements AbsAdvActionListener {
    private final FormLevelGrid levelGrid;
    public DimsBlockEvent(FormLevelGrid _g) {
        levelGrid = _g;
    }

    @Override
    public void action(AbsCtrlKeyState _state, String _command) {
        levelGrid.checkDims();
    }
}
