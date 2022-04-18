package aiki.gui.components.walk.events;

import aiki.gui.components.walk.ScenePanel;
import code.gui.events.AbsActionListener;

public class ManageNetworkEvent implements AbsActionListener {

    private ScenePanel scene;

    public ManageNetworkEvent(ScenePanel _scene) {
        scene = _scene;
    }

    @Override
    public void action() {
        scene.manageNetwork();
    }
}
