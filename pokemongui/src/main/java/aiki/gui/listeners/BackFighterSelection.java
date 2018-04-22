package aiki.gui.listeners;
import aiki.gui.components.fight.Battle;
import code.gui.ListSelection;
import code.gui.SelectionInfo;

public class BackFighterSelection extends ListSelection {

    private Battle battle;

    public BackFighterSelection(Battle _battle) {
        battle = _battle;
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        battle.chooseBackFighter();
    }
}
