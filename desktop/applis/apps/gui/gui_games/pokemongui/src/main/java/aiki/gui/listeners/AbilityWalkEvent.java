package aiki.gui.listeners;

import aiki.gui.components.walk.ScenePanel;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerIntRel;

public class AbilityWalkEvent implements AbsMouseListenerIntRel {

    private ScenePanel window;

    private String ability;

    public AbilityWalkEvent(ScenePanel _window, String _ability) {
        window = _window;
        ability = _ability;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        window.getAbility(ability);
    }
}
