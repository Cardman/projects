package aiki.map.characters;

import aiki.db.DataBase;
import aiki.map.characters.enums.SellType;
import aiki.util.DataInfoChecker;
import code.util.Shorts;
import code.util.StringList;


public final class Seller extends Person {

    private SellType sell;

    private StringList items;

    private Shorts tm;

    public void validate(DataBase _data) {
        DataInfoChecker.checkStringListContains(_data.getItems().getKeys(),items,_data);
        DataInfoChecker.checkShortsContains(_data.getTmPrice().getKeys(),tm,_data);
        if (sell == SellType.MOVE) {
            DataInfoChecker.checkEmptyStringList(items,_data);
            if (!tm.isEmpty()) {
                _data.setError(true);
            }
            return;
        }
        if (sell == SellType.ITEM) {
            if (!tm.isEmpty()) {
                _data.setError(true);
            }
            return;
        }
        DataInfoChecker.checkEmptyStringList(items,_data);
    }

    public SellType getSell() {
        return sell;
    }

    public void setSell(SellType _sell) {
        sell = _sell;
    }

    public StringList getItems() {
        return items;
    }

    public void setItems(StringList _items) {
        items = _items;
    }

    public Shorts getTm() {
        return tm;
    }

    public void setTm(Shorts _tm) {
        tm = _tm;
    }

}
