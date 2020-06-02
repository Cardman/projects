package aiki.game.fight.actions;


public abstract class ActionHeal implements AbstractAction,ChosenHealing {

    /***/
    private String chosenHealingItem;

    /***/
    private boolean team;

    @Override
    public String getChosenHealingItem() {
        return chosenHealingItem;
    }

    @Override
    public void setChosenHealingItem(String _chosenHealingItem) {
        chosenHealingItem = _chosenHealingItem;
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
