package aiki.beans.moves.effects;

import aiki.fight.moves.effects.enums.ConstValuesType;
import aiki.fight.moves.effects.enums.ExchangeType;
import aiki.map.levels.enums.EnvironmentType;
import code.scripts.confs.PkScriptPages;
import code.util.StringList;
import org.junit.Test;

public final class EffectSwitchTypesBeanTest extends InitDbMoveEffectSwitch {
    @Test
    public void globalMoves() {
        StringList ls_ = EffectSwitchTypesBean.globalMoves(feedDbMoveEffDataDam(ConstValuesType.NOTHING, ExchangeType.NOTHING).getData());
        assertEq(1,ls_.size());
        assertEq(M_WEA,ls_.get(0));
    }
    @Test
    public void isConstTypes1() {
        assertTrue(callEffectSwitchTypesBeanIsConstTypes(dispMoveEffCopyMove(ConstValuesType.LANCEUR_ATTAQUES_TYPES,ExchangeType.NOTHING)));
    }
    @Test
    public void isConstTypes2() {
        assertFalse(callEffectSwitchTypesBeanIsConstTypes(dispMoveEffCopyMove(ConstValuesType.NOTHING,ExchangeType.NOTHING)));
    }
    @Test
    public void isResTypes1() {
        assertTrue(callEffectSwitchTypesBeanIsResTypes(dispMoveEffCopyMove(ConstValuesType.TYPES_ATTAQUES_RES,ExchangeType.NOTHING)));
    }
    @Test
    public void isResTypes2() {
        assertFalse(callEffectSwitchTypesBeanIsResTypes(dispMoveEffCopyMove(ConstValuesType.NOTHING,ExchangeType.NOTHING)));
    }
    @Test
    public void isUserTypes1() {
        assertTrue(callEffectSwitchTypesBeanIsUserTypes(dispMoveEffCopyMove(ConstValuesType.LANCEUR_ATTAQUES_TYPES,ExchangeType.NOTHING)));
    }
    @Test
    public void isUserTypes2() {
        assertFalse(callEffectSwitchTypesBeanIsUserTypes(dispMoveEffCopyMove(ConstValuesType.NOTHING,ExchangeType.NOTHING)));
    }
    @Test
    public void giveToTarget1() {
        assertTrue(callEffectSwitchTypesBeanGiveToTarget(dispMoveEffCopyMove(ConstValuesType.NOTHING,ExchangeType.GIVE_TO_TARGET)));
    }
    @Test
    public void giveToTarget2() {
        assertFalse(callEffectSwitchTypesBeanGiveToTarget(dispMoveEffCopyMove(ConstValuesType.NOTHING,ExchangeType.NOTHING)));
    }
    @Test
    public void giveToUser1() {
        assertTrue(callEffectSwitchTypesBeanGiveToUser(dispMoveEffCopyMove(ConstValuesType.NOTHING,ExchangeType.GIVE_TO_THROWER)));
    }
    @Test
    public void giveToUser2() {
        assertFalse(callEffectSwitchTypesBeanGiveToUser(dispMoveEffCopyMove(ConstValuesType.NOTHING,ExchangeType.NOTHING)));
    }
    @Test
    public void giveConst1() {
        assertTrue(callEffectSwitchTypesBeanGiveConst(dispMoveEffCopyMove(ConstValuesType.NOTHING,ExchangeType.GIVE_CONST)));
    }
    @Test
    public void giveConst2() {
        assertFalse(callEffectSwitchTypesBeanGiveConst(dispMoveEffCopyMove(ConstValuesType.NOTHING,ExchangeType.NOTHING)));
    }
    @Test
    public void switchTypes1() {
        assertTrue(callEffectSwitchTypesBeanSwitchTypes(dispMoveEffCopyMove(ConstValuesType.NOTHING,ExchangeType.EXCHANGE)));
    }
    @Test
    public void switchTypes2() {
        assertFalse(callEffectSwitchTypesBeanSwitchTypes(dispMoveEffCopyMove(ConstValuesType.NOTHING,ExchangeType.NOTHING)));
    }
    @Test
    public void getGlobalMoves1() {
        assertSizeEq(1,callEffectSwitchTypesBeanGlobalMovesGet(dispMoveEffCopyMove(ConstValuesType.NOTHING,ExchangeType.NOTHING)));
    }
    @Test
    public void getGlobalMoves2() {
        assertEq(M_WEA,elt(callEffectSwitchTypesBeanGlobalMovesGet(dispMoveEffCopyMove(ConstValuesType.NOTHING,ExchangeType.NOTHING)),0));
    }
    @Test
    public void getTrGlobalMoveFctEnv() {
        assertEq(M_WEA_TR,callEffectSwitchTypesBeanGetTrGlobalMoveFctEnv(dispMoveEffCopyMove(ConstValuesType.NOTHING,ExchangeType.NOTHING),0));
    }
    @Test
    public void clickGlobalMoveFctEnv1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callEffectSwitchTypesBeanClickGlobalMoveFctEnv(dispMoveEffCopyMove(ConstValuesType.NOTHING,ExchangeType.NOTHING),0));
    }
    @Test
    public void clickGlobalMoveFctEnv2() {
        assertEq(M_WEA,callEffectSwitchTypesBeanClickGlobalMoveFctEnvId(dispMoveEffCopyMove(ConstValuesType.NOTHING,ExchangeType.NOTHING),0));
    }
    @Test
    public void getAddedTypes1() {
        assertSizeEq(1,callEffectSwitchTypesBeanAddedTypesGet(dispMoveEffCopyMove(ConstValuesType.NOTHING,ExchangeType.NOTHING)));
    }
    @Test
    public void getAddedTypes2() {
        assertEq(T_TYPE1,elt(callEffectSwitchTypesBeanAddedTypesGet(dispMoveEffCopyMove(ConstValuesType.NOTHING,ExchangeType.NOTHING)),0));
    }
    @Test
    public void getTrAddedType() {
        assertEq(T_TYPE1_TR,callEffectSwitchTypesBeanGetTrAddedType(dispMoveEffCopyMove(ConstValuesType.NOTHING,ExchangeType.NOTHING),0));
    }
    @Test
    public void getConstTypes1() {
        assertSizeEq(1,callEffectSwitchTypesBeanAddedTypesGet(dispMoveEffCopyMove(ConstValuesType.NOTHING,ExchangeType.NOTHING)));
    }
    @Test
    public void getConstTypes2() {
        assertEq(T_TYPE1,elt(callEffectSwitchTypesBeanConstTypesGet(dispMoveEffCopyMove(ConstValuesType.NOTHING,ExchangeType.NOTHING)),0));
    }
    @Test
    public void getTrConstType() {
        assertEq(T_TYPE1_TR,callEffectSwitchTypesBeanGetTrConstType(dispMoveEffCopyMove(ConstValuesType.NOTHING,ExchangeType.NOTHING),0));
    }
    @Test
    public void getChgtTypeByEnv1() {
        assertSizeEq(1,callEffectSwitchTypesBeanChgtTypeByEnvGet(dispMoveEffCopyMove(ConstValuesType.NOTHING,ExchangeType.NOTHING)));
    }
    @Test
    public void getChgtTypeByEnv2() {
        assertEq(EnvironmentType.ROAD.getEnvName(),first(elt(callEffectSwitchTypesBeanChgtTypeByEnvGet(dispMoveEffCopyMove(ConstValuesType.NOTHING,ExchangeType.NOTHING)),0)));
    }
    @Test
    public void getChgtTypeByEnv3() {
        assertEq(T_TYPE1_TR,second(elt(callEffectSwitchTypesBeanChgtTypeByEnvGet(dispMoveEffCopyMove(ConstValuesType.NOTHING,ExchangeType.NOTHING)),0)));
    }
    @Test
    public void getTrEnv() {
        assertEq(ROAD_TR,callEffectSwitchTypesBeanGetTrEnv(dispMoveEffCopyMove(ConstValuesType.NOTHING,ExchangeType.NOTHING),0));
    }
}
