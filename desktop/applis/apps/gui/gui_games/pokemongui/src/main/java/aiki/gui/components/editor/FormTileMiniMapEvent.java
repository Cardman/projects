package aiki.gui.components.editor;

import code.gui.*;
import code.gui.events.*;

public class FormTileMiniMapEvent implements AbsMouseListenerIntRel {
    private final FormMiniMapGrid form;

    public FormTileMiniMapEvent(FormMiniMapGrid _f) {
        form = _f;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        form.view(_location.getXcoord(),_location.getYcoord());
    }
}
