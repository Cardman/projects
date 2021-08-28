package aiki.gui.components.checks;
import aiki.gui.components.fight.Battle;

public final class MoveFighterCheckBox extends CheckBox {

    private Battle battle;

    public MoveFighterCheckBox(String _key, String _text, boolean _selected,Battle _battle) {
        super(_key, _text, _selected, _battle.getWindow().getCompoFactory());
        battle = _battle;
    }

    @Override
    protected void processKey(String _key) {
        battle.addOrForgetMove(_key);
    }

}
