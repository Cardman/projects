package aiki.gui.components.walk.events;

import aiki.gui.components.walk.ScenePanel;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsMouseListenerRel;

public class ChangeItemListEvent implements AbsActionListener {

    private ScenePanel scene;

    private boolean add;

    public ChangeItemListEvent(ScenePanel _scene, boolean _add) {
        scene = _scene;
        add = _add;
    }

    @Override
    public void action() {
        scene.addOrRemoveItemToBuyOrSell(add);
    }
}
