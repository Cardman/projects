package aiki.fight.items;

import aiki.db.DataBase;
import aiki.util.DataInfoChecker;


public abstract class Item {

    public static final String BALL = "0";
    public static final String BERRY = "1";
    public static final String BOOST = "2";
    public static final String EVOLVING_ITEM = "3";
    public static final String EVOLVING_STONE = "4";
    public static final String FOSSIL = "5";
    public static final String HEALING_HP = "6";
    public static final String HEALING_HP_STATUS = "7";
    public static final String HEALING_ITEM = "8";
    public static final String HEALING_PP = "9";
    public static final String HEALING_STATUS = "10";
    public static final String ITEM_FOR_BATTLE = "11";
    public static final String REPEL = "12";
    public static final String SELLING_ITEM = "13";
    private long price;

    /**
     * DataBase _data the zipped data base
     * 
     * @param _data
     */
    public void validate(DataBase _data) {
        DataInfoChecker.checkPositiveOrZero(price,_data);
    }

    public abstract String getItemType();

    public long getPrice() {
        return price;
    }

    public void setPrice(long _price) {
        price = _price;
    }

}
