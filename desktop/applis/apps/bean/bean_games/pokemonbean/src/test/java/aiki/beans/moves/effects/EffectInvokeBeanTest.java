package aiki.beans.moves.effects;

import aiki.map.levels.enums.EnvironmentType;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import code.util.StringList;
import org.junit.Test;

public final class EffectInvokeBeanTest extends InitDbMoveEffectInvoke {
    @Test
    public void globalMoves() {
        StringList moves_ = EffectInvokeBean.globalMoves(feedDbMoveEffDataInvoke(true, true, true, true, true, true).getData());
        assertEq(1, moves_.size());
        assertEq(M_STA, moves_.get(0));
    }
    @Test
    public void getInvokingMoveButUser1() {
        assertTrue(callEffectInvokeBeanInvokingMoveButUserGet(dispMoveEffInvoke(true,true,true,true,true,true)));
    }
    @Test
    public void getInvokingMoveButUser2() {
        assertFalse(callEffectInvokeBeanInvokingMoveButUserGet(dispMoveEffInvoke(true,false,true,true,true,true)));
    }
    @Test
    public void getInvokingTargetChosenMove1() {
        assertTrue(callEffectInvokeBeanInvokingTargetChosenMoveGet(dispMoveEffInvoke(true,true,true,true,true,true)));
    }
    @Test
    public void getInvokingTargetChosenMove2() {
        assertFalse(callEffectInvokeBeanInvokingTargetChosenMoveGet(dispMoveEffInvoke(true,true,true,false,true,true)));
    }
    @Test
    public void getInvokingUserMoveWhileSleep1() {
        assertTrue(callEffectInvokeBeanInvokingUserMoveWhileSleepGet(dispMoveEffInvoke(true,true,true,true,true,true)));
    }
    @Test
    public void getInvokingUserMoveWhileSleep2() {
        assertFalse(callEffectInvokeBeanInvokingUserMoveWhileSleepGet(dispMoveEffInvoke(true,true,true,true,true,false)));
    }
    @Test
    public void getInvokingAllyMove1() {
        assertTrue(callEffectInvokeBeanInvokingAllyMoveGet(dispMoveEffInvoke(true,true,true,true,true,true)));
    }
    @Test
    public void getInvokingAllyMove2() {
        assertFalse(callEffectInvokeBeanInvokingAllyMoveGet(dispMoveEffInvoke(false,true,true,true,true,true)));
    }
    @Test
    public void getInvokingTargetSuccesfulMove1() {
        assertTrue(callEffectInvokeBeanInvokingTargetSuccesfulMoveGet(dispMoveEffInvoke(true,true,true,true,true,true)));
    }
    @Test
    public void getInvokingTargetSuccesfulMove2() {
        assertFalse(callEffectInvokeBeanInvokingTargetSuccesfulMoveGet(dispMoveEffInvoke(true,true,true,true,false,true)));
    }
    @Test
    public void getInvokingSufferedMove1() {
        assertTrue(callEffectInvokeBeanInvokingSufferedMoveGet(dispMoveEffInvoke(true,true,true,true,true,true)));
    }
    @Test
    public void getInvokingSufferedMove2() {
        assertFalse(callEffectInvokeBeanInvokingSufferedMoveGet(dispMoveEffInvoke(true,true,false,true,true,true)));
    }
    @Test
    public void getRateInvokationMove() {
        assertEq(Rate.one(),callEffectInvokeBeanRateInvokationMoveGet(dispMoveEffInvoke(true,true,true,true,true,true)));
    }
    @Test
    public void isType1() {
        assertFalse(callEffectInvokeBeanIsType(dispMoveEffInvoke(true,true,true,true,true,true),0));
    }
    @Test
    public void isType2() {
        assertTrue(callEffectInvokeBeanIsType(dispMoveEffInvoke(true,true,true,true,true,true),1));
    }
    @Test
    public void getTrUserTypes1() {
        assertEq("",callEffectInvokeBeanGetTrUserTypes(dispMoveEffInvoke(true,true,true,true,true,true),0));
    }
    @Test
    public void getTrUserTypes2() {
        assertEq(T_TYPE1_TR,callEffectInvokeBeanGetTrUserTypes(dispMoveEffInvoke(true,true,true,true,true,true),1));
    }
    @Test
    public void getTrMoveUserTypes1() {
        assertEq(M_WEA_TR,callEffectInvokeBeanGetTrMoveUserTypes(dispMoveEffInvoke(true,true,true,true,true,true),0));
    }
    @Test
    public void getTrMoveUserTypes2() {
        assertEq(M_STA_TR,callEffectInvokeBeanGetTrMoveUserTypes(dispMoveEffInvoke(true,true,true,true,true,true),1));
    }
    @Test
    public void clickMoveUserTypes1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callEffectInvokeBeanClickMoveUserTypes(dispMoveEffInvoke(true,true,true,true,true,true),0,0));
    }
    @Test
    public void clickMoveUserTypes2() {
        assertEq(M_WEA,callEffectInvokeBeanClickMoveUserTypesId(dispMoveEffInvoke(true,true,true,true,true,true),0,0));
    }
    @Test
    public void clickMoveUserTypes3() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callEffectInvokeBeanClickMoveUserTypes(dispMoveEffInvoke(true,true,true,true,true,true),0,1));
    }
    @Test
    public void clickMoveUserTypes4() {
        assertEq(M_STA,callEffectInvokeBeanClickMoveUserTypesId(dispMoveEffInvoke(true,true,true,true,true,true),0,1));
    }
    @Test
    public void getGlobalMoves1() {
        assertSizeEq(1,callEffectInvokeBeanGlobalMovesGet(dispMoveEffInvoke(true,true,true,true,true,true)));
    }
    @Test
    public void getGlobalMoves2() {
        assertEq(M_STA,elt(callEffectInvokeBeanGlobalMovesGet(dispMoveEffInvoke(true,true,true,true,true,true)),0));
    }
    @Test
    public void getTrGlobalMoveFctEnv() {
        assertEq(M_STA_TR,callEffectInvokeBeanGetTrGlobalMoveFctEnv(dispMoveEffInvoke(true,true,true,true,true,true),0));
    }
    @Test
    public void clickGlobalMoveFctEnv1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callEffectInvokeBeanClickGlobalMoveFctEnv(dispMoveEffInvoke(true,true,true,true,true,true),0));
    }
    @Test
    public void clickGlobalMoveFctEnv2() {
        assertEq(M_STA,callEffectInvokeBeanClickGlobalMoveFctEnvId(dispMoveEffInvoke(true,true,true,true,true,true),0));
    }
    @Test
    public void getMovesNotToBeInvoked1() {
        assertSizeEq(1,callEffectInvokeBeanMovesNotToBeInvokedGet(dispMoveEffInvoke(true,true,true,true,true,true)));
    }
    @Test
    public void getMovesNotToBeInvoked2() {
        assertEq(M_STA,elt(callEffectInvokeBeanMovesNotToBeInvokedGet(dispMoveEffInvoke(true,true,true,true,true,true)),0));
    }
    @Test
    public void getTrMoveNotInvok() {
        assertEq(M_STA_TR,callEffectInvokeBeanGetTrMoveNotInvok(dispMoveEffInvoke(true,true,true,true,true,true),0));
    }
    @Test
    public void clickMoveNotInvok1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callEffectInvokeBeanClickMoveNotInvok(dispMoveEffInvoke(true,true,true,true,true,true),0,0));
    }
    @Test
    public void clickMoveNotInvok2() {
        assertEq(M_STA,callEffectInvokeBeanClickMoveNotInvokId(dispMoveEffInvoke(true,true,true,true,true,true),0,0));
    }
    @Test
    public void getInvokingMoveByUserTypes1() {
        assertSizeEq(2,callEffectInvokeBeanInvokingMoveByUserTypesGet(dispMoveEffInvoke(true,true,true,true,true,true)));
    }
    @Test
    public void getInvokingMoveByUserTypes2() {
        assertEq(NULL_REF,first(elt(callEffectInvokeBeanInvokingMoveByUserTypesGet(dispMoveEffInvoke(true,true,true,true,true,true)),0)));
    }
    @Test
    public void getInvokingMoveByUserTypes3() {
        assertEq(M_WEA,second(elt(callEffectInvokeBeanInvokingMoveByUserTypesGet(dispMoveEffInvoke(true,true,true,true,true,true)),0)));
    }
    @Test
    public void getInvokingMoveByUserTypes4() {
        assertEq(T_TYPE1,first(elt(callEffectInvokeBeanInvokingMoveByUserTypesGet(dispMoveEffInvoke(true,true,true,true,true,true)),1)));
    }
    @Test
    public void getInvokingMoveByUserTypes5() {
        assertEq(M_STA,second(elt(callEffectInvokeBeanInvokingMoveByUserTypesGet(dispMoveEffInvoke(true,true,true,true,true,true)),1)));
    }
    @Test
    public void getMoveFctEnv1() {
        assertSizeEq(1,callEffectInvokeBeanMoveFctEnvGet(dispMoveEffInvoke(true,true,true,true,true,true)));
    }
    @Test
    public void getMoveFctEnv2() {
        assertEq(EnvironmentType.ROAD.getEnvName(),first(elt(callEffectInvokeBeanMoveFctEnvGet(dispMoveEffInvoke(true,true,true,true,true,true)),0)));
    }
    @Test
    public void getMoveFctEnv3() {
        assertEq(M_WEA,second(elt(callEffectInvokeBeanMoveFctEnvGet(dispMoveEffInvoke(true,true,true,true,true,true)),0)));
    }
    @Test
    public void getTrMoveFctEnv() {
        assertEq(M_WEA_TR,callEffectInvokeBeanGetTrMoveFctEnv(dispMoveEffInvoke(true,true,true,true,true,true),0));
    }
    @Test
    public void getTrEnv() {
        assertEq(ROAD_TR,callEffectInvokeBeanGetTrEnv(dispMoveEffInvoke(true,true,true,true,true,true),0));
    }
    @Test
    public void clickMoveFctEnv1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callEffectInvokeBeanClickMoveFctEnv(dispMoveEffInvoke(true,true,true,true,true,true),0));
    }
    @Test
    public void clickMoveFctEnv2() {
        assertEq(M_WEA,callEffectInvokeBeanClickMoveFctEnvId(dispMoveEffInvoke(true,true,true,true,true,true),0));
    }
}
