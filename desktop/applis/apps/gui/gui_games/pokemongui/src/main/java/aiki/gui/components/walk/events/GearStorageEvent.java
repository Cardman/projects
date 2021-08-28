package aiki.gui.components.walk.events;

import aiki.facade.enums.StorageActions;
import aiki.gui.components.walk.ScenePanel;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsMouseListenerRel;

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
