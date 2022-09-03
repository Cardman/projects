package aiki.util;
import aiki.facade.Sorting;
import code.maths.LgInt;

public class SortingItem extends Sorting {

    private String itemClass;

    private int price;

    private LgInt number = LgInt.zero();

    public String getItemClass() {
        return itemClass;
    }

    public void setItemClass(String _itemClass) {
        itemClass = _itemClass;
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
