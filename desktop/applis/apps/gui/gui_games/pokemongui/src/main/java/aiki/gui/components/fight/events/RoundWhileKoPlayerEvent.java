package aiki.gui.components.fight.events;

import aiki.gui.components.fight.Battle;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class RoundWhileKoPlayerEvent extends AbsMouseListenerRel {

    private Battle battle;

    public RoundWhileKoPlayerEvent(Battle _battle) {
        battle = _battle;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        battle.roundWhileKoPlayer();
    }
}
