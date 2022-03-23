package code.bean.nat;

import code.maths.Rate;
import code.util.StringMap;
import code.util.core.BoolVal;

public final class StringMapObjectSample {

    private final StringMap<BoolVal> mapBool = new StringMap<BoolVal>();
    private final StringMap<String> mapStr = new StringMap<String>();
    private final StringMap<Rate> mapRate = new StringMap<Rate>();

    public void put(String _key, boolean _v) {
        if (_v) {
            mapBool.put(_key, BoolVal.TRUE);
        } else {
            mapBool.put(_key, BoolVal.FALSE);
        }
    }

    public void put(String _key, String _v) {
        mapStr.put(_key, _v);
    }

    public void put(String _key, Rate _v) {
        mapRate.put(_key, _v);
    }


    public String getString(String _key) {
        return mapStr.getVal(_key);
    }
    public void putAllMap(StringMapObjectSample _m) {
        mapBool.putAllMap(_m.mapBool);
        mapStr.putAllMap(_m.mapStr);
        mapRate.putAllMap(_m.mapRate);
    }

}
