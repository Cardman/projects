package aiki.gui.components.editor;

import code.gui.*;
import code.gui.events.*;

public final class FormBlockTileEvent implements AbsMouseListenerIntRel {
    private final FormLevelGrid form;

    public FormBlockTileEvent(FormLevelGrid _f) {
        form = _f;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        form.view(_location.getXcoord(),_location.getYcoord());
    }
}
