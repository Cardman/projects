package aiki.beans.fight;

import aiki.beans.InitDbFight;
import org.junit.Test;

public final class FightCalculationBeanTest extends InitDbFight {
    @Test
    public void damage1() {
        assertSizeEq(192, callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation(dbBaseCalc()))));
    }
    @Test
    public void damage2() {
        assertSizeEq(192, callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation2(dbBaseCalc()))));
    }
    @Test
    public void damage3() {
        assertSizeEq(288, callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation3(dbBaseCalc()))));
    }
    @Test
    public void damage4() {
        assertSizeEq(288, callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation4(dbBaseCalc()))));
    }
}
