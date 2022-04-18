package aiki.gui.listeners;

import aiki.gui.components.fight.Battle;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerIntRel;

public class SelectPlaceEvent implements AbsMouseListenerIntRel {

    private Battle battle;

    private byte index;

    public SelectPlaceEvent(Battle _battle, byte _index) {
        battle = _battle;
        index = _index;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        battle.changeFrontPlace(index);
    }
}
