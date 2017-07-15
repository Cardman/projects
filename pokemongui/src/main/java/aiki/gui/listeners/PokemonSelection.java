package aiki.gui.listeners;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import aiki.gui.components.fight.Battle;

public class PokemonSelection implements ListSelectionListener {

    private Battle battle;

    public PokemonSelection(Battle _battle) {
        battle = _battle;
    }

    @Override
    public void valueChanged(ListSelectionEvent _e) {
        if (_e.getValueIsAdjusting()) {
            return;
        }
        battle.chooseEvolution();
    }

}
