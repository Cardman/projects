package aiki.gui.listeners;

import aiki.gui.components.walk.ScenePanel;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerIntRel;

public class TileListener implements AbsMouseListenerIntRel  {

    private ScenePanel scene;

    private int xCoords;

    private int yCoords;

    public TileListener(ScenePanel _scene, int _xCoords, int _yCoords) {
        scene = _scene;
        xCoords = _xCoords;
        yCoords = _yCoords;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        scene.choosePlace(xCoords, yCoords);
    }
}
