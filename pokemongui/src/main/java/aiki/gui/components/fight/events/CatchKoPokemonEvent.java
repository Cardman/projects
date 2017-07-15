package aiki.gui.components.fight.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.gui.components.fight.Battle;

public class CatchKoPokemonEvent extends MouseAdapter {

    private Battle battle;

    public CatchKoPokemonEvent(Battle _battle) {
        battle = _battle;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        battle.catchKoPokemon();
    }
}
