package aiki.gui.components.walk.events;

import aiki.gui.components.walk.ScenePanel;
import code.gui.events.AbsActionListener;

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
