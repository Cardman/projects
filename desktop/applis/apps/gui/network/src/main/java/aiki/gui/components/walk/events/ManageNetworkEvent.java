package aiki.gui.components.walk.events;

import aiki.gui.components.walk.ScenePanelMulti;
import code.gui.events.AbsActionListener;

public class ManageNetworkEvent implements AbsActionListener {

    private ScenePanelMulti scene;

    public ManageNetworkEvent(ScenePanelMulti _scene) {
        scene = _scene;
    }

    @Override
    public void action() {
        scene.manageNetwork();
    }
}
