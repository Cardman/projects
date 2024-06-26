package aiki.gui.components.checks;
import aiki.gui.components.fight.Battle;

public final class MoveFighterCheckBox extends CheckBox {

    private final Battle battle;

    public MoveFighterCheckBox(String _key, String _text, boolean _selected,Battle _battle) {
        super(_key, _text, _selected, _battle.getWindow().getFrames(), _battle.getWindow().getFacade(), _battle.getWindow().getModal());
        battle = _battle;
    }

    @Override
    protected void processKey(String _key) {
        battle.addOrForgetMove(_key);
    }

}
