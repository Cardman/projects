package aiki.gui.components.fight.events;

import aiki.gui.components.fight.Battle;
import code.gui.events.AbsActionListener;

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
