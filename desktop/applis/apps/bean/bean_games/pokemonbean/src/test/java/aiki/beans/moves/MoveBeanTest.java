package aiki.beans.moves;

import aiki.db.MessagesDataBaseConstants;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import code.maths.LgInt;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
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
        assertFalse(callMoveBeanIsConstAccuracy(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
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
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_DATA_HTML,callMoveBeanClickPokemon(dispMove(feedDbMoveDamTwo(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),1),0,0));
    }
    @Test
    public void clickPokemon2() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_DATA_HTML,callMoveBeanClickPokemon(dispMove(feedDbMoveDamTwo(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),1),0,1));
    }
    @Test
    public void clickPokemon3() {
        assertEq(P_PIKA,callMoveBeanClickPokemonId(dispMove(feedDbMoveDamTwo(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),1),0,0));
    }
    @Test
    public void clickPokemon4() {
        assertEq(P_POKEMON,callMoveBeanClickPokemonId(dispMove(feedDbMoveDamTwo(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),1),0,1));
    }
    @Test
    public void affectedByMoves1() {
        assertSizeEq(5,callMoveBeanAffectedByMovesGet(dispMove(feedDbMoveDamAffect(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),2)));
    }
    @Test
    public void affectedByMoves2() {
        assertSizeEq(1,callMoveBeanAffectedByMovesGet(dispMove(feedDbMoveDamAffect(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,false,false,false,false,true, true, "1"),2)));
    }
    @Test
    public void affectedByMoves3() {
        assertSizeEq(6,callMoveBeanAffectedByMovesGet(dispMove(feedDbMoveStaEndRoundAffect(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, 1, 1),5)));
    }
    @Test
    public void affectedByMoves4() {
        assertSizeEq(3,callMoveBeanAffectedByMovesGet(dispMove(feedDbMoveStaEndRoundAffect(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,false,false, 1, 0),5)));
    }
    @Test
    public void affectedByMoves5() {
        assertEq(M_DAM_BAD,elt(callMoveBeanAffectedByMovesGet(dispMove(feedDbMoveDamAffect(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),2)),0));
    }
    @Test
    public void affectedByMoves6() {
        assertEq(M_DAM_POW,elt(callMoveBeanAffectedByMovesGet(dispMove(feedDbMoveDamAffect(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),2)),1));
    }
    @Test
    public void affectedByMoves7() {
        assertEq(M_DAM_VERY_BAD,elt(callMoveBeanAffectedByMovesGet(dispMove(feedDbMoveDamAffect(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),2)),2));
    }
    @Test
    public void affectedByMoves8() {
        assertEq(M_STA,elt(callMoveBeanAffectedByMovesGet(dispMove(feedDbMoveDamAffect(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),2)),3));
    }
    @Test
    public void affectedByMoves9() {
        assertEq(M_WEA,elt(callMoveBeanAffectedByMovesGet(dispMove(feedDbMoveDamAffect(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, true, "1"),2)),4));
    }
    @Test
    public void affectedByMoves10() {
        assertEq(M_DAM_POW,elt(callMoveBeanAffectedByMovesGet(dispMove(feedDbMoveDamAffect(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,false,false,false,false,true, true, "1"),2)),0));
    }
    @Test
    public void affectedByMoves11() {
        assertEq(M_DAM_BAD,elt(callMoveBeanAffectedByMovesGet(dispMove(feedDbMoveStaEndRoundAffect(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, 1, 1),5)),0));
    }
    @Test
    public void affectedByMoves12() {
        assertEq(M_DAM_POW,elt(callMoveBeanAffectedByMovesGet(dispMove(feedDbMoveStaEndRoundAffect(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, 1, 1),5)),1));
    }
    @Test
    public void affectedByMoves13() {
        assertEq(M_DAM,elt(callMoveBeanAffectedByMovesGet(dispMove(feedDbMoveStaEndRoundAffect(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, 1, 1),5)),2));
    }
    @Test
    public void affectedByMoves14() {
        assertEq(M_DAM_VAR,elt(callMoveBeanAffectedByMovesGet(dispMove(feedDbMoveStaEndRoundAffect(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, 1, 1),5)),3));
    }
    @Test
    public void affectedByMoves15() {
        assertEq(M_DAM_VERY_BAD,elt(callMoveBeanAffectedByMovesGet(dispMove(feedDbMoveStaEndRoundAffect(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, 1, 1),5)),4));
    }
    @Test
    public void affectedByMoves16() {
        assertEq(M_WEA,elt(callMoveBeanAffectedByMovesGet(dispMove(feedDbMoveStaEndRoundAffect(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true, 1, 1),5)),5));
    }
    @Test
    public void affectedByMoves17() {
        assertEq(M_DAM,elt(callMoveBeanAffectedByMovesGet(dispMove(feedDbMoveStaEndRoundAffect(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,false,false, 1, 0),5)),0));
    }
    @Test
    public void affectedByMoves18() {
        assertEq(M_DAM_VAR,elt(callMoveBeanAffectedByMovesGet(dispMove(feedDbMoveStaEndRoundAffect(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,false,false, 1, 0),5)),1));
    }
    @Test
    public void affectedByMoves19() {
        assertEq(M_WEA,elt(callMoveBeanAffectedByMovesGet(dispMove(feedDbMoveStaEndRoundAffect(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,false,false, 1, 0),5)),2));
    }
    @Test
    public void abilities1() {
        assertSizeEq(1,callMoveBeanAbilitiesGet(dispMove(feedDbMoveDamAb(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void abilities2() {
        assertEq(A_ABILITY,elt(callMoveBeanAbilitiesGet(dispMove(feedDbMoveDamAb(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)),0));
    }
    @Test
    public void items1() {
        assertSizeEq(0,callMoveBeanItemsGet(dispMove(feedDbMoveDamItBatNot(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void items2() {
        assertSizeEq(1,callMoveBeanItemsGet(dispMove(feedDbMoveDamItBat(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void items3() {
        assertEq(I_ITEM,elt(callMoveBeanItemsGet(dispMove(feedDbMoveDamItBat(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)),0));
    }
    @Test
    public void accVar1() {
        assertSizeEq(1,callMoveBeanMapVarsAccuracyGet(dispMove(feedDbMoveDamComp(TargetChoice.ANY_FOE, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void accVar2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,first(elt(callMoveBeanMapVarsAccuracyGet(dispMove(feedDbMoveDamComp(TargetChoice.ANY_FOE, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)),0)));
    }
    @Test
    public void accVar3() {
        assertEq(TIME,second(elt(callMoveBeanMapVarsAccuracyGet(dispMove(feedDbMoveDamComp(TargetChoice.ANY_FOE, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)),0)));
    }
    @Test
    public void switchAfterUsingMove1() {
        assertFalse(callMoveBeanSwitchAfterUsingMove(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.CIBLE,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void switchAfterUsingMove2() {
        assertTrue(callMoveBeanSwitchAfterUsingMove(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.LANCEUR,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isAdjAdv1() {
        assertFalse(callMoveBeanIsAdjAdv(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isAdjAdv2() {
        assertTrue(callMoveBeanIsAdjAdv(dispMove(feedDbMoveDam(TargetChoice.ADJ_ADV,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isAdjMult1() {
        assertFalse(callMoveBeanIsAdjMult(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isAdjMult2() {
        assertTrue(callMoveBeanIsAdjMult(dispMove(feedDbMoveDam(TargetChoice.ADJ_MULT,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isAdjUniq1() {
        assertFalse(callMoveBeanIsAdjUniq(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isAdjUniq2() {
        assertTrue(callMoveBeanIsAdjUniq(dispMove(feedDbMoveDam(TargetChoice.ADJ_UNIQ,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isAllie1() {
        assertFalse(callMoveBeanIsAllie(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isAllie2() {
        assertTrue(callMoveBeanIsAllie(dispMove(feedDbMoveDam(TargetChoice.ALLIE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isAllies1() {
        assertFalse(callMoveBeanIsAllies(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isAllies2() {
        assertTrue(callMoveBeanIsAllies(dispMove(feedDbMoveDam(TargetChoice.ALLIES,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isAnyFoe1() {
        assertFalse(callMoveBeanIsAnyFoe(dispMove(feedDbMoveDam(TargetChoice.ALLIES,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isAnyFoe2() {
        assertTrue(callMoveBeanIsAnyFoe(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isAutreUniq1() {
        assertFalse(callMoveBeanIsAutreUniq(dispMove(feedDbMoveDam(TargetChoice.ALLIES,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isAutreUniq2() {
        assertTrue(callMoveBeanIsAutreUniq(dispMove(feedDbMoveDam(TargetChoice.AUTRE_UNIQ,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isGlobale1() {
        assertFalse(callMoveBeanIsGlobale(dispMove(feedDbMoveDam(TargetChoice.ALLIES,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isGlobale2() {
        assertTrue(callMoveBeanIsGlobale(dispMove(feedDbMoveDam(TargetChoice.GLOBALE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isLanceur1() {
        assertFalse(callMoveBeanIsLanceur(dispMove(feedDbMoveDam(TargetChoice.ALLIES,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isLanceur2() {
        assertTrue(callMoveBeanIsLanceur(dispMove(feedDbMoveDam(TargetChoice.LANCEUR,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isPseudoGlobale1() {
        assertFalse(callMoveBeanIsPseudoGlobale(dispMove(feedDbMoveDam(TargetChoice.ALLIES,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isPseudoGlobale2() {
        assertTrue(callMoveBeanIsPseudoGlobale(dispMove(feedDbMoveDam(TargetChoice.PSEUDO_GLOBALE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isTousAdv1() {
        assertFalse(callMoveBeanIsTousAdv(dispMove(feedDbMoveDam(TargetChoice.ALLIES,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isTousAdv2() {
        assertTrue(callMoveBeanIsTousAdv(dispMove(feedDbMoveDam(TargetChoice.TOUS_ADV,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isUniqueImporte1() {
        assertFalse(callMoveBeanIsUniqueImporte(dispMove(feedDbMoveDam(TargetChoice.ALLIES,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void isUniqueImporte2() {
        assertTrue(callMoveBeanIsUniqueImporte(dispMove(feedDbMoveDam(TargetChoice.UNIQUE_IMPORTE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void key() {
        assertEq(M_DAM,callMoveBeanNameGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void cat() {
        assertEq(C_CAT1_TR,callMoveBeanCategoryGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void pp() {
        assertEq(1,callMoveBeanPpGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void prio() {
        assertEq(1,callMoveBeanPriorityGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void acc() {
        assertEq("1",callMoveBeanAccuracyGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void prepa() {
        assertEq(1,callMoveBeanNbPrepaRoundGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void rk() {
        assertEq(1,callMoveBeanRankIncrementNbRoundGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,1,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void boost1() {
        assertSizeEq(1,callMoveBeanBoostedTypesGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,1,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void boost2() {
        assertEq(T_TYPE1_TR,elt(callMoveBeanBoostedTypesGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,1,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)),0));
    }
    @Test
    public void types1() {
        assertSizeEq(1,callMoveBeanTypesGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,1,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void types2() {
        assertEq(T_TYPE1_TR,elt(callMoveBeanTypesGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,1,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)),0));
    }
    @Test
    public void deletedStatus1() {
        assertSizeEq(1,callMoveBeanDeletedStatusGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,1,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void deletedStatus2() {
        assertEq(S_STA_SIM,elt(callMoveBeanDeletedStatusGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,1,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)),0));
    }
    @Test
    public void requiredStatus1() {
        assertSizeEq(1,callMoveBeanRequiredStatusGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,1,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void requiredStatus2() {
        assertEq(S_STA_REL,elt(callMoveBeanRequiredStatusGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,1,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)),0));
    }
    @Test
    public void achieveDisappearedPkUsingMove1() {
        assertSizeEq(1,callMoveBeanAchieveDisappearedPkUsingMoveGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,1,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void achieveDisappearedPkUsingMove2() {
        assertEq(M_STA,elt(callMoveBeanAchieveDisappearedPkUsingMoveGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,1,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)),0));
    }
    @Test
    public void getEffects() {
        assertSizeEq(1,callMoveBeanEffectsGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,1,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void getRepeatRoundLaw1() {
        assertSizeEq(1,callMoveBeanRepeatRoundLawGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,1,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void getRepeatRoundLaw2() {
        assertEq(LgInt.one(),first(elt(callMoveBeanRepeatRoundLawGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,1,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)),0)));
    }
    @Test
    public void getRepeatRoundLaw3() {
        assertEq(Rate.one(),second(elt(callMoveBeanRepeatRoundLawGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,1,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)),0)));
    }
    @Test
    public void getTrAchieveDisappearedPkUsingMove() {
        assertEq(M_STA_TR,callMoveBeanGetTrAchieveDisappearedPkUsingMove(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,1,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0),0));
    }
    @Test
    public void clickAchieveDisappearedPkUsingMove1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callMoveBeanClickAchieveDisappearedPkUsingMove(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,1,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0),0));
    }
    @Test
    public void clickAchieveDisappearedPkUsingMove2() {
        assertEq(M_STA,callMoveBeanClickAchieveDisappearedPkUsingMoveId(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,1,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0),0));
    }
    @Test
    public void getDeletedStatus() {
        assertEq(S_STA_SIM_TR,callMoveBeanGetDeletedStatus(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,1,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0),0));
    }
    @Test
    public void clickDeletedStatus1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML,callMoveBeanClickDeletedStatus(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,1,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0),0));
    }
    @Test
    public void clickDeletedStatus2() {
        assertEq(S_STA_SIM,callMoveBeanClickDeletedStatusId(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,1,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0),0));
    }
    @Test
    public void getRequiredStatus() {
        assertEq(S_STA_REL_TR,callMoveBeanGetRequiredStatus(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,1,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0),0));
    }
    @Test
    public void clickRequiredStatus1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML,callMoveBeanClickRequiredStatus(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,1,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0),0));
    }
    @Test
    public void clickRequiredStatus2() {
        assertEq(S_STA_REL,callMoveBeanClickRequiredStatusId(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,1,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0),0));
    }
    @Test
    public void getSecEffectsByItem1() {
        assertSizeEq(1,callMoveBeanSecEffectsByItemGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void getSecEffectsByItem2() {
        assertEq(I_ITEM,first(elt(callMoveBeanSecEffectsByItemGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)),0)));
    }
    @Test
    public void getSecEffectsByItem3() {
        assertSizeEq(1,second(elt(callMoveBeanSecEffectsByItemGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)),0)));
    }
    @Test
    public void getSecEffectsByItem4() {
        assertEq(0,elt(second(elt(callMoveBeanSecEffectsByItemGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)),0)),0));
    }
    @Test
    public void getTypesByOwnedItems1() {
        assertSizeEq(1,callMoveBeanTypesByOwnedItemsGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void getTypesByOwnedItems2() {
        assertEq(I_ITEM,first(elt(callMoveBeanTypesByOwnedItemsGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)),0)));
    }
    @Test
    public void getTypesByOwnedItems3() {
        assertEq(T_TYPE1_TR,second(elt(callMoveBeanTypesByOwnedItemsGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)),0)));
    }
    @Test
    public void getTypesByWeathers1() {
        assertSizeEq(1,callMoveBeanTypesByWeathersGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void getTypesByWeathers2() {
        assertEq(M_WEA,first(elt(callMoveBeanTypesByWeathersGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)),0)));
    }
    @Test
    public void getTypesByWeathers3() {
        assertEq(T_TYPE1_TR,second(elt(callMoveBeanTypesByWeathersGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)),0)));
    }
    @Test
    public void getMovesTmLearntByPokemon1() {
        assertSizeEq(1,callMoveBeanMovesTmLearntByPokemonGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void getMovesTmLearntByPokemon2() {
        assertEq(P_POKEMON,elt(callMoveBeanMovesTmLearntByPokemonGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)),0));
    }
    @Test
    public void getTrPokemonTm() {
        assertEq(P_POKEMON_TR,callMoveBeanGetTrPokemonTm(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0),0));
    }
    @Test
    public void clickPokemonTm1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_DATA_HTML,callMoveBeanClickPokemonTm(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0),0));
    }
    @Test
    public void clickPokemonTm2() {
        assertEq(P_POKEMON,callMoveBeanClickPokemonTmId(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0),0));
    }
    @Test
    public void getMovesMtLearntByPokemon1() {
        assertSizeEq(1,callMoveBeanMovesMtLearntByPokemonGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void getMovesMtLearntByPokemon2() {
        assertEq(P_POKEMON,elt(callMoveBeanMovesMtLearntByPokemonGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)),0));
    }
    @Test
    public void getTrPokemonMt() {
        assertEq(P_POKEMON_TR,callMoveBeanGetTrPokemonMt(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0),0));
    }
    @Test
    public void clickPokemonMt1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_DATA_HTML,callMoveBeanClickPokemonMt(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0),0));
    }
    @Test
    public void clickPokemonMt2() {
        assertEq(P_POKEMON,callMoveBeanClickPokemonMtId(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0),0));
    }
    @Test
    public void getMovesHmLearntByPokemon1() {
        assertSizeEq(1,callMoveBeanMovesHmLearntByPokemonGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),1)));
    }
    @Test
    public void getMovesHmLearntByPokemon2() {
        assertEq(P_POKEMON,elt(callMoveBeanMovesHmLearntByPokemonGet(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),1)),0));
    }
    @Test
    public void getTrPokemonHm() {
        assertEq(P_POKEMON_TR,callMoveBeanGetTrPokemonHm(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),1),0));
    }
    @Test
    public void clickPokemonHm1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_DATA_HTML,callMoveBeanClickPokemonHm(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),1),0));
    }
    @Test
    public void clickPokemonHm2() {
        assertEq(P_POKEMON,callMoveBeanClickPokemonHmId(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),1),0));
    }
    @Test
    public void getTrTypesByWeathers() {
        assertEq(M_WEA_TR,callMoveBeanGetTrTypesByWeathers(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0),0));
    }
    @Test
    public void clickTypesByWeathers1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callMoveBeanClickTypesByWeathers(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0),0));
    }
    @Test
    public void clickTypesByWeathers2() {
        assertEq(M_WEA,callMoveBeanClickTypesByWeathersId(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0),0));
    }
    @Test
    public void getTrMove1() {
        assertEq(M_DAM_TR,callMoveBeanGetTrMove(dispMove(feedDbMoveStaEndRoundAffect(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,false,false, 1, 0),5),0));
    }
    @Test
    public void getTrMove2() {
        assertEq(M_DAM_VAR_TR,callMoveBeanGetTrMove(dispMove(feedDbMoveStaEndRoundAffect(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,false,false, 1, 0),5),1));
    }
    @Test
    public void getTrMove3() {
        assertEq(M_WEA_TR,callMoveBeanGetTrMove(dispMove(feedDbMoveStaEndRoundAffect(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,false,false, 1, 0),5),2));
    }
    @Test
    public void clickMove1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callMoveBeanClickMove(dispMove(feedDbMoveStaEndRoundAffect(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,false,false, 1, 0),5),0));
    }
    @Test
    public void clickMove2() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callMoveBeanClickMove(dispMove(feedDbMoveStaEndRoundAffect(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,false,false, 1, 0),5),1));
    }
    @Test
    public void clickMove3() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callMoveBeanClickMove(dispMove(feedDbMoveStaEndRoundAffect(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,false,false, 1, 0),5),2));
    }
    @Test
    public void clickMove4() {
        assertEq(M_DAM,callMoveBeanClickMoveId(dispMove(feedDbMoveStaEndRoundAffect(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,false,false, 1, 0),5),0));
    }
    @Test
    public void clickMove5() {
        assertEq(M_DAM_VAR,callMoveBeanClickMoveId(dispMove(feedDbMoveStaEndRoundAffect(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,false,false, 1, 0),5),1));
    }
    @Test
    public void clickMove6() {
        assertEq(M_WEA,callMoveBeanClickMoveId(dispMove(feedDbMoveStaEndRoundAffect(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,false,false, 1, 0),5),2));
    }
    @Test
    public void getTrAbility() {
        assertEq(A_ABILITY_TR,callMoveBeanGetTrAbility(dispMove(feedDbMoveDamAb(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0),0));
    }
    @Test
    public void clickAbility1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ABILITY_DATA_HTML,callMoveBeanClickAbility(dispMove(feedDbMoveDamAb(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0),0));
    }
    @Test
    public void clickAbility2() {
        assertEq(A_ABILITY,callMoveBeanClickAbilityId(dispMove(feedDbMoveDamAb(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0),0));
    }
    @Test
    public void clickMoves() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_MOVES_HTML,callMoveBeanClickMoves(dispMove(feedDbMoveDamAb(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0)));
    }
    @Test
    public void getTrTypesByOwnedItems() {
        assertEq(I_ITEM_TR,callMoveBeanGetTrTypesByOwnedItems(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0),0));
    }
    @Test
    public void clickTypesByOwnedItems1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_ITEMFORBATTLE_HTML,callMoveBeanClickTypesByOwnedItems(dispMove(feedDbMoveDamItBat(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0),0));
    }
    @Test
    public void clickTypesByOwnedItems2() {
        assertEq(I_ITEM,callMoveBeanClickTypesByOwnedItemsId(dispMove(feedDbMoveDamItBat(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0),0));
    }
    @Test
    public void translateItemSecEffect() {
        assertEq(I_ITEM_TR,callMoveBeanTranslateItemSecEffect(dispMove(feedDbMoveDam(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0),0));
    }
    @Test
    public void clickItemSecEffect1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_ITEMFORBATTLE_HTML,callMoveBeanClickItemSecEffect(dispMove(feedDbMoveDamItBat(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0),0));
    }
    @Test
    public void clickItemSecEffect2() {
        assertEq(I_ITEM,callMoveBeanClickItemSecEffectId(dispMove(feedDbMoveDamItBat(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0),0));
    }
    @Test
    public void getTrItem() {
        assertEq(I_ITEM_TR,callMoveBeanGetTrItem(dispMove(feedDbMoveDamItBat(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0),0));

    }
    @Test
    public void clickItem1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_ITEMFORBATTLE_HTML,callMoveBeanClickItem(dispMove(feedDbMoveDamItBat(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0),0));

    }
    @Test
    public void clickItem2() {
        assertEq(I_ITEM,callMoveBeanClickItemId(dispMove(feedDbMoveDamItBat(TargetChoice.ANY_FOE,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true,true,"1"),0),0));
    }
}
