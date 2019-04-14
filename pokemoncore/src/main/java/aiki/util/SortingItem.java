package aiki.util;
import aiki.facade.Sorting;
import code.maths.LgInt;
import code.util.CustList;
import code.util.Numbers;
import code.util.ints.Cmp;

public final class SortingItem implements Sorting {

    private int index;

    private String itemClass;

    private String name;

    private String keyName;

    private int price;

    private LgInt number = LgInt.zero();

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
