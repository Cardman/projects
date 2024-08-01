package aiki.beans.items;

import aiki.db.DataBase;
import org.junit.Test;

public final class BallBeanTest extends InitDbItemOther {
    @Test
    public void getCatchingRate() {
        assertEq(DataBase.DEF_TEMPS_TOUR,callBallBeanCatchingRateGet());
    }
    @Test
    public void getMapVars1() {
        assertSizeEq(1,callBallBeanMapVarsGet());
    }
    @Test
    public void getMapVars2() {
        assertEq(DataBase.DEF_TEMPS_TOUR,first(elt(callBallBeanMapVarsGet(),0)));
    }
    @Test
    public void getMapVars3() {
        assertEq(TIME,second(elt(callBallBeanMapVarsGet(),0)));
    }
}
