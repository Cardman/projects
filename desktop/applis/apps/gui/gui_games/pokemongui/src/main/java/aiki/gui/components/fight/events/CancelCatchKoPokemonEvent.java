package aiki.gui.components.fight.events;

import aiki.gui.components.fight.Battle;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsMouseListenerRel;

public class CancelCatchKoPokemonEvent implements AbsActionListener {

    private Battle battle;

    public CancelCatchKoPokemonEvent(Battle _battle) {
        battle = _battle;
    }

    @Override
    public void action() {
        battle.cancelCatchKoPokemon();
    }
}
