package aiki.game.fight.actions;
import code.util.annot.RwXml;

@RwXml
public final class ActionSwitch extends AbstractAction implements ChosenReplacing{

    /***/
    private byte substitute;

    @Override
    public boolean isEmpty() {
        return false;
    }
    @Override
    public boolean isActionSwitch() {
        return true;
    }
    @Override
    public boolean isActionMove() {
        return false;
    }
    @Override
    public boolean isActionHealMove() {
        return false;
    }
    @Override
    public byte getSubstitute() {
        return substitute;
    }

    @Override
    public void setSubstitute(byte _substitute) {
        substitute = _substitute;
    }
}
