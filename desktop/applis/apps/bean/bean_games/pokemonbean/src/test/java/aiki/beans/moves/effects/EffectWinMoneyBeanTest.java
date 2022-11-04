package aiki.beans.moves.effects;

import code.maths.Rate;
import org.junit.Test;

public final class EffectWinMoneyBeanTest extends InitDbMoveEffectOther {
    @Test
    public void win() {
        assertEq(Rate.one(),callEffectWinMoneyBeanWinningRateBySumTargetUserGet(dispMoveEffWinMoney()));
    }
}
