package aiki.fight.items;

import aiki.db.DataBase;


public abstract class Item {

    private int price;

    private String name;

    /**
     * DataBase _data the zipped data base
     * 
     * @param _data
     */
    public void validate(DataBase _data) {
        if (price < 0) {
            _data.setError(true);

        }
    }

    public abstract String getItemType();

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int _price) {
        price = _price;
    }

}
