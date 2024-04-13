package aiki.gui.listeners;
import aiki.gui.components.fight.Battle;
import code.gui.ListSelection;
import code.gui.SelectionInfo;

public final class FighterCaughtNicknameSelection implements ListSelection {

    private final Battle battle;

    public FighterCaughtNicknameSelection(Battle _battle) {
        battle = _battle;
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        battle.chooseFighterCaughtNickname();
    }
}
