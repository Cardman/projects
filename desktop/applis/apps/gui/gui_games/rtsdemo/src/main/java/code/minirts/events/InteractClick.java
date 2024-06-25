package code.minirts.events;

import code.gui.*;
import code.gui.events.*;
import code.minirts.*;

public final class InteractClick implements AbsMouseListenerPresRel, AbsMouseMotionListener {

    private final WindowRts fenetre;
    public InteractClick(WindowRts _fenetre) {
        fenetre = _fenetre;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        if (!_buttons.isLeftMouseButton()) {
            fenetre.setNewLocation(_location.getXcoord(), _location.getYcoord());
            return;
        }
        if (fenetre.isDragged()) {
            fenetre.setDragged(false);
            return;
        }
        if (fenetre.isAddingSoldier()) {
            fenetre.addNewSoldier(_location.getXcoord(), _location.getYcoord());
            return;
        }
        fenetre.selectOrDeselect(_location.getXcoord(), _location.getYcoord());
    }

    @Override
    public void mousePressed(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        fenetre.setFirst(_location.getXcoord(), _location.getYcoord());
    }

    @Override
    public void mouseDragged(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        if (!_buttons.isLeftMouseButton()) {
            return;
        }
        fenetre.setDragged(true);
        fenetre.setLast(_location.getXcoord(), _location.getYcoord());
        fenetre.selectOrDeselectMulti();
    }

//    @Override
//    public void mouseEntered(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
//        //
//    }
//
//    @Override
//    public void mouseExited(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
//        //
//    }

//    @Override
//    public void mouseClicked(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
//        //
//    }

    @Override
    public void mouseMoved(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        //
        fenetre.getCurrentCoords().setText(_location.getXcoord()+","+ _location.getYcoord());
    }
}
