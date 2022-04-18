package aiki.gui.components.walk.events;

import aiki.gui.components.walk.ScenePanel;
import code.gui.events.AbsActionListener;

public class ChoosePlaceEvent implements AbsActionListener {

    private ScenePanel scene;

    public ChoosePlaceEvent(ScenePanel _scene) {
        scene = _scene;
    }

    @Override
    public void action() {
        scene.choosePlace();
    }
}
