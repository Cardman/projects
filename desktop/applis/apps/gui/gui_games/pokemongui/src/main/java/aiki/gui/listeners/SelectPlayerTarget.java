package aiki.gui.listeners;

import aiki.gui.components.fight.Battle;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class SelectPlayerTarget extends AbsMouseListenerRel {

    private Battle battle;

    private byte number;

    private int index;

    public SelectPlayerTarget(Battle _battle, byte _number, int _index) {
        battle = _battle;
        number = _number;
        index = _index;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        battle.choosePlayerTarget(number, index);
    }
}
