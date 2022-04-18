package aiki.gui.components.walk.events;

import aiki.facade.enums.StorageActions;
import aiki.gui.components.walk.ScenePanel;
import code.gui.events.AbsActionListener;

public class GearStorageEvent implements AbsActionListener {

    private ScenePanel scene;

    private StorageActions action;

    public GearStorageEvent(ScenePanel _scene, StorageActions _action) {
        scene = _scene;
        action = _action;
    }

    @Override
    public void action() {
        scene.gearStorage(action);
    }
}
