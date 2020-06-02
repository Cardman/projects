package aiki.util;
import aiki.facade.Sorting;


public final class SortingEgg implements Sorting {

    private int index;

    private int steps;

    private String name;

    private String keyName;

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int _index) {
        index = _index;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int _steps) {
        steps = _steps;
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
}
