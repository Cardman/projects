package aiki.game.fight.actions;


public abstract class ActionHeal extends AbstractAction implements ChosenHealing {

    /***/
    private boolean team;

    protected ActionHeal(KindAction _k) {
        super(_k);
    }

    @Override
    public boolean isTeam() {
        return team;
    }

    @Override
    public void setTeam(boolean _team) {
        team = _team;
    }

}
