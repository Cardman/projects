package aiki.map.pokemon;

import aiki.db.DataBase;
import aiki.map.levels.AreaApparition;
import aiki.map.pokemon.enums.Gender;
import code.maths.LgInt;
import code.util.CustList;
import code.util.core.StringUtil;


public final class WildPk extends Pokemon {


    public WildPk() {
        setName(DataBase.EMPTY_STRING);
        setLevel((short) 1);
        setGender(Gender.NO_GENDER);
        setAbility(DataBase.EMPTY_STRING);
        setItem(DataBase.EMPTY_STRING);
    }
    public static int nbPk(MonteCarloWilPkList _monte) {
        CustList<CustList<WildPk>> wp_ = new CustList<CustList<WildPk>>();
        for (CustList<WildPk> e: _monte.events()) {
//            boolean eq_ = false;
//            for (WildPk f: wp_) {
//                if (e.eq(f)) {
//                    eq_ = true;
//                }
//            }
            if (!contains(e,wp_)) {
                wp_.add(e);
            }
        }
        return wp_.size();
    }
    public static boolean containsPk(MonteCarloWilPkList _monte, CustList<WildPk> _ev) {
        return contains(_ev, _monte.events());
    }

    public static boolean contains(CustList<WildPk> _ev, CustList<CustList<WildPk>> _list) {
        for (CustList<WildPk> e: _list) {
            if (AreaApparition.eqList(e, _ev)) {
                return true;
            }
        }
        return false;
    }
    public static LgInt freqPk(MonteCarloWilPkList _monte, CustList<WildPk> _ev) {
        return _monte.rate(_ev);
    }
    public static boolean eq(WildPk _wildOne, WildPk _wildTwo) {
        return _wildOne.eq(_wildTwo);
    }

    //For laws of apparition in areas
    public boolean eq(WildPk _obj) {
        if (!StringUtil.quickEq(getName(),_obj.getName())) {
            return false;
        }
        if (getLevel() != _obj.getLevel()) {
            return false;
        }
        if (getGender() != _obj.getGender()) {
            return false;
        }
        if (!StringUtil.quickEq(getAbility(),_obj.getAbility())) {
            return false;
        }
        return StringUtil.quickEq(getItem(), _obj.getItem());
    }


}
