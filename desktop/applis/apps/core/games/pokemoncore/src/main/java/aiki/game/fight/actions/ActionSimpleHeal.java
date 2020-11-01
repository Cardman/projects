package aiki.game.fight.actions;


public final class ActionSimpleHeal extends ActionHeal {

    /***/
    private String chosenHealingItem;

    @Override
    public String getChosenHealingItem() {
        return chosenHealingItem;
    }

    @Override
    public void setChosenHealingItem(String _chosenHealingItem) {
        chosenHealingItem = _chosenHealingItem;
    }

}
