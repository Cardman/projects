package aiki.gui.components.editor;

import code.gui.*;
import code.gui.events.*;

public class FormTileMiniMapEvent implements AbsMouseListenerIntRel {
    private final FormMiniMapGrid form;
    private final int first;
    private final int second;
    public FormTileMiniMapEvent(FormMiniMapGrid _f, int _j, int _i) {
        form = _f;
        first = _j;
        second = _i;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        form.view(first,second);
    }
}
