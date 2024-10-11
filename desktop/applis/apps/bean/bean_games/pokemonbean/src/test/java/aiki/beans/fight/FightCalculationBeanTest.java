package aiki.beans.fight;

import aiki.game.fight.Fighter;
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
    public void damage7() {
        assertSizeEq(4, callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation7(dbBaseCalc()))));
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
        assertEq(PIKA_2_TR +" "+0,callKeyHypothesisGetTargetPokemon(elt(callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),0)));
    }
    @Test
    public void targetName2() {
        assertEq(PIKACHU_TR+" "+0,callKeyHypothesisGetTargetPokemon(elt(callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),1)));
    }
    @Test
    public void targetName3() {
        assertEq(PIKA_2_TR +" "+0,callKeyHypothesisGetTargetPokemon(elt(callFightCalculationBeanDamageGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),2)));
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
    @Test
    public void getFoeChoices1() {
        assertSizeEq(4, callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation(dbBaseCalc()))));
    }
    @Test
    public void getFoeChoices2() {
        assertEq(0,first(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation(dbBaseCalc()))),0)));
    }
    @Test
    public void getFoeChoices3() {
        assertEq(M_TEAM_TR,callMoveTargetGetMove(second(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation(dbBaseCalc()))),0))));
    }
    @Test
    public void getFoeChoices4() {
        assertEq(Fighter.BACK, callMoveTargetGetTarget(second(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation(dbBaseCalc()))),0))));
    }
    @Test
    public void getFoeChoices5() {
        assertEq(1,first(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation(dbBaseCalc()))),1)));
    }
    @Test
    public void getFoeChoices6() {
        assertEq(M_TEAM_TR,callMoveTargetGetMove(second(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation(dbBaseCalc()))),1))));
    }
    @Test
    public void getFoeChoices7() {
        assertEq(Fighter.BACK, callMoveTargetGetTarget(second(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation(dbBaseCalc()))),1))));
    }
    @Test
    public void getFoeChoices8() {
        assertEq(2,first(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation(dbBaseCalc()))),2)));
    }
    @Test
    public void getFoeChoices9() {
        assertEq(M_TEAM_TR,callMoveTargetGetMove(second(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation(dbBaseCalc()))),2))));
    }
    @Test
    public void getFoeChoices10() {
        assertEq(Fighter.BACK, callMoveTargetGetTarget(second(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation(dbBaseCalc()))),2))));
    }
    @Test
    public void getFoeChoices11() {
        assertEq(3,first(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation(dbBaseCalc()))),3)));
    }
    @Test
    public void getFoeChoices12() {
        assertEq(M_TEAM_TR,callMoveTargetGetMove(second(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation(dbBaseCalc()))),3))));
    }
    @Test
    public void getFoeChoices13() {
        assertEq(Fighter.BACK, callMoveTargetGetTarget(second(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation(dbBaseCalc()))),3))));
    }
    @Test
    public void getFoeChoices14() {
        assertSizeEq(4, callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation2(dbBaseCalc()))));
    }
    @Test
    public void getFoeChoices15() {
        assertEq(0,first(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation2(dbBaseCalc()))),0)));
    }
    @Test
    public void getFoeChoices16() {
        assertEq(ECLAIR_TR,callMoveTargetGetMove(second(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation2(dbBaseCalc()))),0))));
    }
    @Test
    public void getFoeChoices17() {
        assertEq(0, callMoveTargetGetTarget(second(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation2(dbBaseCalc()))),0))));
    }
    @Test
    public void getFoeChoices18() {
        assertEq(1,first(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation2(dbBaseCalc()))),1)));
    }
    @Test
    public void getFoeChoices19() {
        assertEq(ECLAIR_TR,callMoveTargetGetMove(second(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation2(dbBaseCalc()))),1))));
    }
    @Test
    public void getFoeChoices20() {
        assertEq(1, callMoveTargetGetTarget(second(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation2(dbBaseCalc()))),1))));
    }
    @Test
    public void getFoeChoices21() {
        assertEq(2,first(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation2(dbBaseCalc()))),2)));
    }
    @Test
    public void getFoeChoices22() {
        assertEq(ECLAIR_TR,callMoveTargetGetMove(second(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation2(dbBaseCalc()))),2))));
    }
    @Test
    public void getFoeChoices23() {
        assertEq(2, callMoveTargetGetTarget(second(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation2(dbBaseCalc()))),2))));
    }
    @Test
    public void getFoeChoices24() {
        assertEq(3,first(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation2(dbBaseCalc()))),3)));
    }
    @Test
    public void getFoeChoices25() {
        assertEq(ECLAIR_TR,callMoveTargetGetMove(second(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation2(dbBaseCalc()))),3))));
    }
    @Test
    public void getFoeChoices26() {
        assertEq(3, callMoveTargetGetTarget(second(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation2(dbBaseCalc()))),3))));
    }
    @Test
    public void getFoeChoices27() {
        assertSizeEq(4, callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation6(dbBaseCalcRoque()))));
    }
    @Test
    public void getFoeChoices28() {
        assertEq(0,first(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation6(dbBaseCalcRoque()))),0)));
    }
    @Test
    public void getFoeChoices29() {
        assertEq(M_ROQUE_TR,callMoveTargetGetMove(second(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation6(dbBaseCalcRoque()))),0))));
    }
    @Test
    public void getFoeChoices30() {
        assertEq(1, callMoveTargetGetTarget(second(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation6(dbBaseCalcRoque()))),0))));
    }
    @Test
    public void getFoeChoices31() {
        assertEq(1,first(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation6(dbBaseCalcRoque()))),1)));
    }
    @Test
    public void getFoeChoices32() {
        assertEq(M_ROQUE_TR,callMoveTargetGetMove(second(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation6(dbBaseCalcRoque()))),1))));
    }
    @Test
    public void getFoeChoices33() {
        assertEq(0, callMoveTargetGetTarget(second(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation6(dbBaseCalcRoque()))),1))));
    }
    @Test
    public void getFoeChoices34() {
        assertEq(2,first(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation6(dbBaseCalcRoque()))),2)));
    }
    @Test
    public void getFoeChoices35() {
        assertEq(M_ROQUE_TR,callMoveTargetGetMove(second(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation6(dbBaseCalcRoque()))),2))));
    }
    @Test
    public void getFoeChoices36() {
        assertEq(1, callMoveTargetGetTarget(second(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation6(dbBaseCalcRoque()))),2))));
    }
    @Test
    public void getFoeChoices37() {
        assertEq(3,first(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation6(dbBaseCalcRoque()))),3)));
    }
    @Test
    public void getFoeChoices38() {
        assertEq(M_ROQUE_TR,callMoveTargetGetMove(second(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation6(dbBaseCalcRoque()))),3))));
    }
    @Test
    public void getFoeChoices39() {
        assertEq(2, callMoveTargetGetTarget(second(elt(callFightCalculationBeanFoeChoicesGet(beanFightCalculation(facadeCalculation6(dbBaseCalcRoque()))),3))));
    }

    @Test
    public void isChosenTarget1() {
        assertFalse(callFightCalculationBeanIsChosenTarget(beanFightCalculation(facadeCalculation(dbBaseCalc())),0));
    }
    @Test
    public void isChosenTarget2() {
        assertTrue(callFightCalculationBeanIsChosenTarget(beanFightCalculation(facadeCalculation2(dbBaseCalc())),0));
    }
    @Test
    public void isChosenTarget3() {
        assertTrue(callFightCalculationBeanIsChosenTarget(beanFightCalculation(facadeCalculation6(dbBaseCalcRoque())),0));
    }
    @Test
    public void isFoeTargetChTeam1() {
        assertFalse(callFightCalculationBeanIsFoeTargetChTeam(beanFightCalculation(facadeCalculation(dbBaseCalc())),0));
    }
    @Test
    public void isFoeTargetChTeam2() {
        assertFalse(callFightCalculationBeanIsFoeTargetChTeam(beanFightCalculation(facadeCalculation2(dbBaseCalc())),0));
    }
    @Test
    public void isFoeTargetChTeam3() {
        assertTrue(callFightCalculationBeanIsFoeTargetChTeam(beanFightCalculation(facadeCalculation6(dbBaseCalcRoque())),0));
    }
    @Test
    public void getFoeFighterName1() {
        assertEq(PIKACHU_TR,callFightCalculationBeanGetFoeFighterName(beanFightCalculation(facadeCalculation(dbBaseCalc())),0));
    }
    @Test
    public void getFoeFighterName2() {
        assertEq(PIKA_2_TR,callFightCalculationBeanGetFoeFighterName(beanFightCalculation(facadeCalculation2(dbBaseCalc())),1));
    }
    @Test
    public void getFoeFighterName3() {
        assertEq(PIKACHU_TR,callFightCalculationBeanGetFoeFighterName(beanFightCalculation(facadeCalculation6(dbBaseCalcRoque())),2));
    }
    @Test
    public void getFoeFighterName4() {
        assertEq(PIKA_2_TR,callFightCalculationBeanGetFoeFighterName(beanFightCalculation(facadeCalculation6(dbBaseCalcRoque())),3));
    }
    @Test
    public void getTargetNameFoeChoice1() {
        assertEq(PIKACHU_TR,callFightCalculationBeanGetTargetNameFoeChoice(beanFightCalculation(facadeCalculation2(dbBaseCalc())),0));
    }
    @Test
    public void getTargetNameFoeChoice2() {
        assertEq(PIKA_2_TR,callFightCalculationBeanGetTargetNameFoeChoice(beanFightCalculation(facadeCalculation2(dbBaseCalc())),1));
    }
    @Test
    public void getAllyChoice1() {
        assertSizeEq(4, callFightCalculationBeanAllyChoiceGet(beanFightCalculation(facadeCalculation3(dbBaseCalc()))));
    }
    @Test
    public void getAllyChoice2() {
        assertEq(CHARGE_TR2,callMoveTargetGetMove(first(elt(callFightCalculationBeanAllyChoiceGet(beanFightCalculation(facadeCalculation3(dbBaseCalc()))),0))));
    }
    @Test
    public void getAllyChoice3() {
        assertEq(ECLAIR_TR,callMoveTargetGetMove(second(elt(callFightCalculationBeanAllyChoiceGet(beanFightCalculation(facadeCalculation3(dbBaseCalc()))),0))));
    }
    @Test
    public void getAllyChoice4() {
        assertEq(0,callMoveTargetGetTarget(first(elt(callFightCalculationBeanAllyChoiceGet(beanFightCalculation(facadeCalculation3(dbBaseCalc()))),0))));
    }
    @Test
    public void getAllyChoice5() {
        assertEq(0,callMoveTargetGetTarget(second(elt(callFightCalculationBeanAllyChoiceGet(beanFightCalculation(facadeCalculation3(dbBaseCalc()))),0))));
    }
    @Test
    public void getAllyChoice6() {
        assertSizeEq(1, callFightCalculationBeanAllyChoiceGet(beanFightCalculation(facadeCalculation4(dbBaseCalc()))));
    }
    @Test
    public void getAllyChoice7() {
        assertEq(NULL_REF,callMoveTargetGetMove(first(elt(callFightCalculationBeanAllyChoiceGet(beanFightCalculation(facadeCalculation4(dbBaseCalc()))),0))));
    }
    @Test
    public void getAllyChoice8() {
        assertEq(NULL_REF,callMoveTargetGetMove(second(elt(callFightCalculationBeanAllyChoiceGet(beanFightCalculation(facadeCalculation4(dbBaseCalc()))),0))));
    }
    @Test
    public void getAllyChoice9() {
        assertEq(Fighter.BACK,callMoveTargetGetTarget(first(elt(callFightCalculationBeanAllyChoiceGet(beanFightCalculation(facadeCalculation4(dbBaseCalc()))),0))));
    }
    @Test
    public void getAllyChoice10() {
        assertEq(Fighter.BACK,callMoveTargetGetTarget(second(elt(callFightCalculationBeanAllyChoiceGet(beanFightCalculation(facadeCalculation4(dbBaseCalc()))),0))));
    }
    @Test
    public void issFoeTargetChoiceTeam1() {
        assertTrue(callFightCalculationBeanIsFoeTargetChoiceTeam(beanFightCalculation(facadeCalculation3(dbBaseCalc())),0));
    }
    @Test
    public void isFoeTargetTeam1() {
        assertTrue(callFightCalculationBeanIsFoeTargetTeam(beanFightCalculation(facadeCalculation3(dbBaseCalc())),0));
    }
    @Test
    public void isBackTargetChoiceTeam1() {
        assertFalse(callFightCalculationBeanIsBackTargetChoiceTeam(beanFightCalculation(facadeCalculation3(dbBaseCalc())),0));
    }
    @Test
    public void isBackTargetTeam1() {
        assertFalse(callFightCalculationBeanIsBackTargetTeam(beanFightCalculation(facadeCalculation3(dbBaseCalc())),0));
    }
    @Test
    public void issFoeTargetChoiceTeam2() {
        assertFalse(callFightCalculationBeanIsFoeTargetChoiceTeam(beanFightCalculation(facadeCalculation4(dbBaseCalc())),0));
    }
    @Test
    public void isFoeTargetTeam2() {
        assertFalse(callFightCalculationBeanIsFoeTargetTeam(beanFightCalculation(facadeCalculation4(dbBaseCalc())),0));
    }
    @Test
    public void isBackTargetChoiceTeam2() {
        assertTrue(callFightCalculationBeanIsBackTargetChoiceTeam(beanFightCalculation(facadeCalculation4(dbBaseCalc())),0));
    }
    @Test
    public void isBackTargetTeam2() {
        assertTrue(callFightCalculationBeanIsBackTargetTeam(beanFightCalculation(facadeCalculation4(dbBaseCalc())),0));
    }
    @Test
    public void getTargetNameAllyChoice() {
        assertEq(PIKACHU_TR,callFightCalculationBeanGetTargetNameAllyChoice(beanFightCalculation(facadeCalculation3(dbBaseCalc())),0));
    }
    @Test
    public void getTargetNameAllyChoiceCondition() {
        assertEq(PIKACHU_TR,callFightCalculationBeanGetTargetNameAllyChoiceCondition(beanFightCalculation(facadeCalculation3(dbBaseCalc())),0));
    }
    @Test
    public void sortedFightersWild1() {
        assertSizeEq(2, callFightCalculationBeanSortedFightersWildFightGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))));
    }

    @Test
    public void sortedFightersWild2() {
        assertEq(ECLAIR_TR, callFighterNamePkNameMvNameMvGet(elt(first(elt(callFightCalculationBeanSortedFightersWildFightGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),0)),0)));
    }
    @Test
    public void sortedFightersWild3() {
        assertSizeEq(1, second(elt(callFightCalculationBeanSortedFightersWildFightGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),0)));
    }
    @Test
    public void sortedFightersWild4() {
        assertEq(CHARGE_TR, callFighterNamePkNameMvNameMvGet(elt(first(elt(callFightCalculationBeanSortedFightersWildFightGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),1)),0)));
    }
    @Test
    public void sortedFightersWild5() {
        assertSizeEq(1, second(elt(callFightCalculationBeanSortedFightersWildFightGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),1)));
    }
    @Test
    public void sortedFightersWild6() {
        assertEq(PIKA_2_TR, callFightCalculationBeanGetFighterWildFight(beanFightCalculation(facadeCalculation5(dbBaseCalc())),0,0));
    }
    @Test
    public void sortedFightersWild7() {
        assertEq(PIKA_2_TR, callFightCalculationBeanGetFighterWildFight(beanFightCalculation(facadeCalculation5(dbBaseCalc())),1,0));
    }

    @Test
    public void sortedFightersWild8() {
        assertEq(one(IMG_MAX_RAI), callFighterNamePkNameMvNamePkGet(elt(first(elt(callFightCalculationBeanSortedFightersWildFightGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),0)),0)));
    }

    @Test
    public void sortedFightersWild9() {
        assertEq(one(IMG_MAX_RAI), callFighterNamePkNameMvNamePkGet(elt(first(elt(callFightCalculationBeanSortedFightersWildFightGet(beanFightCalculation(facadeCalculation5(dbBaseCalc()))),1)),0)));
    }

    @Test
    public void sortedFightersTrainer1() {
        assertSizeEq(1, callFightCalculationBeanSortedFightersGet(beanFightCalculation(facadeCalculation7(dbBaseCalc()))));
    }
    @Test
    public void sortedFightersTrainer2() {
        assertEq(PIKA_2_TR, callFightCalculationBeanGetFighter(beanFightCalculation(facadeCalculation7(dbBaseCalc())),0));
    }
}
