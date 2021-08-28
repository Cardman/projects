package aiki.gui.components.fight.events;

import aiki.gui.components.fight.Battle;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsMouseListenerRel;

public class CatchNotKoPokemonEvent implements AbsActionListener {

    private Battle battle;

    public CatchNotKoPokemonEvent(Battle _battle) {
        battle = _battle;
    }

    @Override
    public void action() {
        battle.catchNotKoPokemon();
    }
}
