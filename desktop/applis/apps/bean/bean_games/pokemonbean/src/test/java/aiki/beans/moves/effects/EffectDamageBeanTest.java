package aiki.beans.moves.effects;

import aiki.db.DataBase;
import aiki.game.fight.Fight;
import code.maths.LgInt;
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
}
