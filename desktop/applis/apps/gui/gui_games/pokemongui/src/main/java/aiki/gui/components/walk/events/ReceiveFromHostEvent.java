package aiki.gui.components.walk.events;

import aiki.gui.components.walk.ScenePanel;
import code.gui.events.AbsActionListener;

public class ReceiveFromHostEvent implements AbsActionListener {

    private ScenePanel scene;

    private boolean onlyEgg;

    public ReceiveFromHostEvent(ScenePanel _scene, boolean _onlyEgg) {
        scene = _scene;
        onlyEgg = _onlyEgg;
    }

    @Override
    public void action() {
        scene.receiveFromHost(onlyEgg);
    }
}
