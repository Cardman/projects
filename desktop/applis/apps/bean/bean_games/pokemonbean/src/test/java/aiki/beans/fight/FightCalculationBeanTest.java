package aiki.beans.fight;

import aiki.beans.InitDbFight;
import org.junit.Test;

public final class FightCalculationBeanTest extends InitDbFight {
    @Test
    public void damage1() {
        assertSizeEq(256, callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation(dbBaseCalc()))));
    }
    @Test
    public void damage2() {
        assertSizeEq(256, callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation2(dbBaseCalc()))));
    }
    @Test
    public void damage3() {
        assertSizeEq(384, callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation3(dbBaseCalc()))));
    }
    @Test
    public void damage4() {
        assertSizeEq(384, callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation4(dbBaseCalc()))));
    }
    @Test
    public void damage5() {
        assertSizeEq(4, callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))));
    }
    @Test
    public void damage6() {
        assertSizeEq(256, callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation6(dbBaseCalcRoque()))));
    }
    @Test
    public void tmp1() {
        assertEq(0,callKeyHypothesisGetNumberPlayer(elt(callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation(dbBaseCalc()))),0)));
    }
    @Test
    public void tmp2() {
        assertEq(0,callKeyHypothesisGetNumberTarget(elt(callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation(dbBaseCalc()))),0)));
    }
}
