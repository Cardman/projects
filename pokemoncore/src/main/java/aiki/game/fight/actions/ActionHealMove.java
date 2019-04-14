package aiki.game.fight.actions;



public final class ActionHealMove extends ActionHeal implements ChosenMove{

    /***/
    private String firstChosenMove;

    @Override
    public String getFirstChosenMove() {
        return firstChosenMove;
    }

    @Override
    public void setFirstChosenMove(String _firstChosenMove) {
        firstChosenMove = _firstChosenMove;
    }
}
