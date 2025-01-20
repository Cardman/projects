package aiki.util;
import aiki.facade.Sorting;
import aiki.fight.moves.enums.TargetChoice;

public final class SortingMove extends Sorting {

    private int moveClass;

    private long pp;

    private long priority;

    private TargetChoice targetChoice;

    private long price;

    public int getMoveClass() {
        return moveClass;
    }

    public void setMoveClass(int _moveClass) {
        moveClass = _moveClass;
    }

    public long getPp() {
        return pp;
    }

    public void setPp(long _pp) {
        pp = _pp;
    }

    public long getPriority() {
        return priority;
    }

    public void setPriority(long _priority) {
        priority = _priority;
    }

    public TargetChoice getTargetChoice() {
        return targetChoice;
    }

    public void setTargetChoice(TargetChoice _targetChoice) {
        targetChoice = _targetChoice;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long _price) {
        price = _price;
    }
}
