package aiki.gui.components.walk.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.gui.components.walk.ScenePanel;

public class ReceiveFromHostEvent extends MouseAdapter {

    private ScenePanel scene;

    private boolean onlyEgg;

    public ReceiveFromHostEvent(ScenePanel _scene, boolean _onlyEgg) {
        scene = _scene;
        onlyEgg = _onlyEgg;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        scene.receiveFromHost(onlyEgg);
    }
}
