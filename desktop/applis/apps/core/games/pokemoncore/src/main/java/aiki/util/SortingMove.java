package aiki.util;
import aiki.facade.Sorting;
import aiki.fight.moves.enums.TargetChoice;

public final class SortingMove extends Sorting {

    private int moveClass;

    private short pp;

    private byte priority;

    private TargetChoice targetChoice;

    private int price;

    public int getMoveClass() {
        return moveClass;
    }

    public void setMoveClass(int _moveClass) {
        moveClass = _moveClass;
    }

    public short getPp() {
        return pp;
    }

    public void setPp(short _pp) {
        pp = _pp;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte _priority) {
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
