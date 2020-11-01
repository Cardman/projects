package aiki.game.fight.actions;



public final class ActionHealMove extends ActionHeal implements ChosenMove{

    /***/
    private String chosenHealingItem;

    /***/
    private String firstChosenMove;

    @Override
    public String getChosenHealingItem() {
        return chosenHealingItem;
    }

    @Override
    public void setChosenHealingItem(String _chosenHealingItem) {
        chosenHealingItem = _chosenHealingItem;
    }

    @Override
    public String getFirstChosenMove() {
        return firstChosenMove;
    }

    @Override
    public void setFirstChosenMove(String _firstChosenMove) {
        firstChosenMove = _firstChosenMove;
    }
}
