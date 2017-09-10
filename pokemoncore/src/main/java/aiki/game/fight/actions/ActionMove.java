package aiki.game.fight.actions;
import aiki.game.fight.TargetCoords;
import code.serialize.CheckedData;
import code.util.EqList;
import code.util.annot.RwXml;

@RwXml
public final class ActionMove extends AbstractAction implements ChosenMove,ChosenReplacing{

    /***/
    @CheckedData
    private String firstChosenMove;

    /***/
    @CheckedData
    private String finalChosenMove;

    /***/
    private EqList<TargetCoords> chosenTargets;

    /***/
    @CheckedData
    private byte substitute;

    @Override
    public boolean isActionMove() {
        return true;
    }
    @Override
    public boolean isActionHealMove() {
        return false;
    }
    @Override
    public boolean isActionSwitch() {
        return false;
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

    @Override
    public boolean isEmpty() {
        return false;
    }
}
