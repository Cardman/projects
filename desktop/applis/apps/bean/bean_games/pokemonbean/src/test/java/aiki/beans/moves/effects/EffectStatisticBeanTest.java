package aiki.beans.moves.effects;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.game.fight.Fight;
import code.maths.LgInt;
import code.maths.Rate;
import org.junit.Test;

public final class EffectStatisticBeanTest extends InitDbMoveEffectStatistic {
    @Test
    public void evt() {
        assertEq(Rate.one(),callEffectStatisticBeanEvtRateGet(dispMoveEffStatis(feedDbMoveEffDataDamComp(eff(Rate.one(), DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR, DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR)),0)));
    }
    @Test
    public void isAlwaysEnabled1() {
        assertTrue(callEffectStatisticBeanIsAlwaysEnabled(dispMoveEffStatis(feedDbMoveEffDataDamComp(eff(Rate.one(), DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR, DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR)),0)));
    }
    @Test
    public void isAlwaysEnabled2() {
        assertFalse(callEffectStatisticBeanIsAlwaysEnabled(dispMoveEffStatis(feedDbMoveEffDataDamComp(eff(Rate.newRate("1/2"), DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR, DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR)),0)));
    }
    @Test
    public void notEmptyVarBoost1() {
        assertTrue(callEffectStatisticBeanNotEmptyVarBoost(dispMoveEffStatis(feedDbMoveEffDataDamComp(withStatisVarRank(eff(Rate.one(), DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR, DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR), Statistic.SPEED,1)),0)));
    }
    @Test
    public void notEmptyVarBoost2() {
        assertFalse(callEffectStatisticBeanNotEmptyVarBoost(dispMoveEffStatis(feedDbMoveEffDataDamComp(eff(Rate.one(), DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR, DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR)),0)));
    }
    @Test
    public void randomStatis1() {
        assertTrue(callEffectStatisticBeanRandomStatis(dispMoveEffStatis(feedDbMoveEffDataDamComp(withLawBoost(eff(Rate.one(), DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR, DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR),Statistic.SPEED, LgInt.one())),0)));
    }
    @Test
    public void randomStatis2() {
        assertFalse(callEffectStatisticBeanRandomStatis(dispMoveEffStatis(feedDbMoveEffDataDamComp(eff(Rate.one(), DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR, DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR)),0)));
    }
}
