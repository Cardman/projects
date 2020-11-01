package aiki.game.fight.actions;


public abstract class ActionHeal implements AbstractAction,ChosenHealing {

    /***/
    private boolean team;

    @Override
    public boolean isTeam() {
        return team;
    }

    @Override
    public void setTeam(boolean _team) {
        team = _team;
    }

}
