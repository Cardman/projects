package aiki.gui.components.walk.events;

import aiki.gui.components.walk.ScenePanel;
import code.gui.events.AbsActionListener;

public class BuyItemsEvent  implements AbsActionListener {

    private final ScenePanel scene;

    public BuyItemsEvent(ScenePanel _scene) {
        scene = _scene;
    }

    @Override
    public void action() {
        scene.buyItems();
    }
}
