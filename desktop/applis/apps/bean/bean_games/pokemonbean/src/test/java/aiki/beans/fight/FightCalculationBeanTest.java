package aiki.beans.fight;

import aiki.beans.InitDbFight;
import code.maths.Rate;
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
    @Test
    public void playerName1() {
        assertEq(PIKACHU_TR+" "+0,callKeyHypothesisGetPlayerPokemon(elt(callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),0)));
    }
    @Test
    public void playerName2() {
        assertEq(PIKACHU_TR+" "+0,callKeyHypothesisGetPlayerPokemon(elt(callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),1)));
    }
    @Test
    public void playerName3() {
        assertEq(PIKACHU_TR+" "+0,callKeyHypothesisGetPlayerPokemon(elt(callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),2)));
    }
    @Test
    public void playerName4() {
        assertEq(PIKACHU_TR+" "+0,callKeyHypothesisGetPlayerPokemon(elt(callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),3)));
    }
    @Test
    public void moveName1() {
        assertEq(CHARGE_TR2,callKeyHypothesisGetMove(elt(callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),0)));
    }
    @Test
    public void moveName2() {
        assertEq(CHARGE_TR2,callKeyHypothesisGetMove(elt(callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),1)));
    }
    @Test
    public void moveName3() {
        assertEq(CHARGE_TR,callKeyHypothesisGetMove(elt(callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),2)));
    }
    @Test
    public void moveName4() {
        assertEq(CHARGE_TR,callKeyHypothesisGetMove(elt(callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),3)));
    }
    @Test
    public void targetName1() {
        assertEq(PIKACHU_TR+" "+0,callKeyHypothesisGetTargetPokemon(elt(callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),0)));
    }
    @Test
    public void targetName2() {
        assertEq(PIKACHU_TR+" "+0,callKeyHypothesisGetTargetPokemon(elt(callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),1)));
    }
    @Test
    public void targetName3() {
        assertEq(PIKACHU_TR+" "+0,callKeyHypothesisGetTargetPokemon(elt(callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),2)));
    }
    @Test
    public void targetName4() {
        assertEq(PIKACHU_TR+" "+0,callKeyHypothesisGetTargetPokemon(elt(callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),3)));
    }
    @Test
    public void targetTeam1() {
        assertFalse(callKeyHypothesisIsBelongsToUser(elt(callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),0)));
    }
    @Test
    public void targetTeam2() {
        assertTrue(callKeyHypothesisIsBelongsToUser(elt(callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),1)));
    }
    @Test
    public void targetTeam3() {
        assertFalse(callKeyHypothesisIsBelongsToUser(elt(callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),2)));
    }
    @Test
    public void targetTeam4() {
        assertTrue(callKeyHypothesisIsBelongsToUser(elt(callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),3)));
    }
    @Test
    public void dam1() {
        assertEq(Rate.newRate("653/50"),callKeyHypothesisGetDamage(elt(callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),0)));
    }
    @Test
    public void dam2() {
        assertEq(Rate.newRate("1399/100"),callKeyHypothesisGetDamage(elt(callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),1)));
    }
    @Test
    public void dam3() {
        assertEq(Rate.newRate("653/50"),callKeyHypothesisGetDamage(elt(callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),2)));
    }
    @Test
    public void dam4() {
        assertEq(Rate.newRate("1399/100"),callKeyHypothesisGetDamage(elt(callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),3)));
    }
    @Test
    public void damSec1() {
        assertEq(Rate.newRate("653/50"),callKeyHypothesisGetDamageSecond(elt(callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),0)));
    }
    @Test
    public void damSec2() {
        assertEq(Rate.newRate("1399/100"),callKeyHypothesisGetDamageSecond(elt(callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),1)));
    }
    @Test
    public void damSec3() {
        assertEq(Rate.newRate("653/50"),callKeyHypothesisGetDamageSecond(elt(callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),2)));
    }
    @Test
    public void damSec4() {
        assertEq(Rate.newRate("1399/100"),callKeyHypothesisGetDamageSecond(elt(callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),3)));
    }
}
