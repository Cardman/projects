package aiki.gui.components.walk.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import aiki.gui.components.walk.ScenePanel;
import code.gui.AbsActionListener;

public class BuyOrSellEvent implements AbsActionListener {

    private ScenePanel scene;

    public BuyOrSellEvent(ScenePanel _scene) {
        scene = _scene;
    }

    @Override
    public void action() {
        scene.clearItemList();
    }

}
