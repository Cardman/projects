package code.bean.nat;

import code.expressionlanguage.structs.Struct;
import code.maths.Rate;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.BoolVal;

public class StringMapObjectBase {
    private final StringMap<Rate> mapRate = new StringMap<Rate>();
    private final StringMap<Integer> mapInt = new StringMap<Integer>();
    private final StringMap<String> mapString = new StringMap<String>();
    private final StringMap<StringList> mapStringList = new StringMap<StringList>();
    private final StringMap<BoolVal> mapBoolean = new StringMap<BoolVal>();
    private final StringMap<Struct> beansOthers = new StringMap<Struct>();

    public static BoolVal to(boolean _v) {
        if (_v) {
            return BoolVal.TRUE;
        } else {
            return BoolVal.FALSE;
        }
    }

    public boolean containsBase(String _key) {
        return mapRate.contains(_key)||
                mapInt.contains(_key)||
                mapString.contains(_key)||
                mapStringList.contains(_key)||
                mapBoolean.contains(_key)||beansOthers.contains(_key);
    }
    public static boolean from(BoolVal _v) {
        return _v == BoolVal.TRUE;
    }
    public StringMap<BoolVal> getMapBoolean() {
        return mapBoolean;
    }

    public void putAllMapBase(StringMapObjectBase _m) {
        mapRate.putAllMap(_m.mapRate);
        mapInt.putAllMap(_m.mapInt);
        mapString.putAllMap(_m.mapString);
        mapStringList.putAllMap(_m.mapStringList);
        mapBoolean.putAllMap(_m.mapBoolean);
        beansOthers.putAllMap(_m.beansOthers);
    }
    public void removeKeyBase(String _key) {
        mapRate.removeKey(_key);
        mapInt.removeKey(_key);
        mapString.removeKey(_key);
        mapStringList.removeKey(_key);
        mapBoolean.removeKey(_key);
        beansOthers.removeKey(_key);
    }
    public void put(String _key, Rate _v) {
        mapRate.put(_key, _v);
    }

    public void put(String _key, int _v) {
        mapInt.put(_key, _v);
    }

    public void put(String _key, String _v) {
        mapString.put(_key, _v);
    }

    public void put(String _key, boolean _v) {
        mapBoolean.put(_key, to(_v));
    }

    public void put(String _key, StringList _v) {
        mapStringList.put(_key, _v);
    }

    public void put(String _key, Struct _v) {
        beansOthers.put(_key, _v);
    }
    public Rate getValRate(String _key) {
        return mapRate.getVal(_key);
    }
    public boolean getValBool(String _key) {
        return from(mapBoolean.getVal(_key));
    }

    public StringList getValList(String _key) {
        return mapStringList.getVal(_key);
    }
    public String getValStr(String _key) {
        return mapString.getVal(_key);
    }

    public int getValInt(String _key) {
        return defInt(mapInt.getVal(_key));
    }
    private int defInt(Integer _i) {
        if (_i == null) {
            return 0;
        }
        return _i;
    }

    public StringMap<Struct> getBeansOthers() {
        return beansOthers;
    }

    public StringMap<Integer> getMapInt() {
        return mapInt;
    }

}
