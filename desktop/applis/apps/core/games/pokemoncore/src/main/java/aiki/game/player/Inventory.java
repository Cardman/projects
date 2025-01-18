package aiki.game.player;
import aiki.db.DataBase;
import code.maths.LgInt;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;


public final class Inventory {

    private StringMap<LgInt> items;

    private IntMap<BoolVal> tm;

    private IntMap<BoolVal> hm;

    public Inventory() {
        setItems(new StringMap<LgInt>());
        setTm(new IntMap<BoolVal>());
        setHm(new IntMap<BoolVal>());
    }

    public Inventory(DataBase _dataBase) {
        setItems(new StringMap<LgInt>());
        for (String o: _dataBase.getItems().getKeys()) {
            getItems().put(o, LgInt.zero());
        }
        setTm(new IntMap<BoolVal>());
        for (int t: _dataBase.getTm().getKeys()) {
            getTm().put(t, BoolVal.FALSE);
        }
        setHm(new IntMap<BoolVal>());
        for (int t: _dataBase.getHm().getKeys()) {
            getHm().put(t, BoolVal.FALSE);
        }
    }

    public boolean validate(DataBase _data) {
        for (LgInt i: items.values()) {
            if (!i.isZeroOrGt()) {
                return false;
            }
        }
        CustList<String> obj_ = items.getKeys();
        CustList<String> objData_ = _data.getItems().getKeys();
        if (!StringUtil.equalsSet(obj_, objData_)) {
            return false;
        }
        if (!NumberUtil.equalsSetInts(tm.getKeys(), _data.getTm().getKeys())) {
            return false;
        }
        return NumberUtil.equalsSetInts(hm.getKeys(), _data.getHm().getKeys());
    }
    public void getTm(int _t) {
        tm.put(_t, BoolVal.TRUE);
    }

    public void getHm(int _t) {
        hm.put(_t, BoolVal.TRUE);
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

    CustList<Integer> getAllTm() {
        return tm.getKeys();
    }

    CustList<Integer> getAllHm() {
        return hm.getKeys();
    }

    public Ints gotTm() {
        Ints n_;
        n_ = new Ints();
        for (EntryCust<Integer,BoolVal> e: tm.entryList()) {
            if (e.getValue()==BoolVal.TRUE) {
                n_.add(e.getKey());
            }
        }
        return n_;
    }

    public Ints gotHm() {
        Ints n_;
        n_ = new Ints();
        for (EntryCust<Integer,BoolVal> e: hm.entryList()) {
            if (e.getValue()==BoolVal.TRUE) {
                n_.add(e.getKey());
            }
        }
        return n_;
    }

    CustList<String> getItemsKeys() {
        return items.getKeys();
    }

    public StringMap<LgInt> getItems() {
        return items;
    }
    public void setItems(StringMap<LgInt> _items) {
        items = _items;
    }

    public IntMap< BoolVal> getTm() {
        return tm;
    }

    public void setTm(IntMap< BoolVal> _tm) {
        tm = _tm;
    }

    public IntMap< BoolVal> getHm() {
        return hm;
    }

    public void setHm(IntMap< BoolVal> _hm) {
        hm = _hm;
    }
}
