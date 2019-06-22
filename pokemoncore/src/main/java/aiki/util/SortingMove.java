package aiki.util;
import aiki.facade.Sorting;
import aiki.fight.moves.enums.TargetChoice;
import code.util.CustList;
import code.util.*;
import code.util.ints.Cmp;

public final class SortingMove implements Sorting {

    private int index;

    private int moveClass;

    private String name;

    private String keyName;

    private short pp;

    private byte priority;

    private TargetChoice targetChoice;

    private int price;

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int _index) {
        index = _index;
    }

    public int getMoveClass() {
        return moveClass;
    }

    public void setMoveClass(int _moveClass) {
        moveClass = _moveClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String _keyName) {
        keyName = _keyName;
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
