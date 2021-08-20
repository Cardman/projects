package aiki.gui.components.walk.events;

import aiki.gui.components.walk.ScenePanel;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class ChangeItemListEvent extends AbsMouseListenerRel {

    private ScenePanel scene;

    private boolean add;

    public ChangeItemListEvent(ScenePanel _scene, boolean _add) {
        scene = _scene;
        add = _add;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        scene.addOrRemoveItemToBuyOrSell(add);
    }
}
