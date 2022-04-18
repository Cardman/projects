package aiki.gui.components.fight.events;

import aiki.gui.components.fight.Battle;
import code.gui.events.AbsActionListener;

public class NicknameEvent implements AbsActionListener {

    private Battle battle;

    public NicknameEvent(Battle _battle) {
        battle = _battle;
    }

    @Override
    public void action() {
        battle.nicknamePokemon();
    }
}
