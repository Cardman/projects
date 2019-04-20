package aiki.map.characters;

import aiki.db.DataBase;
import aiki.map.characters.enums.SellType;
import code.util.Numbers;
import code.util.StringList;


public final class Seller extends Person {

    private SellType sell;

    private StringList items;

    private Numbers<Short> tm;

    public void validate(DataBase _data) {
        if (!_data.getItems().containsAllAsKeys(items)) {
            _data.setError(true);
            return;

        }
        if (!_data.getTmPrice().containsAllAsKeys(tm)) {
            _data.setError(true);
            return;

        }
        if (sell == SellType.MOVE) {
            if (!items.isEmpty()) {
                _data.setError(true);
                return;

            }
            if (!tm.isEmpty()) {
                _data.setError(true);
                return;

            }
            return;
        }
        if (sell == SellType.ITEM) {
            if (!tm.isEmpty()) {
                _data.setError(true);
                return;

            }
            return;
        }
        if (!items.isEmpty()) {
            _data.setError(true);
            return;

        }
    }

    @Override
    public void validateForEditing(DataBase _data) {
        super.validateForEditing(_data);
        items.retainAllElements(_data.getItems().getKeys());
        tm.retainAllElements(_data.getTmPrice().getKeys());
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

    public Numbers<Short> getTm() {
        return tm;
    }

    public void setTm(Numbers<Short> _tm) {
        tm = _tm;
    }

}
