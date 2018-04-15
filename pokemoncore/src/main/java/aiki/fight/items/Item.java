package aiki.fight.items;

import aiki.DataBase;
import code.util.annot.RwXml;

@RwXml
public abstract class Item {

    private int price;

    /**
     * DataBase _data the zipped data base
     * 
     * @param _data
     */
    public void validate(DataBase _data) {
        if (price < 0) {
            _data.setError(true);
            return;

        }
    }

    public abstract String getItemType();

    public int getPrice() {
        return price;
    }

    public void setPrice(int _price) {
        price = _price;
    }

}
