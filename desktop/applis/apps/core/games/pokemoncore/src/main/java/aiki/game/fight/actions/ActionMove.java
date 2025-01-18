package aiki.game.fight.actions;
import aiki.util.TargetCoordsList;


public final class ActionMove extends AbstractAction implements ChosenMove,ChosenReplacing{

    /***/
    private String firstChosenMove;

    /***/
    private String finalChosenMove;

    /***/
    private TargetCoordsList chosenTargets;

    /***/
    private int substitute;

    public ActionMove() {
        super(KindAction.MOVE);
    }

    @Override
    public String getFirstChosenMove() {
        return firstChosenMove;
    }

    @Override
    public void setFirstChosenMove(String _firstChosenMove) {
        firstChosenMove = _firstChosenMove;
    }

    public String getFinalChosenMove() {
        return finalChosenMove;
    }

    public void setFinalChosenMove(String _finalChosenMove) {
        finalChosenMove = _finalChosenMove;
    }

    public TargetCoordsList getChosenTargets() {
        return chosenTargets;
    }

    public void setChosenTargets(TargetCoordsList _chosenTargets) {
        chosenTargets = _chosenTargets;
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
