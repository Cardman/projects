package aiki.gui.components.walk.events;

import aiki.gui.components.walk.ScenePanel;
import code.gui.events.AbsActionListener;

public class ManageTeamEvent implements AbsActionListener {

    private ScenePanel scene;

    public ManageTeamEvent(ScenePanel _scene) {
        scene = _scene;
    }

    @Override
    public void action() {
        scene.manageTeam();
    }
}
