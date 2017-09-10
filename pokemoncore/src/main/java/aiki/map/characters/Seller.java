package aiki.map.characters;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.map.characters.enums.SellType;
import code.serialize.CheckedData;
import code.util.Numbers;
import code.util.StringList;
import code.util.annot.RwXml;

@RwXml
public class Seller extends Person {

    @CheckedData
    private SellType sell;

    private StringList items;

    private Numbers<Short> tm;

    public void validate(DataBase _data) {
        if (sell == null) {
            throw new DataException();
        }
        if (!_data.getItems().containsAllAsKeys(items)) {
            throw new DataException();
        }
        if (!_data.getTmPrice().containsAllAsKeys(tm)) {
            throw new DataException();
        }
        if (sell == SellType.MOVE) {
            if (!items.isEmpty()) {
                throw new DataException();
            }
            if (!tm.isEmpty()) {
                throw new DataException();
            }
            return;
        }
        if (sell == SellType.ITEM) {
            if (!tm.isEmpty()) {
                throw new DataException();
            }
            return;
        }
        if (!items.isEmpty()) {
            throw new DataException();
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
