package aiki.game.fight.actions;


public final class ActionSwitch extends AbstractAction implements ChosenReplacing{

    /***/
    private int substitute;

    public ActionSwitch() {
        super(KindAction.SWITCH);
    }

    @Override
    public int getSubstitute() {
        return substitute;
    }

    @Override
    public void setSubstitute(int _substitute) {
        substitute = _substitute;
    }
}
