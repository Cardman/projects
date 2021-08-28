package aiki.gui.components.walk.events;

import aiki.gui.components.walk.ScenePanel;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsMouseListenerRel;

public class SelectItemForListEvent implements AbsActionListener {

    private ScenePanel scene;

    public SelectItemForListEvent(ScenePanel _scene) {
        scene = _scene;
    }

    @Override
    public void action() {
        scene.selectItemForList();
    }
}
