package aiki.gui.components.walk.events;

import aiki.gui.components.walk.ScenePanel;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class ReceiveFromHostEvent extends AbsMouseListenerRel {

    private ScenePanel scene;

    private boolean onlyEgg;

    public ReceiveFromHostEvent(ScenePanel _scene, boolean _onlyEgg) {
        scene = _scene;
        onlyEgg = _onlyEgg;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        scene.receiveFromHost(onlyEgg);
    }
}
