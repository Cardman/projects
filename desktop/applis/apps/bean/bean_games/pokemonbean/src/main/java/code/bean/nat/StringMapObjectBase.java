package code.bean.nat;

import aiki.beans.*;
import aiki.beans.endround.*;
import aiki.beans.moves.effects.*;
import aiki.beans.pokemon.evolutions.EvolutionBean;
import aiki.fight.moves.effects.*;
import code.maths.*;
import code.util.*;
import code.util.core.*;

public class StringMapObjectBase {
    private final StringMap<Rate> mapRate = new StringMap<Rate>();
    private final StringMap<Integer> mapInt = new StringMap<Integer>();
    private final StringMap<Long> mapLong = new StringMap<Long>();
    private final StringMap<String> mapString = new StringMap<String>();
    private final StringMap<StringList> mapStringList = new StringMap<StringList>();
    private final StringMap<BoolVal> mapBoolean = new StringMap<BoolVal>();
    private final StringMap<NaSt> beansOthers = new StringMap<NaSt>();
    private final CustList<TranslatedKey> evts = new CustList<TranslatedKey>();
    private final CustList<CustList<TranslatedKey>> evtsGroups = new CustList<CustList<TranslatedKey>>();
    private final CustList<Effect> current = new CustList<Effect>();
    private CustList<EffectBean> currentBean = new CustList<EffectBean>();
    private CustList<EffectEndRoundBean> currentBeanEnd = new CustList<EffectEndRoundBean>();
    private CustList<EvolutionBean> currentBeanEvo = new CustList<EvolutionBean>();

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
                mapLong.contains(_key)||
                mapString.contains(_key)||
                mapStringList.contains(_key)||
                mapBoolean.contains(_key)||beansOthers.contains(_key);
    }
    public static boolean from(BoolVal _v) {
        return _v == BoolVal.TRUE;
    }

    public void putAllMapBase(StringMapObjectBase _m) {
        mapRate.putAllMap(_m.mapRate);
        mapInt.putAllMap(_m.mapInt);
        mapLong.putAllMap(_m.mapLong);
        mapString.putAllMap(_m.mapString);
        mapStringList.putAllMap(_m.mapStringList);
        mapBoolean.putAllMap(_m.mapBoolean);
        beansOthers.putAllMap(_m.beansOthers);
        getEvts().clear();
        getEvtsGroups().clear();
        getEvts().addAllElts(_m.getEvts());
        getEvtsGroups().addAllElts(_m.getEvtsGroups());
        getCurrent().clear();
        getCurrent().addAllElts(_m.getCurrent());
        getCurrentBean().clear();
        getCurrentBean().addAllElts(_m.getCurrentBean());
        getCurrentBeanEnd().clear();
        getCurrentBeanEnd().addAllElts(_m.getCurrentBeanEnd());
        getCurrentBeanEvo().clear();
        getCurrentBeanEvo().addAllElts(_m.getCurrentBeanEvo());
    }
    public void removeKeyBase(String _key) {
        mapRate.removeKey(_key);
        mapInt.removeKey(_key);
        mapLong.removeKey(_key);
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

    public void put(String _key, long _v) {
        mapLong.put(_key, _v);
    }

    public void put(String _key, String _v) {
        mapString.put(_key, _v);
    }

    public void put(String _key, boolean _v) {
        put(_key, to(_v));
    }

    public void put(String _key, BoolVal _v) {
        mapBoolean.put(_key, _v);
    }

    public void put(String _key, StringList _v) {
        mapStringList.put(_key, _v);
    }

    public void put(String _key, NaSt _v) {
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

    public long getValLong(String _key) {
        return defLong(mapLong.getVal(_key));
    }
    private int defInt(Integer _i) {
        if (_i == null) {
            return 0;
        }
        return _i;
    }
    private long defLong(Long _i) {
        if (_i == null) {
            return 0;
        }
        return _i;
    }

    public StringMap<NaSt> getBeansOthers() {
        return beansOthers;
    }

    public CustList<TranslatedKey> getEvts() {
        return evts;
    }

    public CustList<CustList<TranslatedKey>> getEvtsGroups() {
        return evtsGroups;
    }

    public CustList<Effect> getCurrent() {
        return current;
    }

    public CustList<EffectBean> getCurrentBean() {
        return currentBean;
    }

    public void setCurrentBean(CustList<EffectBean> _c) {
        this.currentBean = _c;
    }

    public CustList<EffectEndRoundBean> getCurrentBeanEnd() {
        return currentBeanEnd;
    }

    public void setCurrentBeanEnd(CustList<EffectEndRoundBean> _c) {
        this.currentBeanEnd = _c;
    }

    public CustList<EvolutionBean> getCurrentBeanEvo() {
        return currentBeanEvo;
    }

    public void setCurrentBeanEvo(CustList<EvolutionBean> _c) {
        this.currentBeanEvo = _c;
    }
}
