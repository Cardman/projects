package aiki.beans.moves.effects;

import code.maths.Rate;
import org.junit.Test;

public final class EffectMultMovePowerBeanTest extends InitDbMoveEffectOther {
    @Test
    public void getMultMovePowerFctType1() {
        assertSizeEq(1,callEffectMultUsedMovePowerBeanMultMovePowerFctTypeGet(dispMoveEffMultMovePower(0)));
    }
    @Test
    public void getMultMovePowerFctType2() {
        assertEq(T_TYPE1,first(elt(callEffectMultUsedMovePowerBeanMultMovePowerFctTypeGet(dispMoveEffMultMovePower(0)),0)));
    }
    @Test
    public void getMultMovePowerFctType3() {
        assertEq(Rate.one(),second(elt(callEffectMultUsedMovePowerBeanMultMovePowerFctTypeGet(dispMoveEffMultMovePower(0)),0)));
    }
    @Test
    public void getMultMovePowerFctType4() {
        assertSizeEq(1,callEffectMultSufferedMovePowerBeanMultMovePowerFctTypeGet(dispMoveEffMultMovePower(1)));
    }
    @Test
    public void getMultMovePowerFctType5() {
        assertEq(T_TYPE2,first(elt(callEffectMultSufferedMovePowerBeanMultMovePowerFctTypeGet(dispMoveEffMultMovePower(1)),0)));
    }
    @Test
    public void getMultMovePowerFctType6() {
        assertEq(Rate.newRate("2"),second(elt(callEffectMultSufferedMovePowerBeanMultMovePowerFctTypeGet(dispMoveEffMultMovePower(1)),0)));
    }
    @Test
    public void getTrType1() {
        assertEq(T_TYPE1_TR,callEffectMultUsedMovePowerBeanGetTrType(dispMoveEffMultMovePower(0),0));
    }
    @Test
    public void getTrType2() {
        assertEq(T_TYPE2_TR,callEffectMultSufferedMovePowerBeanGetTrType(dispMoveEffMultMovePower(1),0));
    }
}
