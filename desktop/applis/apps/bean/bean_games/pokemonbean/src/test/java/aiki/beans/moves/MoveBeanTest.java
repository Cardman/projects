package aiki.beans.moves;

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
        assertTrue(callMoveBeanIsEndRoundEffect(dispMove(feedDbMoveStaEndRound(TargetChoice.LANCEUR,"1", SwitchType.NOTHING,0,true,true,true,true,true,true,true,true,true,true,true),1),0));
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
}
