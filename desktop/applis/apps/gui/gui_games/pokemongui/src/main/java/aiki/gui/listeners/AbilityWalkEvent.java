package aiki.gui.listeners;

import aiki.gui.components.walk.ScenePanel;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class AbilityWalkEvent extends AbsMouseListenerRel {

    private ScenePanel window;

    private String ability;

    public AbilityWalkEvent(ScenePanel _window, String _ability) {
        window = _window;
        ability = _ability;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        window.getAbility(ability);
    }
}
