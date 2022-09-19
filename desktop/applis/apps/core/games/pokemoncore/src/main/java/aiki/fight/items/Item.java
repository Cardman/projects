package aiki.fight.items;

import aiki.db.DataBase;
import aiki.util.DataInfoChecker;


public abstract class Item {

    private int price;

    /**
     * DataBase _data the zipped data base
     * 
     * @param _data
     */
    public void validate(DataBase _data) {
        DataInfoChecker.checkPositiveOrZero(price,_data);
    }

    public abstract String getItemType();

    public int getPrice() {
        return price;
    }

    public void setPrice(int _price) {
        price = _price;
    }

}
