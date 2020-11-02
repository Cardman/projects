package aiki.game.fight.actions;


public final class ActionSwitch extends AbstractAction implements ChosenReplacing{

    /***/
    private byte substitute;

    public ActionSwitch() {
        super(KindAction.SWITCH);
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
