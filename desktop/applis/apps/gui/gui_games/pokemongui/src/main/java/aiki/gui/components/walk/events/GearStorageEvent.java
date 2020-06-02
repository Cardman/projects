package aiki.gui.components.walk.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.facade.enums.StorageActions;
import aiki.gui.components.walk.ScenePanel;

public class GearStorageEvent extends MouseAdapter {

    private ScenePanel scene;

    private StorageActions action;

    public GearStorageEvent(ScenePanel _scene, StorageActions _action) {
        scene = _scene;
        action = _action;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        scene.gearStorage(action);
    }
}
