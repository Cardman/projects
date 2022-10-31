package aiki.beans.moves.effects;

import aiki.db.DataBase;
import aiki.game.fight.Fight;
import code.maths.LgInt;
import code.maths.Rate;
import org.junit.Test;

public final class EffectDamageBeanTest extends InitDbMoveEffectDamage {

    @Test
    public void hasConstPower1() {
        assertFalse(callEffectDamageBeanHasConstPower(dispMoveEffDamage(feedDbMoveEffDataDamComp(effDam(DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR,true,true,1,true,true,true)),0)));
    }
    @Test
    public void hasConstPower2() {
        assertTrue(callEffectDamageBeanHasConstPower(dispMoveEffDamage(feedDbMoveEffDataDamComp(effDam("1",true,true,1,true,true,true)),0)));
    }
    @Test
    public void hasDeterminatedLawForDamage1() {
        assertTrue(callEffectDamageBeanHasDeterminatedLawForDamage(dispMoveEffDamage(feedDbMoveEffDataDamComp(withDamageLawEv(effDam("1",true,true,1,true,true,true),DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR, LgInt.one())),0)));
    }
    @Test
    public void hasDeterminatedLawForDamage2() {
        assertFalse(callEffectDamageBeanHasDeterminatedLawForDamage(dispMoveEffDamage(feedDbMoveEffDataDamComp(withDamageLawEv(withDamageLawEv(effDam("1",true,true,1,true,true,true),DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR, LgInt.one()),"1", LgInt.one())),0)));
    }
    @Test
    public void power1() {
        assertEq(Fight.TEMPS_TOUR,callEffectDamageBeanPowerGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withDamageLawEv(effDam("1",true,true,1,true,true,true),DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR, LgInt.one())),0)));
    }
    @Test
    public void hasLawForDamage1() {
        assertTrue(callEffectDamageBeanHasLawForDamage(dispMoveEffDamage(feedDbMoveEffDataDamComp(withDamageLawEv(effDam("1",true,true,1,true,true,true),DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR, LgInt.one())),0)));
    }
    @Test
    public void hasLawForDamage2() {
        assertFalse(callEffectDamageBeanHasLawForDamage(dispMoveEffDamage(feedDbMoveEffDataDamComp(effDam("1",true,true,1,true,true,true)),0)));
    }
    @Test
    public void getDamageLaw1() {
        assertSizeEq(2,callEffectDamageBeanDamageLawGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withDamageLawEv(withDamageLawEv(effDam("1",true,true,1,true,true,true),"1", LgInt.one()),DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR, LgInt.one())),0)));
    }
    @Test
    public void getDamageLaw2() {
        assertEq("1",first(elt(callEffectDamageBeanDamageLawGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withDamageLawEv(withDamageLawEv(effDam("1",true,true,1,true,true,true),"1", LgInt.one()),DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR, LgInt.one())),0)),0)));
    }
    @Test
    public void getDamageLaw3() {
        assertEq(Fight.TEMPS_TOUR,first(elt(callEffectDamageBeanDamageLawGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withDamageLawEv(withDamageLawEv(effDam("1",true,true,1,true,true,true),"1", LgInt.one()),DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR, LgInt.one())),0)),1)));
    }
    @Test
    public void getDamageLaw4() {
        assertEq(Rate.newRate("1/4"),second(elt(callEffectDamageBeanDamageLawGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withDamageLawEv(withDamageLawEv(effDam("1",true,true,1,true,true,true),"1", LgInt.one()),DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR, LgInt.newLgInt("3"))),0)),0)));
    }
    @Test
    public void getDamageLaw5() {
        assertEq(Rate.newRate("3/4"),second(elt(callEffectDamageBeanDamageLawGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withDamageLawEv(withDamageLawEv(effDam("1",true,true,1,true,true,true),"1", LgInt.one()),DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR, LgInt.newLgInt("3"))),0)),1)));
    }
    @Test
    public void getMapVarsDamage1() {
        assertSizeEq(1,callEffectDamageBeanMapVarsDamageGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withDamageLawEv(withDamageLawEv(effDam("1",true,true,1,true,true,true),"1", LgInt.one()),DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR, LgInt.one())),0)));
    }
    @Test
    public void getMapVarsDamage2() {
        assertEq(Fight.TEMPS_TOUR,first(elt(callEffectDamageBeanMapVarsDamageGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withDamageLawEv(withDamageLawEv(effDam("1",true,true,1,true,true,true),"1", LgInt.one()),DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR, LgInt.one())),0)),0)));
    }
    @Test
    public void getMapVarsDamage3() {
        assertEq(TIME,second(elt(callEffectDamageBeanMapVarsDamageGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(withDamageLawEv(withDamageLawEv(effDam("1",true,true,1,true,true,true),"1", LgInt.one()),DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR, LgInt.newLgInt("3"))),0)),0)));
    }
    @Test
    public void getMapVarsDamage4() {
        assertSizeEq(1,callEffectDamageBeanMapVarsDamageGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(effDam(DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR,true,true,1,true,true,true)),0)));
    }
    @Test
    public void getMapVarsDamage5() {
        assertEq(Fight.TEMPS_TOUR,first(elt(callEffectDamageBeanMapVarsDamageGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(effDam(DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR,true,true,1,true,true,true)),0)),0)));
    }
    @Test
    public void getMapVarsDamage6() {
        assertEq(TIME,second(elt(callEffectDamageBeanMapVarsDamageGet(dispMoveEffDamage(feedDbMoveEffDataDamComp(effDam(DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR,true,true,1,true,true,true)),0)),0)));
    }
}
