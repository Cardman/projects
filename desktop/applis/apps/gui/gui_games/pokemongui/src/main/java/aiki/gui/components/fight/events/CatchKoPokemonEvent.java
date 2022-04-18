package aiki.gui.components.fight.events;

import aiki.gui.components.fight.Battle;
import code.gui.events.AbsActionListener;

public class CatchKoPokemonEvent implements AbsActionListener {

    private Battle battle;

    public CatchKoPokemonEvent(Battle _battle) {
        battle = _battle;
    }

    @Override
    public void action() {
        battle.catchKoPokemon();
    }
}
