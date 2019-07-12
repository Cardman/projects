package aiki.map.characters;

import aiki.db.DataBase;
import aiki.map.characters.enums.SellType;
import code.util.*;
import code.util.StringList;


public final class Seller extends Person {

    private SellType sell;

    private StringList items;

    private Shorts tm;

    public void validate(DataBase _data) {
        if (!_data.getItems().containsAllAsKeys(items)) {
            _data.setError(true);
        }
        if (!_data.getTmPrice().containsAllAsKeys(tm)) {
            _data.setError(true);
        }
        if (sell == SellType.MOVE) {
            if (!items.isEmpty()) {
                _data.setError(true);
            }
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
        if (!items.isEmpty()) {
            _data.setError(true);

        }
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
