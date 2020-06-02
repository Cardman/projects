package aiki.gui.components.walk.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import aiki.gui.components.walk.ScenePanel;

public class BuyOrSellEvent implements ActionListener {

    private ScenePanel scene;

    public BuyOrSellEvent(ScenePanel _scene) {
        scene = _scene;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        scene.clearItemList();
    }

}
