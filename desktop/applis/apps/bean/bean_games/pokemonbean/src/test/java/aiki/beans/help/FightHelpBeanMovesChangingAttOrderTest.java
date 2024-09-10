package aiki.beans.help;

import aiki.facade.FacadeGame;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.effects.EffectOrder;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.scripts.confs.PkScriptPages;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FightHelpBeanMovesChangingAttOrderTest extends InitDbFightHelp {
    @Test
    public void movesTypesDefWeatherInitTest() {
        StringList ls_ = FightHelpBean.movesChangingAttOrderInit(db().getData());
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }
    @Test
    public void attackFirst1() {
        assertFalse(callFightHelpBeanAttackFirst(bean(dbLast())));
    }
    @Test
    public void attackFirst2() {
        assertTrue(callFightHelpBeanAttackFirst(bean(db())));
    }
    @Test
    public void attackLastAny1() {
        assertFalse(callFightHelpBeanAttackLastAny(bean(db())));
    }
    @Test
    public void attackLastAny2() {
        assertTrue(callFightHelpBeanAttackLastAny(bean(dbLast())));
    }
    @Test
    public void attackLast1() {
        assertFalse(callFightHelpBeanAttackLast(bean(db()),0));
    }
    @Test
    public void attackLast2() {
        assertTrue(callFightHelpBeanAttackLast(bean(dbLast()),0));
    }
    @Test
    public void init() {
        assertSizeEq(1,callFightHelpBeanMovesChangingAttOrderGet(bean(db())));
    }
    @Test
    public void tr() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrMovesChangingAttOrder(bean(db()),0));
    }
    @Test
    public void cl1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,click());
    }
    @Test
    public void clId1() {
        assertEq(M_DAM,clickId());
    }
    private String click() {
        NaSt b_ = bean(db());
        return toStr(callFightHelpBeanClickMovesChangingAttOrder(b_,0));
    }
    private String clickId() {
        NaSt b_ = bean(db());
        callFightHelpBeanClickMovesChangingAttOrder(b_,0);
        return getValMoveId(b_);
    }
    private static FacadeGame db() {
        FacadeGame f_ = facade();
        DamagingMoveData t_ = Instances.newDamagingMoveData();
        t_.getEffects().add(Instances.newEffectStatistic());
        EffectOrder e_ = Instances.newEffectOrder();
        e_.setTargetAttacksLast(false);
        t_.getEffects().add(e_);
        f_.getData().completeMembers(M_DAM, t_);
        StatusMoveData s_ = Instances.newStatusMoveData();
        s_.getEffects().add(Instances.newEffectStatistic());
        f_.getData().completeMembers(M_STA, s_);
        f_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_STA,M_STA_TR);
        f_.getData().setCombos(Instances.newCombos());
        return f_;
    }
    private static FacadeGame dbLast() {
        FacadeGame f_ = facade();
        DamagingMoveData t_ = Instances.newDamagingMoveData();
        t_.getEffects().add(Instances.newEffectStatistic());
        EffectOrder e_ = Instances.newEffectOrder();
        e_.setTargetAttacksLast(true);
        t_.getEffects().add(e_);
        f_.getData().completeMembers(M_DAM, t_);
        StatusMoveData s_ = Instances.newStatusMoveData();
        s_.getEffects().add(Instances.newEffectStatistic());
        f_.getData().completeMembers(M_STA, s_);
        f_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_STA,M_STA_TR);
        f_.getData().setCombos(Instances.newCombos());
        return f_;
    }
}
