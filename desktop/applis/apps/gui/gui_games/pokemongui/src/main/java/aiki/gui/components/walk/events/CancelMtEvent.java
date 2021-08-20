package aiki.gui.components.walk.events;

import aiki.gui.components.walk.ScenePanel;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class CancelMtEvent extends AbsMouseListenerRel {

    private ScenePanel scene;

    public CancelMtEvent(ScenePanel _scene) {
        scene = _scene;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        scene.cancelMt();
    }
}
