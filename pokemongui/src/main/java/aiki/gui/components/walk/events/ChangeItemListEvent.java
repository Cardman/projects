package aiki.gui.components.walk.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.gui.components.walk.ScenePanel;

public class ChangeItemListEvent extends MouseAdapter {

    private ScenePanel scene;

    private boolean add;

    public ChangeItemListEvent(ScenePanel _scene, boolean _add) {
        scene = _scene;
        add = _add;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        scene.addOrRemoveItemToBuyOrSell(add);
    }
}
