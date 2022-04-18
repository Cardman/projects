package aiki.gui.components.walk.events;

import aiki.gui.components.walk.ScenePanel;
import code.gui.events.AbsActionListener;

public class TakeItemFromTeamEvent implements AbsActionListener {

    private ScenePanel scene;

    public TakeItemFromTeamEvent(ScenePanel _scene) {
        scene = _scene;
    }

    @Override
    public void action() {
        scene.takeItemFromTeam();
    }
}
