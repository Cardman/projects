package aiki.util;
import aiki.facade.Sorting;
import aiki.fight.moves.enums.TargetChoice;

public final class SortingMove extends Sorting {

    private int moveClass;

    private int pp;

    private int priority;

    private TargetChoice targetChoice;

    private int price;

    public int getMoveClass() {
        return moveClass;
    }

    public void setMoveClass(int _moveClass) {
        moveClass = _moveClass;
    }

    public int getPp() {
        return pp;
    }

    public void setPp(int _pp) {
        pp = _pp;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int _priority) {
        priority = _priority;
    }

    public TargetChoice getTargetChoice() {
        return targetChoice;
    }

    public void setTargetChoice(TargetChoice _targetChoice) {
        targetChoice = _targetChoice;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int _price) {
        price = _price;
    }
}
