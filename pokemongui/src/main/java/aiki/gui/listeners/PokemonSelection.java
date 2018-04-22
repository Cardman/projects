package aiki.gui.listeners;
import aiki.gui.components.fight.Battle;
import code.gui.ListSelection;
import code.gui.SelectionInfo;

public class PokemonSelection extends ListSelection {

    private Battle battle;

    public PokemonSelection(Battle _battle) {
        battle = _battle;
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        battle.chooseEvolution();
    }

}
