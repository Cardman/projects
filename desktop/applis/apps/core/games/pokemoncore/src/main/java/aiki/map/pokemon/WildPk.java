package aiki.map.pokemon;

import aiki.db.DataBase;
import aiki.map.pokemon.enums.Gender;
import code.maths.LgInt;
import code.maths.montecarlo.EventFreq;
import code.maths.montecarlo.MonteCarloList;
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
    public static int nbPk(MonteCarloList<WildPk> _monte) {
        CustList<WildPk> wp_ = new CustList<WildPk>();
        for (WildPk e: _monte.events()) {
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
    public static boolean containsPk(MonteCarloList<WildPk> _monte, WildPk _ev) {
        return contains(_ev, _monte.events());
    }

    public static boolean contains(WildPk _ev, CustList<WildPk> _list) {
        for (WildPk e: _list) {
            if (e.eq(_ev)) {
                return true;
            }
        }
        return false;
    }
    public static LgInt freqPk(MonteCarloList<WildPk> _monte, WildPk _ev) {
        LgInt sum_ = LgInt.zero();
        for (EventFreq<WildPk> e: _monte.getEvents()) {
            if (e.getEvent().eq(_ev)) {
                sum_.addNb(e.getFreq());
            }
        }
        return sum_;
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
