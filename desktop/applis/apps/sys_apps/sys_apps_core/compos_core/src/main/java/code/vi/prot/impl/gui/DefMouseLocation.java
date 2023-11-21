package code.vi.prot.impl.gui;

import code.gui.CoreMouseLocation;

import java.awt.event.MouseEvent;

public final class DefMouseLocation extends CoreMouseLocation {

    public DefMouseLocation(MouseEvent _action) {
        super(_action.getX(),_action.getY());
    }

}
