package code.vi.prot.impl.gui;

import code.gui.AbsMouseLocation;

import java.awt.event.MouseEvent;

public final class DefMouseLocation implements AbsMouseLocation {
    private final int xcoord;
    private final int ycoord;

    public DefMouseLocation(MouseEvent _action) {
        this.xcoord = _action.getX();
        this.ycoord = _action.getY();
    }

    @Override
    public int getXcoord() {
        return xcoord;
    }

    @Override
    public int getYcoord() {
        return ycoord;
    }
}
