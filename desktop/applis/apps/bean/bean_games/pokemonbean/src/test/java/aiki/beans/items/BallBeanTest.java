package aiki.beans.items;

import aiki.game.fight.Fight;
import org.junit.Test;

public final class BallBeanTest extends InitDbItemOther {
    @Test
    public void getCatchingRate() {
        assertEq(Fight.TEMPS_TOUR,callBallBeanCatchingRateGet());
    }
    @Test
    public void getMapVars1() {
        assertSizeEq(1,callBallBeanMapVarsGet());
    }
    @Test
    public void getMapVars2() {
        assertEq(Fight.TEMPS_TOUR,first(elt(callBallBeanMapVarsGet(),0)));
    }
    @Test
    public void getMapVars3() {
        assertEq(TIME,second(elt(callBallBeanMapVarsGet(),0)));
    }
}
