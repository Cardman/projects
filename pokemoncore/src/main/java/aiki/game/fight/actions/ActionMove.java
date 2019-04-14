package aiki.game.fight.actions;
import aiki.game.fight.TargetCoords;
import code.util.EqList;


public final class ActionMove extends AbstractAction implements ChosenMove,ChosenReplacing{

    /***/
    private String firstChosenMove;

    /***/
    private String finalChosenMove;

    /***/
    private EqList<TargetCoords> chosenTargets;

    /***/
    private byte substitute;

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

    public EqList<TargetCoords> getChosenTargets() {
        return chosenTargets;
    }

    public void setChosenTargets(EqList<TargetCoords> _chosenTargets) {
        chosenTargets = _chosenTargets;
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
