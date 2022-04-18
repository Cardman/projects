package aiki.gui.listeners;

import aiki.gui.components.Paginator;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerIntRel;


public class PaginatorEvent implements AbsMouseListenerIntRel {

    private Paginator paginator;

    private int index;

    public PaginatorEvent(Paginator _paginator, int _index) {
        paginator = _paginator;
        index = _index;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        paginator.check(index);
    }
}
