package aiki.gui.listeners;

import aiki.gui.components.fight.Battle;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class SelectPlaceEvent extends AbsMouseListenerRel {

    private Battle battle;

    private byte index;

    public SelectPlaceEvent(Battle _battle, byte _index) {
        battle = _battle;
        index = _index;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        battle.changeFrontPlace(index);
    }
}
