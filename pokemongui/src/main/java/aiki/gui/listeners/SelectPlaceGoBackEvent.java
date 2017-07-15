package aiki.gui.listeners;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.gui.components.walk.ScenePanel;

public class SelectPlaceGoBackEvent extends MouseAdapter {

    private ScenePanel scene;

    private int index;

    public SelectPlaceGoBackEvent(ScenePanel _scene, int _index) {
        scene = _scene;
        index = _index;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        scene.choosePlace(index);
    }
}
