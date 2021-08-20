package aiki.gui.components.fight.events;

import aiki.gui.components.fight.Battle;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class ShowFightDataEvent extends AbsMouseListenerRel {

    private Battle battle;

    public ShowFightDataEvent(Battle _battle) {
        battle = _battle;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        battle.showFightData();
    }
}
