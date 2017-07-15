package aiki.util;
import code.maths.LgInt;
import code.util.CustList;
import code.util.Numbers;
import code.util.ints.Cmp;
import code.util.pagination.Sorting;

public final class SortingItem implements Sorting,Cmp<SortingItem> {

    private int index;

    private String itemClass;

    private String name;

    private String keyName;

    private int price;

    private LgInt number = LgInt.zero();

    @Override
    public boolean eq(SortingItem _g) {
        return cmp(_g) == CustList.EQ_CMP;
    }

    @Override
    public int cmp(SortingItem _o2) {
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

    public String getItemClass() {
        return itemClass;
    }

    public void setItemClass(String _itemClass) {
        itemClass = _itemClass;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int _price) {
        price = _price;
    }

    public LgInt getNumber() {
        return number;
    }

    public void setNumber(LgInt _number) {
        number = _number;
    }
}
