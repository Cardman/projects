package aiki.game.player;
import aiki.db.DataBase;
import code.maths.LgInt;
import code.util.EntryCust;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;


public final class Inventory {

    private StringMap<LgInt> items;

    private NumberMap<Short,Boolean> tm;

    private NumberMap<Short,Boolean> hm;

    public Inventory() {
        setItems(new StringMap<LgInt>());
        setTm(new NumberMap<Short,Boolean>());
        setHm(new NumberMap<Short,Boolean>());
    }

    public Inventory(DataBase _dataBase) {
        setItems(new StringMap<LgInt>());
        for (String o: _dataBase.getItems().getKeys()) {
            getItems().put(o, LgInt.zero());
        }
        setTm(new NumberMap<Short,Boolean>());
        for (short t: _dataBase.getTm().getKeys()) {
            getTm().put(t, false);
        }
        setHm(new NumberMap<Short,Boolean>());
        for (short t: _dataBase.getHm().getKeys()) {
            getHm().put(t, false);
        }
    }

    public boolean validate(DataBase _data) {
        for (LgInt i: items.values()) {
            if (!i.isZeroOrGt()) {
                return false;
            }
        }
        StringList obj_ = items.getKeys();
        StringList objData_ = _data.getItems().getKeys();
        if (!StringList.equalsSet(obj_, objData_)) {
            return false;
        }
        if (!Numbers.equalsSetShorts(tm.getKeys(), _data.getTm().getKeys())) {
            return false;
        }
        if (!Numbers.equalsSetShorts(hm.getKeys(), _data.getHm().getKeys())) {
            return false;
        }
        return true;
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
