package aiki.beans.moves;

import aiki.beans.pokemon.AikiBeansPokemonStd;
import aiki.db.DataBase;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.game.fight.Fight;
import org.junit.Test;

public final class MoveBeanTest extends InitDbMove {
    @Test
    public void name1() {
        assertEq(M_DAM_TR,callMoveBeanDisplayNameGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void name2() {
        assertEq(M_STA_TR,callMoveBeanDisplayNameGet(dispMove(feedDbMoveStaStatis(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true),1)));
    }
    @Test
    public void endRound1() {
        assertFalse(callMoveBeanIsEndRoundEffect(dispMove(feedDbMoveStaStatis(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true),1),0));
    }
    @Test
    public void endRound2() {
        assertTrue(callMoveBeanIsEndRoundEffect(dispMove(feedDbMoveStaEndRound(TargetChoice.LANCEUR,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, 1, 1),1),0));
    }
    @Test
    public void hasDef1() {
        assertFalse(callMoveBeanHasDefaultTypesGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void hasDef2() {
        assertTrue(callMoveBeanHasDefaultTypesGet(dispMove(feedDbMoveDamDefType(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void ignVarAccurUserNeg1() {
        assertFalse(callMoveBeanIgnVarAccurUserNegGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,false,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void ignVarAccurUserNeg2() {
        assertTrue(callMoveBeanIgnVarAccurUserNegGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void ignVarEvasTargetPos1() {
        assertFalse(callMoveBeanIgnVarEvasTargetPosGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,false,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void ignVarEvasTargetPos2() {
        assertTrue(callMoveBeanIgnVarEvasTargetPosGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void disappearBeforeUse1() {
        assertFalse(callMoveBeanDisappearBeforeUseGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,false,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void disappearBeforeUse2() {
        assertTrue(callMoveBeanDisappearBeforeUseGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void cannotKo1() {
        assertFalse(callMoveBeanCannotKoGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,false,true,"1"),0)));
    }
    @Test
    public void cannotKo2() {
        assertTrue(callMoveBeanCannotKoGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void secEffectIfNoDamage1() {
        assertFalse(callMoveBeanSecEffectIfNoDamageGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,false,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void secEffectIfNoDamage2() {
        assertTrue(callMoveBeanSecEffectIfNoDamageGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void reacharge1() {
        assertFalse(callMoveBeanRechargeRoundGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,false,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void reacharge2() {
        assertTrue(callMoveBeanRechargeRoundGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void user1() {
        assertFalse(callMoveBeanConstUserChoiceGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,false,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void user2() {
        assertTrue(callMoveBeanConstUserChoiceGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void dam1() {
        assertTrue(callMoveBeanIsDamagingMove(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void dam2() {
        assertFalse(callMoveBeanIsDamagingMove(dispMove(feedDbMoveStaStatis(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true),1)));
    }
    @Test
    public void direct1() {
        assertFalse(callMoveBeanIsDamagingDirectMove(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,false,"1"),0)));
    }
    @Test
    public void direct2() {
        assertTrue(callMoveBeanIsDamagingDirectMove(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void cstAcc1() {
        assertFalse(callMoveBeanIsConstAccuracy(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE, DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR, SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void cstAcc2() {
        assertTrue(callMoveBeanIsConstAccuracy(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isZeroPriority1() {
        assertFalse(callMoveBeanIsZeroPriority(dispMove(feedDbMoveStaEndRound(TargetChoice.LANCEUR,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, 1, 1),1)));
    }
    @Test
    public void isZeroPriority2() {
        assertTrue(callMoveBeanIsZeroPriority(dispMove(feedDbMoveStaEndRound(TargetChoice.LANCEUR,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, 1, 0),1)));
    }
    @Test
    public void isZeroPrepaRound1() {
        assertFalse(callMoveBeanIsZeroPrepaRound(dispMove(feedDbMoveStaEndRound(TargetChoice.LANCEUR,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, 1, 1),1)));
    }
    @Test
    public void isZeroPrepaRound2() {
        assertTrue(callMoveBeanIsZeroPrepaRound(dispMove(feedDbMoveStaEndRound(TargetChoice.LANCEUR,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, 0, 1),1)));
    }
    @Test
    public void isRepeatedRound1() {
        assertFalse(callMoveBeanIsRepeatedRound(dispMove(feedDbMoveDamNonRepeated(TargetChoice.LANCEUR,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),0)));
    }
    @Test
    public void isRepeatedRound2() {
        assertTrue(callMoveBeanIsRepeatedRound(dispMove(feedDbMoveDam(TargetChoice.LANCEUR,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),0)));
    }
    @Test
    public void isWeather1() {
        assertFalse(callMoveBeanIsWeather(dispMove(feedDbMoveDamDefType(TargetChoice.LANCEUR,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),0),0));
    }
    @Test
    public void isWeather2() {
        assertTrue(callMoveBeanIsWeather(dispMove(feedDbMoveDamDefType(TargetChoice.LANCEUR,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),0),1));
    }
    @Test
    public void isItem1() {
        assertFalse(callMoveBeanIsItem(dispMove(feedDbMoveDamDefTypeIt(TargetChoice.LANCEUR,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),0),0));
    }
    @Test
    public void isItem2() {
        assertTrue(callMoveBeanIsItem(dispMove(feedDbMoveDamDefTypeIt(TargetChoice.LANCEUR,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),0),1));
    }
    @Test
    public void isItem3() {
        assertTrue(callMoveBeanIsItem(dispMove(feedDbMoveDamDefTypeItWeather(TargetChoice.LANCEUR,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),0),0));
    }
    @Test
    public void typesDependOnWeatherAndItem1() {
        assertFalse(callMoveBeanTypesDependOnWeatherAndItem(dispMove(feedDbMoveDamDefTypeNo(TargetChoice.LANCEUR,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),0)));
    }
    @Test
    public void typesDependOnWeatherAndItem2() {
        assertFalse(callMoveBeanTypesDependOnWeatherAndItem(dispMove(feedDbMoveDamDefTypeNoItem(TargetChoice.LANCEUR,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),0)));
    }
    @Test
    public void typesDependOnWeatherAndItem3() {
        assertFalse(callMoveBeanTypesDependOnWeatherAndItem(dispMove(feedDbMoveDamDefTypeIt(TargetChoice.LANCEUR,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),0)));
    }
    @Test
    public void typesDependOnWeatherAndItem4() {
        assertTrue(callMoveBeanTypesDependOnWeatherAndItem(dispMove(feedDbMoveDamDefTypeItWeather(TargetChoice.LANCEUR,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),0)));
    }
    @Test
    public void typesDependOnlyOnItem1() {
        assertFalse(callMoveBeanTypesDependOnlyOnItem(dispMove(feedDbMoveDamDefTypeNo(TargetChoice.LANCEUR,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),0)));
    }
    @Test
    public void typesDependOnlyOnItem2() {
        assertFalse(callMoveBeanTypesDependOnlyOnItem(dispMove(feedDbMoveDamDefTypeNoItem(TargetChoice.LANCEUR,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),0)));
    }
    @Test
    public void typesDependOnlyOnItem3() {
        assertTrue(callMoveBeanTypesDependOnlyOnItem(dispMove(feedDbMoveDamDefTypeIt(TargetChoice.LANCEUR,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),0)));
    }
    @Test
    public void typesDependOnlyOnItem4() {
        assertFalse(callMoveBeanTypesDependOnlyOnItem(dispMove(feedDbMoveDamDefTypeItWeather(TargetChoice.LANCEUR,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),0)));
    }
    @Test
    public void typesDependOnlyOnWeather1() {
        assertFalse(callMoveBeanTypesDependOnlyOnWeather(dispMove(feedDbMoveDamDefTypeNo(TargetChoice.LANCEUR,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),0)));
    }
    @Test
    public void typesDependOnlyOnWeather2() {
        assertTrue(callMoveBeanTypesDependOnlyOnWeather(dispMove(feedDbMoveDamDefTypeNoItem(TargetChoice.LANCEUR,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),0)));
    }
    @Test
    public void typesDependOnlyOnWeather3() {
        assertFalse(callMoveBeanTypesDependOnlyOnWeather(dispMove(feedDbMoveDamDefTypeIt(TargetChoice.LANCEUR,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),0)));
    }
    @Test
    public void typesDependOnlyOnWeather4() {
        assertFalse(callMoveBeanTypesDependOnlyOnWeather(dispMove(feedDbMoveDamDefTypeItWeather(TargetChoice.LANCEUR,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),0)));
    }
    @Test
    public void isBeforePrimaryEffect1() {
        assertFalse(callMoveBeanIsBeforePrimaryEffect(dispMove(feedDbMoveDamBig(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),0),1));
    }
    @Test
    public void isBeforePrimaryEffect2() {
        assertTrue(callMoveBeanIsBeforePrimaryEffect(dispMove(feedDbMoveDamBig(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),0),0));
    }
    @Test
    public void isPrimaryEffect1() {
        assertFalse(callMoveBeanIsPrimaryEffect(dispMove(feedDbMoveDamBig(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),0),2));
    }
    @Test
    public void isPrimaryEffect2() {
        assertTrue(callMoveBeanIsPrimaryEffect(dispMove(feedDbMoveDamBig(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),0),1));
    }
    @Test
    public void isAfterPrimaryEffect1() {
        assertFalse(callMoveBeanIsAfterPrimaryEffect(dispMove(feedDbMoveDamBig(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),0),1));
    }
    @Test
    public void isAfterPrimaryEffect2() {
        assertTrue(callMoveBeanIsAfterPrimaryEffect(dispMove(feedDbMoveDamBig(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),0),2));
    }
    @Test
    public void effPrimOrBeforeNotEndRound1() {
        assertTrue(callMoveBeanEffPrimOrBeforeNotEndRound(dispMove(feedDbMoveDamBig(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),0),0));
    }
    @Test
    public void effPrimOrBeforeNotEndRound2() {
        assertFalse(callMoveBeanEffPrimOrBeforeNotEndRound(dispMove(feedDbMoveDamBig(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),0),3));
    }
    @Test
    public void effPrimOrBeforeNotEndRound3() {
        assertFalse(callMoveBeanEffPrimOrBeforeNotEndRound(dispMove(feedDbMoveStaEndRound(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, 1,1),1),0));
    }
    @Test
    public void effPrimOrBeforeNotEndRound4() {
        assertFalse(callMoveBeanEffPrimOrBeforeNotEndRound(dispMove(feedDbMoveDamBig(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),0),2));
    }
    @Test
    public void effSecNotEndRound1() {
        assertTrue(callMoveBeanSecNotEndRound(dispMove(feedDbMoveDamBig(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),0),2));
    }
    @Test
    public void effSecNotEndRound2() {
        assertFalse(callMoveBeanSecNotEndRound(dispMove(feedDbMoveStaEndRound(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, 1,1),1),0));
    }
    @Test
    public void effSecNotEndRound3() {
        assertFalse(callMoveBeanSecNotEndRound(dispMove(feedDbMoveDamBig(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),0),3));
    }
    @Test
    public void effSecNotEndRound4() {
        assertFalse(callMoveBeanSecNotEndRound(dispMove(feedDbMoveDamBig(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),0),1));
    }
    @Test
    public void canBeLearnt1() {
        assertTrue(callMoveBeanCanBeLearnt(dispMove(feedDbMoveDamFullLearn(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1",1,1),0)));
    }
    @Test
    public void canBeLearnt2() {
        assertTrue(callMoveBeanCanBeLearnt(dispMove(feedDbMoveDamFullLearn(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1",1,1),1)));
    }
    @Test
    public void canBeLearnt3() {
        assertTrue(callMoveBeanCanBeLearnt(dispMove(feedDbMoveDamFullLearn(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1",2,2),2)));
    }
    @Test
    public void canBeLearnt4() {
        assertTrue(callMoveBeanCanBeLearnt(dispMove(feedDbMoveDamFullLearn(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1",2,2),3)));
    }
    @Test
    public void canBeLearnt5() {
        assertFalse(callMoveBeanCanBeLearnt(dispMove(feedDbMoveDamFullLearn(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1",1,1),4)));
    }
    @Test
    public void canBeLearnt6() {
        assertFalse(callMoveBeanCanBeLearnt(dispMove(feedDbMoveDamFullLearn(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1",2,2),0)));
    }
    @Test
    public void canBeLearnt7() {
        assertFalse(callMoveBeanCanBeLearnt(dispMove(feedDbMoveDamFullLearn(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1",2,2),1)));
    }
    @Test
    public void getMovesLevelLearntByPokemon1() {
        assertSizeEq(1,callMoveBeanMovesLevelLearntByPokemonGet(dispMove(feedDbMoveDamTwo(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),1)));
    }
    @Test
    public void getMovesLevelLearntByPokemon2() {
        assertEq(3,first(elt(callMoveBeanMovesLevelLearntByPokemonGet(dispMove(feedDbMoveDamTwo(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),1)),0)));
    }
    @Test
    public void getMovesLevelLearntByPokemon3() {
        assertEq(1,first(elt(callMoveBeanMovesLevelLearntByPokemonGet(dispMove(feedDbMoveDamTwo(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),0)),0)));
    }
    @Test
    public void getMovesLevelLearntByPokemon4() {
        assertSizeEq(2,second(elt(callMoveBeanMovesLevelLearntByPokemonGet(dispMove(feedDbMoveDamTwo(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),1)),0)));
    }
    @Test
    public void getMovesLevelLearntByPokemon5() {
        assertEq(P_PIKA,elt(second(elt(callMoveBeanMovesLevelLearntByPokemonGet(dispMove(feedDbMoveDamTwo(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),1)),0)),0));
    }
    @Test
    public void getMovesLevelLearntByPokemon6() {
        assertEq(P_POKEMON,elt(second(elt(callMoveBeanMovesLevelLearntByPokemonGet(dispMove(feedDbMoveDamTwo(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),1)),0)),1));
    }
    @Test
    public void getTrPokemon1() {
        assertEq(P_PIKA_TR,callMoveBeanGetTrPokemon(dispMove(feedDbMoveDamTwo(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),1),0,0));
    }
    @Test
    public void getTrPokemon2() {
        assertEq(P_POKEMON_TR,callMoveBeanGetTrPokemon(dispMove(feedDbMoveDamTwo(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),1),0,1));
    }
    @Test
    public void clickPokemon1() {
        assertEq(AikiBeansPokemonStd.WEB_HTML_POKEMON_DATA_HTML,callMoveBeanClickPokemon(dispMove(feedDbMoveDamTwo(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),1),0,0));
    }
    @Test
    public void clickPokemon2() {
        assertEq(AikiBeansPokemonStd.WEB_HTML_POKEMON_DATA_HTML,callMoveBeanClickPokemon(dispMove(feedDbMoveDamTwo(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),1),0,1));
    }
    @Test
    public void clickPokemon3() {
        assertEq(P_PIKA,callMoveBeanClickPokemonId(dispMove(feedDbMoveDamTwo(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),1),0,0));
    }
    @Test
    public void clickPokemon4() {
        assertEq(P_POKEMON,callMoveBeanClickPokemonId(dispMove(feedDbMoveDamTwo(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),1),0,1));
    }
}
