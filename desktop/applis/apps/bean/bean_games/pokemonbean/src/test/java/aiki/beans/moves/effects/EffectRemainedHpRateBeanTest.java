package aiki.beans.moves.effects;

import code.maths.Rate;
import org.junit.Test;

public final class EffectRemainedHpRateBeanTest extends InitDbMoveEffectOther {
    @Test
    public void getWinHp1() {
        assertTrue(callEffectRemainedHpRateBeanWinHpGet(dispMoveEffRemainedHpRate(Rate.one())));
    }
    @Test
    public void getWinHp2() {
        assertFalse(callEffectRemainedHpRateBeanWinHpGet(dispMoveEffRemainedHpRate(Rate.minusOne())));
    }
    @Test
    public void getRateHp() {
        assertEq(Rate.one(),callEffectRemainedHpRateBeanRateHpGet(dispMoveEffRemainedHpRate(Rate.one())));
    }
}
