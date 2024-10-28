package aiki.map.levels;

import aiki.db.*;
import aiki.game.fight.*;
import aiki.map.pokemon.MonteCarloWilPkList;
import code.util.CustList;
import org.junit.Test;

import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.maths.LgInt;


public final class AreaApparitionTest extends InitializationDataBase {

    @Test
    public void random1Test() {
        CustList<WildPk> list_ = new CustList<WildPk>();
        MonteCarloWilPkList law_ = AreaApparition.random(list_, AreaApparition.ALWAYS_APPARITION,1);
        assertEq(0, law_.nbEvents());
    }

    @Test
    public void random2Test() {
        CustList<WildPk> list_ = new CustList<WildPk>();
        MonteCarloWilPkList law_ = AreaApparition.random(list_, 2,1);
        assertEq(1, law_.nbEvents());
        assertTrue(law_.events().first().isEmpty());
    }

    @Test
    public void random3Test() {
        CustList<WildPk> list_ = new CustList<WildPk>();
        WildPk pk_;
        pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setAbility(STATIK);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setLevel((short) 3);
        pk_.setItem(NULL_REF);
        list_.add(pk_);
        MonteCarloWilPkList law_ = AreaApparition.random(list_, AreaApparition.ALWAYS_APPARITION,1);
        assertEq(1, law_.nbEvents());
        CustList<WildPk> event_;
        event_ = law_.events().first();
        assertEq(1, event_.size());
        assertEq(PIKACHU, event_.get(0).getName());
        assertEq(STATIK, event_.get(0).getAbility());
        assertEq(NULL_REF, event_.get(0).getItem());
        assertEq(Gender.NO_GENDER, event_.get(0).getGender());
        assertEq(3, event_.get(0).getLevel());
    }

    @Test
    public void random4Test() {
        CustList<WildPk> list_ = new CustList<WildPk>();
        WildPk pk_;
        pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setAbility(STATIK);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setLevel((short) 3);
        pk_.setItem(NULL_REF);
        list_.add(pk_);
        MonteCarloWilPkList law_ = AreaApparition.random(list_, 3,1);
        assertEq(2, law_.nbEvents());
        assertTrue(containsPk(law_,pk_));
        assertTrue(containsPk(law_));
        assertEq(new LgInt("1"), freqPk(law_,pk_));
        assertEq(new LgInt("2"), freqPk(law_));
    }

    @Test
    public void random5Test() {
        CustList<WildPk> list_ = new CustList<WildPk>();
        WildPk pkOne_ = new WildPk();
        pkOne_.setName(PIKACHU);
        pkOne_.setAbility(STATIK);
        pkOne_.setGender(Gender.NO_GENDER);
        pkOne_.setLevel((short) 3);
        pkOne_.setItem(NULL_REF);
        list_.add(pkOne_);
        WildPk pkTwo_ = new WildPk();
        pkTwo_.setName(MELOFEE);
        pkTwo_.setAbility(STATIK);
        pkTwo_.setGender(Gender.NO_GENDER);
        pkTwo_.setLevel((short) 3);
        pkTwo_.setItem(NULL_REF);
        list_.add(pkTwo_);
        MonteCarloWilPkList law_ = AreaApparition.random(list_, 3,1);
        assertEq(3, law_.nbEvents());
        assertTrue(containsPk(law_,pkOne_));
        assertTrue(containsPk(law_,pkTwo_));
        assertTrue(containsPk(law_));
        assertEq(new LgInt("1"), freqPk(law_,pkOne_));
        assertEq(new LgInt("1"), freqPk(law_,pkTwo_));
        assertEq(new LgInt("4"), freqPk(law_));
    }

    @Test
    public void random6Test() {
        CustList<WildPk> list_ = new CustList<WildPk>();
        WildPk pkOne_ = new WildPk();
        pkOne_.setName(PIKACHU);
        pkOne_.setAbility(STATIK);
        pkOne_.setGender(Gender.NO_GENDER);
        pkOne_.setLevel((short) 3);
        pkOne_.setItem(NULL_REF);
        list_.add(pkOne_);
        WildPk pkTwo_ = new WildPk();
        pkTwo_.setName(MELOFEE);
        pkTwo_.setAbility(STATIK);
        pkTwo_.setGender(Gender.NO_GENDER);
        pkTwo_.setLevel((short) 3);
        pkTwo_.setItem(NULL_REF);
        list_.add(pkTwo_);
        WildPk pkThree_ = new WildPk();
        pkThree_.setName(MELOFEE);
        pkThree_.setAbility(STATIK);
        pkThree_.setGender(Gender.NO_GENDER);
        pkThree_.setLevel((short) 3);
        pkThree_.setItem(NULL_REF);
        list_.add(pkThree_);
        MonteCarloWilPkList law_ = AreaApparition.random(list_, 3,1);
        assertEq(3, law_.nbEvents());
        assertTrue(containsPk(law_,pkOne_));
        assertTrue(containsPk(law_,pkTwo_));
        assertTrue(containsPk(law_));
        assertEq(new LgInt("1"), freqPk(law_,pkOne_));
        assertEq(new LgInt("2"), freqPk(law_,pkTwo_));
        assertEq(new LgInt("6"), freqPk(law_));
    }

    @Test
    public void random7Test() {
        CustList<WildPk> list_ = new CustList<WildPk>();
        WildPk pkOne_ = new WildPk();
        pkOne_.setName(PIKACHU);
        pkOne_.setAbility(STATIK);
        pkOne_.setGender(Gender.NO_GENDER);
        pkOne_.setLevel((short) 3);
        pkOne_.setItem(NULL_REF);
        list_.add(pkOne_);
        WildPk pkTwo_ = new WildPk();
        pkTwo_.setName(MELOFEE);
        pkTwo_.setAbility(STATIK);
        pkTwo_.setGender(Gender.NO_GENDER);
        pkTwo_.setLevel((short) 3);
        pkTwo_.setItem(NULL_REF);
        list_.add(pkTwo_);
        MonteCarloWilPkList law_ = AreaApparition.random(list_, AreaApparition.ALWAYS_APPARITION,1);
        assertEq(2, law_.nbEvents());
        assertTrue(containsPk(law_,pkOne_));
        assertTrue(containsPk(law_,pkTwo_));
        assertEq(new LgInt("1"), freqPk(law_,pkOne_));
        assertEq(new LgInt("1"), freqPk(law_,pkTwo_));
    }

    @Test
    public void random8Test() {
        CustList<WildPk> list_ = new CustList<WildPk>();
        WildPk pkOne_ = new WildPk();
        pkOne_.setName(PIKACHU);
        pkOne_.setAbility(STATIK);
        pkOne_.setGender(Gender.NO_GENDER);
        pkOne_.setLevel((short) 3);
        pkOne_.setItem(NULL_REF);
        list_.add(pkOne_);
        WildPk pkTwo_ = new WildPk();
        pkTwo_.setName(MELOFEE);
        pkTwo_.setAbility(STATIK);
        pkTwo_.setGender(Gender.NO_GENDER);
        pkTwo_.setLevel((short) 3);
        pkTwo_.setItem(NULL_REF);
        list_.add(pkTwo_);
        WildPk pkThree_ = new WildPk();
        pkThree_.setName(MELOFEE);
        pkThree_.setAbility(STATIK);
        pkThree_.setGender(Gender.NO_GENDER);
        pkThree_.setLevel((short) 3);
        pkThree_.setItem(NULL_REF);
        list_.add(pkThree_);
        MonteCarloWilPkList law_ = AreaApparition.random(list_, AreaApparition.ALWAYS_APPARITION,1);
        assertEq(2, law_.nbEvents());
        assertTrue(containsPk(law_,pkOne_));
        assertTrue(containsPk(law_,pkTwo_));
        assertEq(new LgInt("1"), freqPk(law_,pkOne_));
        assertEq(new LgInt("2"), freqPk(law_,pkTwo_));
    }
    @Test
    public void random9Test() {
        MultAreaApparition m_ = new MultAreaApparition();
        m_.setAvgNbSteps((short) 2);
        CustList<WildPk> list_ = feed();
        m_.getWildPokemonList().add(list_);
        DataBase db_ = InitializationDataBase.initDb();
        m_.validate(db_);
        m_.initializeWildPokemon();
        assertFalse(db_.isError());
        assertFalse(m_.isVirtual());
        assertEq(3, m_.getWildPokemon().size());
        assertTrue(WildPk.eq(list_.get(0),m_.getWildPokemon(0)));
        assertTrue(WildPk.eq(list_.get(1),m_.getWildPokemon(1)));
        assertTrue(WildPk.eq(list_.get(2),m_.getWildPokemon(2)));
    }

    @Test
    public void random10Test() {
        MultAreaApparition m_ = new MultAreaApparition();
        m_.setAvgNbSteps((short) 2);
        CustList<WildPk> base_ = new CustList<WildPk>();
        base_.add(one());
        CustList<WildPk> list_ = feed();
        m_.getWildPokemonList().add(base_);
        m_.getWildPokemonFishingList().add(list_);
        DataBase db_ = initDb();
        m_.validate(db_);
        m_.initializeWildPokemon();
        assertFalse(db_.isError());
        assertFalse(m_.isVirtual());
        assertEq(3, m_.getWildPokemonFishing().size());
        assertTrue(WildPk.eq(list_.get(0),m_.getPokemonFishing(0)));
        assertTrue(WildPk.eq(list_.get(1),m_.getPokemonFishing(1)));
        assertTrue(WildPk.eq(list_.get(2),m_.getPokemonFishing(2)));
    }

    @Test
    public void random11Test() {
        MultAreaApparition m_ = new MultAreaApparition();
        DataBase db_ = initDb();
        m_.validate(db_);
        assertTrue(m_.isVirtual());
    }

    @Test
    public void random12Test() {
        MultAreaApparition m_ = new MultAreaApparition();
        CustList<WildPk> list_ = feed();
        m_.getWildPokemonFishingList().add(list_);
        assertFalse(m_.isVirtual());
    }
    private CustList<WildPk> feed() {
        CustList<WildPk> list_ = new CustList<WildPk>();
        list_.add(one());
        WildPk pkTwo_ = new WildPk();
        pkTwo_.setName(MELOFEE);
        pkTwo_.setAbility(STATIK);
        pkTwo_.setGender(Gender.NO_GENDER);
        pkTwo_.setLevel((short) 3);
        pkTwo_.setItem(NULL_REF);
        list_.add(pkTwo_);
        WildPk pkThree_ = new WildPk();
        pkThree_.setName(MELOFEE);
        pkThree_.setAbility(STATIK);
        pkThree_.setGender(Gender.NO_GENDER);
        pkThree_.setLevel((short) 3);
        pkThree_.setItem(NULL_REF);
        list_.add(pkThree_);
        return list_;
    }

    private WildPk one() {
        WildPk pkOne_ = new WildPk();
        pkOne_.setName(PIKACHU);
        pkOne_.setAbility(STATIK);
        pkOne_.setGender(Gender.NO_GENDER);
        pkOne_.setLevel((short) 3);
        pkOne_.setItem(NULL_REF);
        return pkOne_;
    }

    private static boolean containsPk(MonteCarloWilPkList _monte, WildPk _ev) {
        CustList<WildPk> l_ = new CustList<WildPk>();
        l_.add(_ev);
//        for (WildPk e: _monte.events()) {
//            if (e.eq(_ev)) {
//                return true;
//            }
//        }
//        return false;
        return WildPk.containsPk(_monte, l_);
    }

    private static boolean containsPk(MonteCarloWilPkList _monte) {
        CustList<WildPk> l_ = new CustList<WildPk>();
//        for (WildPk e: _monte.events()) {
//            if (e.eq(_ev)) {
//                return true;
//            }
//        }
//        return false;
        return WildPk.containsPk(_monte, l_);
    }
    private static LgInt freqPk(MonteCarloWilPkList _monte, WildPk _ev) {
        CustList<WildPk> l_ = new CustList<WildPk>();
        l_.add(_ev);
        return WildPk.freqPk(_monte, l_);
//        LgInt sum_ = LgInt.zero();
//        for (EventFreq<WildPk> e: _monte.getEvents()) {
//            if (e.getEvent().eq(_ev)) {
//                sum_.addNb(e.getFreq());
//            }
//        }
//        return sum_;
    }

    private static LgInt freqPk(MonteCarloWilPkList _monte) {
        CustList<WildPk> l_ = new CustList<WildPk>();
        return WildPk.freqPk(_monte, l_);
//        LgInt sum_ = LgInt.zero();
//        for (EventFreq<WildPk> e: _monte.getEvents()) {
//            if (e.getEvent().eq(_ev)) {
//                sum_.addNb(e.getFreq());
//            }
//        }
//        return sum_;
    }
}
