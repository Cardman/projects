package aiki.gui.listeners;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.gui.components.walk.ScenePanel;

public class TileListener extends MouseAdapter {

    private ScenePanel scene;

    private int xCoords;

    private int yCoords;

    public TileListener(ScenePanel _scene, int _xCoords, int _yCoords) {
        scene = _scene;
        xCoords = _xCoords;
        yCoords = _yCoords;
    }

    @Override
    public void mouseReleased(MouseEvent _event) {
        scene.choosePlace(xCoords, yCoords);
    }
}
