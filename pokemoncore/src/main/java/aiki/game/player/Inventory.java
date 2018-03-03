package aiki.game.player;
import aiki.DataBase;
import aiki.exceptions.GameLoadException;
import code.maths.LgInt;
import code.util.EntryCust;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import code.util.annot.RwXml;

@RwXml
public class Inventory {

    private StringMap<LgInt> items;

    private NumberMap<Short,Boolean> tm;

    private NumberMap<Short,Boolean> hm;

    public Inventory() {
    }

    public Inventory(DataBase _dataBase) {
        items = new StringMap<LgInt>();
        for (String o: _dataBase.getItems().getKeys()) {
            items.put(o, LgInt.zero());
        }
        tm = new NumberMap<Short,Boolean>();
        for (short t: _dataBase.getTm().getKeys()) {
            tm.put(t, false);
        }
        hm = new NumberMap<Short,Boolean>();
        for (short t: _dataBase.getHm().getKeys()) {
            hm.put(t, false);
        }
    }

    public void validate(DataBase _data) {
        for (LgInt i: items.values()) {
            if (!i.isZeroOrGt()) {
                throw new GameLoadException();
            }
        }
        StringList obj_ = items.getKeys();
        StringList objData_ = _data.getItems().getKeys();
        if (!StringList.equalsSet(obj_, objData_)) {
            throw new GameLoadException();
        }
        for (Object o: tm.values()) {
            if (!(o instanceof Boolean)) {
                throw new GameLoadException();
            }
        }
        for (Object o: hm.values()) {
            if (!(o instanceof Boolean)) {
                throw new GameLoadException();
            }
        }
        if (!Numbers.equalsSetShorts(tm.getKeys(), _data.getTm().getKeys())) {
            throw new GameLoadException();
        }
        if (!Numbers.equalsSetShorts(hm.getKeys(), _data.getHm().getKeys())) {
            throw new GameLoadException();
        }
    }
    public void getTm(short _t) {
        tm.put(_t, true);
    }

    public void getHm(short _t) {
        hm.put(_t, true);
    }

    public LgInt getNumber(String _object) {
        return items.getVal(_object);
    }

    public void use(String _object) {
        items.getVal(_object).decrement();
    }

    public void sell(String _object, LgInt _number) {
        items.getVal(_object).removeNb(_number);
    }

    public void getItem(String _object) {
        items.getVal(_object).increment();
    }

    public void buy(String _object, LgInt _number) {
        items.getVal(_object).addNb(_number);
    }

    Numbers<Short> getAllTm() {
        return tm.getKeys();
    }

    Numbers<Short> getAllHm() {
        return hm.getKeys();
    }

    public Numbers<Short> gotTm() {
        Numbers<Short> n_;
        n_ = new Numbers<Short>();
        for (EntryCust<Short,Boolean> e: tm.entryList()) {
            if (e.getValue()) {
                n_.add(e.getKey());
            }
        }
        return n_;
    }

    public Numbers<Short> gotHm() {
        Numbers<Short> n_;
        n_ = new Numbers<Short>();
        for (EntryCust<Short,Boolean> e: hm.entryList()) {
            if (e.getValue()) {
                n_.add(e.getKey());
            }
        }
        return n_;
    }

    StringList getItemsKeys() {
        return items.getKeys();
    }

    public StringMap<LgInt> getItems() {
        return items;
    }
    public void setItems(StringMap<LgInt> _items) {
        items = _items;
    }

    public NumberMap<Short, Boolean> getTm() {
        return tm;
    }

    public void setTm(NumberMap<Short, Boolean> _tm) {
        tm = _tm;
    }

    public NumberMap<Short, Boolean> getHm() {
        return hm;
    }

    public void setHm(NumberMap<Short, Boolean> _hm) {
        hm = _hm;
    }
}
