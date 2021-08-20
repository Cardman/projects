package aiki.gui.components.walk.events;

import aiki.facade.enums.StorageActions;
import aiki.gui.components.walk.ScenePanel;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class GearStorageEvent extends AbsMouseListenerRel {

    private ScenePanel scene;

    private StorageActions action;

    public GearStorageEvent(ScenePanel _scene, StorageActions _action) {
        scene = _scene;
        action = _action;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        scene.gearStorage(action);
    }
}
