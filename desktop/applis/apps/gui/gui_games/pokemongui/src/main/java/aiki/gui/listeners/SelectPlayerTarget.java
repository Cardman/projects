package aiki.gui.listeners;

import aiki.gui.components.fight.Battle;
import aiki.gui.components.fight.MiniTargetLabel;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerIntRel;

public final class SelectPlayerTarget implements AbsMouseListenerIntRel {

    private final Battle battle;

    private final int number;

    private final MiniTargetLabel cur;

    public SelectPlayerTarget(Battle _battle, int _number, MiniTargetLabel _current) {
        battle = _battle;
        number = _number;
        cur = _current;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        battle.choosePlayerTarget(number, cur);
    }
}
