package aiki.gui.listeners;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.gui.components.walk.ScenePanel;

public class AbilityWalkEvent extends MouseAdapter {

    private ScenePanel window;

    private String ability;

    public AbilityWalkEvent(ScenePanel _window, String _ability) {
        window = _window;
        ability = _ability;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        window.getAbility(ability);
    }
}
