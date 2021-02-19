package aiki.map.levels;

import aiki.db.EquallablePkUtil;
import code.maths.montecarlo.EventFreq;
import code.maths.montecarlo.MonteCarloList;
import code.util.CustList;
import org.junit.Test;

import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.maths.LgInt;
import code.util.EqList;


public class AreaApparitionTest extends EquallablePkUtil {

    @Test
    public void random1Test() {
        CustList<WildPk> list_ = new CustList<WildPk>();
        MonteCarloList<WildPk> law_ = AreaApparition.random(list_, AreaApparition.ALWAYS_APPARITION);
        assertEq(0, law_.nbEvents());
    }

    @Test
    public void random2Test() {
        CustList<WildPk> list_ = new CustList<WildPk>();
        MonteCarloList<WildPk> law_ = AreaApparition.random(list_, 2);
        assertEq(1, law_.nbEvents());
        assertTrue(law_.events().first().hasJustBeenCreated());
    }

    @Test
    public void random3Test() {
        CustList<WildPk> list_ = new CustList<WildPk>();
        WildPk pk_;
        pk_ = new WildPk();
        pk_.setName("PIKACHU");
        pk_.setAbility("STATIK");
        pk_.setGender(Gender.NO_GENDER);
        pk_.setLevel((short) 3);
        pk_.setItem("");
        list_.add(pk_);
        MonteCarloList<WildPk> law_ = AreaApparition.random(list_, AreaApparition.ALWAYS_APPARITION);
        assertEq(1, law_.nbEvents());
        WildPk event_;
        event_ = law_.events().first();
        assertEq("PIKACHU", event_.getName());
        assertEq("STATIK", event_.getAbility());
        assertEq("", event_.getItem());
        assertEq(Gender.NO_GENDER, event_.getGender());
        assertEq(3, event_.getLevel());
    }

    @Test
    public void random4Test() {
        CustList<WildPk> list_ = new CustList<WildPk>();
        WildPk pk_;
        pk_ = new WildPk();
        pk_.setName("PIKACHU");
        pk_.setAbility("STATIK");
        pk_.setGender(Gender.NO_GENDER);
        pk_.setLevel((short) 3);
        pk_.setItem("");
        list_.add(pk_);
        MonteCarloList<WildPk> law_ = AreaApparition.random(list_, 3);
        assertEq(2, law_.nbEvents());
        assertTrue(containsPk(law_,pk_));
        assertTrue(containsPk(law_,new WildPk()));
        assertEq(new LgInt("1"), freqPk(law_,pk_));
        assertEq(new LgInt("2"), freqPk(law_,new WildPk()));
    }

    @Test
    public void random5Test() {
        CustList<WildPk> list_ = new CustList<WildPk>();
        WildPk pkOne_ = new WildPk();
        pkOne_.setName("PIKACHU");
        pkOne_.setAbility("STATIK");
        pkOne_.setGender(Gender.NO_GENDER);
        pkOne_.setLevel((short) 3);
        pkOne_.setItem("");
        list_.add(pkOne_);
        WildPk pkTwo_ = new WildPk();
        pkTwo_.setName("MELOFEE");
        pkTwo_.setAbility("STATIK");
        pkTwo_.setGender(Gender.NO_GENDER);
        pkTwo_.setLevel((short) 3);
        pkTwo_.setItem("");
        list_.add(pkTwo_);
        MonteCarloList<WildPk> law_ = AreaApparition.random(list_, 3);
        assertEq(3, law_.nbEvents());
        assertTrue(containsPk(law_,pkOne_));
        assertTrue(containsPk(law_,pkTwo_));
        assertTrue(containsPk(law_,new WildPk()));
        assertEq(new LgInt("1"), freqPk(law_,pkOne_));
        assertEq(new LgInt("1"), freqPk(law_,pkTwo_));
        assertEq(new LgInt("4"), freqPk(law_,new WildPk()));
    }

    @Test
    public void random6Test() {
        CustList<WildPk> list_ = new CustList<WildPk>();
        WildPk pkOne_ = new WildPk();
        pkOne_.setName("PIKACHU");
        pkOne_.setAbility("STATIK");
        pkOne_.setGender(Gender.NO_GENDER);
        pkOne_.setLevel((short) 3);
        pkOne_.setItem("");
        list_.add(pkOne_);
        WildPk pkTwo_ = new WildPk();
        pkTwo_.setName("MELOFEE");
        pkTwo_.setAbility("STATIK");
        pkTwo_.setGender(Gender.NO_GENDER);
        pkTwo_.setLevel((short) 3);
        pkTwo_.setItem("");
        list_.add(pkTwo_);
        WildPk pkThree_ = new WildPk();
        pkThree_.setName("MELOFEE");
        pkThree_.setAbility("STATIK");
        pkThree_.setGender(Gender.NO_GENDER);
        pkThree_.setLevel((short) 3);
        pkThree_.setItem("");
        list_.add(pkThree_);
        MonteCarloList<WildPk> law_ = AreaApparition.random(list_, 3);
        assertEq(3, law_.nbEvents());
        assertTrue(containsPk(law_,pkOne_));
        assertTrue(containsPk(law_,pkTwo_));
        assertTrue(containsPk(law_,new WildPk()));
        assertEq(new LgInt("1"), freqPk(law_,pkOne_));
        assertEq(new LgInt("2"), freqPk(law_,pkTwo_));
        assertEq(new LgInt("6"), freqPk(law_,new WildPk()));
    }

    @Test
    public void random7Test() {
        CustList<WildPk> list_ = new CustList<WildPk>();
        WildPk pkOne_ = new WildPk();
        pkOne_.setName("PIKACHU");
        pkOne_.setAbility("STATIK");
        pkOne_.setGender(Gender.NO_GENDER);
        pkOne_.setLevel((short) 3);
        pkOne_.setItem("");
        list_.add(pkOne_);
        WildPk pkTwo_ = new WildPk();
        pkTwo_.setName("MELOFEE");
        pkTwo_.setAbility("STATIK");
        pkTwo_.setGender(Gender.NO_GENDER);
        pkTwo_.setLevel((short) 3);
        pkTwo_.setItem("");
        list_.add(pkTwo_);
        MonteCarloList<WildPk> law_ = AreaApparition.random(list_, AreaApparition.ALWAYS_APPARITION);
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
        pkOne_.setName("PIKACHU");
        pkOne_.setAbility("STATIK");
        pkOne_.setGender(Gender.NO_GENDER);
        pkOne_.setLevel((short) 3);
        pkOne_.setItem("");
        list_.add(pkOne_);
        WildPk pkTwo_ = new WildPk();
        pkTwo_.setName("MELOFEE");
        pkTwo_.setAbility("STATIK");
        pkTwo_.setGender(Gender.NO_GENDER);
        pkTwo_.setLevel((short) 3);
        pkTwo_.setItem("");
        list_.add(pkTwo_);
        WildPk pkThree_ = new WildPk();
        pkThree_.setName("MELOFEE");
        pkThree_.setAbility("STATIK");
        pkThree_.setGender(Gender.NO_GENDER);
        pkThree_.setLevel((short) 3);
        pkThree_.setItem("");
        list_.add(pkThree_);
        MonteCarloList<WildPk> law_ = AreaApparition.random(list_, AreaApparition.ALWAYS_APPARITION);
        assertEq(2, law_.nbEvents());
        assertTrue(containsPk(law_,pkOne_));
        assertTrue(containsPk(law_,pkTwo_));
        assertEq(new LgInt("1"), freqPk(law_,pkOne_));
        assertEq(new LgInt("2"), freqPk(law_,pkTwo_));
    }
    private static boolean containsPk(MonteCarloList<WildPk> _monte, WildPk _ev) {
        for (WildPk e: _monte.events()) {
            if (e.eq(_ev)) {
                return true;
            }
        }
        return false;
    }
    private static LgInt freqPk(MonteCarloList<WildPk> _monte, WildPk _ev) {
        LgInt sum_ = LgInt.zero();
        for (EventFreq<WildPk> e: _monte.getEvents()) {
            if (e.getEvent().eq(_ev)) {
                sum_.addNb(e.getFreq());
            }
        }
        return sum_;
    }
}
