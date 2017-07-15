package aiki.util;
import code.util.CustList;
import code.util.Numbers;
import code.util.ints.Cmp;
import code.util.pagination.Sorting;


public final class SortingEgg implements Sorting,Cmp<SortingEgg> {

    private int index;

    private int steps;

    private String name;

    private String keyName;

    @Override
    public boolean eq(SortingEgg _obj) {
        return cmp(_obj) == CustList.EQ_CMP;
    }

    @Override
    public int cmp(SortingEgg _o2) {
        return Numbers.compare(getIndex(), _o2.getIndex());
    }

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
