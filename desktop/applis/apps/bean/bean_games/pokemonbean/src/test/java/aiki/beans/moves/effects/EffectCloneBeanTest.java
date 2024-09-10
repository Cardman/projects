package aiki.beans.moves.effects;

import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import code.util.StringList;
import org.junit.Test;

public final class EffectCloneBeanTest extends InitDbMoveEffectOther {
    @Test
    public void movesSending() {
        StringList ls_ = EffectCloneBean.movesSending(feedDbMoveEffDataCloneSending().getData());
        assertEq(1,ls_.size());
        assertEq(M_WEA,ls_.get(0));
    }
    @Test
    public void movesBatonPass() {
        StringList ls_ = EffectCloneBean.movesBatonPass(feedDbMoveEffDataCloneBatonPass().getData());
        assertEq(1,ls_.size());
        assertEq(M_WEA,ls_.get(0));
    }
    @Test
    public void getHpRateClone() {
        assertEq(Rate.one(),callEffectCloneBeanHpRateCloneGet(dispMoveEffClone()));
    }
    @Test
    public void getMovesSending1() {
        assertSizeEq(1,callEffectCloneBeanMovesSendingGet(dispMoveEffClone()));
    }
    @Test
    public void getMovesSending2() {
        assertEq(M_WEA,elt(callEffectCloneBeanMovesSendingGet(dispMoveEffClone()),0));
    }
    @Test
    public void getTrMovesSending() {
        assertEq(M_WEA_TR,callEffectCloneBeanGetTrMovesSending(dispMoveEffClone(),0));
    }
    @Test
    public void clickMoveSending1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callEffectCloneBeanClickMoveSending(dispMoveEffClone(),0));
    }
    @Test
    public void clickMoveSending2() {
        assertEq(M_WEA,callEffectCloneBeanClickMoveSendingId(dispMoveEffClone(),0));
    }
    @Test
    public void getMovesBatonPass1() {
        assertSizeEq(1,callEffectCloneBeanMovesBatonPassGet(dispMoveEffClone()));
    }
    @Test
    public void getMovesBatonPass2() {
        assertEq(M_STA,elt(callEffectCloneBeanMovesBatonPassGet(dispMoveEffClone()),0));
    }
    @Test
    public void getTrMovesBatonPass() {
        assertEq(M_STA_TR,callEffectCloneBeanGetTrMovesBatonPass(dispMoveEffClone(),0));
    }
    @Test
    public void clickMoveBatonPass1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callEffectCloneBeanClickMoveBatonPass(dispMoveEffClone(),0));
    }
    @Test
    public void clickMoveBatonPass2() {
        assertEq(M_STA,callEffectCloneBeanClickMoveBatonPassId(dispMoveEffClone(),0));
    }
    @Test
    public void getMovesEndRound1() {
        assertSizeEq(1,callEffectCloneBeanMovesEndRoundGet(dispMoveEffClone()));
    }
    @Test
    public void getMovesEndRound2() {
        assertEq(M_DAM_VERY_BAD,elt(callEffectCloneBeanMovesEndRoundGet(dispMoveEffClone()),0));
    }
    @Test
    public void getTrMovesEndRound() {
        assertEq(M_DAM_VERY_BAD_TR,callEffectCloneBeanGetTrMovesEndRound(dispMoveEffClone(),0));
    }
    @Test
    public void clickMoveEndRound1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callEffectCloneBeanClickMoveEndRound(dispMoveEffClone(),0));
    }
    @Test
    public void clickMoveEndRound2() {
        assertEq(M_DAM_VERY_BAD,callEffectCloneBeanClickMoveEndRoundId(dispMoveEffClone(),0));
    }
}
