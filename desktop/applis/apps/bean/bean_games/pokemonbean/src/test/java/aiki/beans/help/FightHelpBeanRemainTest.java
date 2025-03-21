package aiki.beans.help;

import aiki.beans.CommonBean;
import aiki.facade.FacadeGame;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.effects.*;
import aiki.instances.Instances;
import code.maths.Rate;
import code.util.StringMap;
import org.junit.Test;

public final class FightHelpBeanRemainTest extends InitDbFightHelp {

    @Test
    public void init1() {
        assertSizeEq(1,callFightHelpBeanMovesHealingSubstituteGet(bean(db1())));
    }
    @Test
    public void tr1() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrMovesHealingSubstitute(bean(db1()),0));
    }
    @Test
    public void cl1() {
        assertEq(CommonBean.REN_ADD_WEB_HTML_MOVES_DATA_HTML,click1());
    }
    @Test
    public void clId1() {
        assertEq(M_DAM,clickId1());
    }
    @Test
    public void init2() {
        assertSizeEq(1,callFightHelpBeanEntryHazardGet(bean(db2())));
    }
    @Test
    public void tr2() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrEntryHazard(bean(db2()),0));
    }
    @Test
    public void cl2() {
        assertEq(CommonBean.REN_ADD_WEB_HTML_MOVES_DATA_HTML,click2());
    }
    @Test
    public void clId2() {
        assertEq(M_DAM,clickId2());
    }
    @Test
    public void init3() {
        assertSizeEq(1,callFightHelpBeanMovesInvokingGet(bean(db3())));
    }
    @Test
    public void tr3() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrMovesInvoking(bean(db3()),0));
    }
    @Test
    public void cl3() {
        assertEq(CommonBean.REN_ADD_WEB_HTML_MOVES_DATA_HTML,click3());
    }
    @Test
    public void clId3() {
        assertEq(M_DAM,clickId3());
    }
    @Test
    public void init4() {
        assertSizeEq(1,callFightHelpBeanProtectMovesGet(bean(db4())));
    }
    @Test
    public void tr4() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrProtectMoves(bean(db4()),0));
    }
    @Test
    public void cl4() {
        assertEq(CommonBean.REN_ADD_WEB_HTML_MOVES_DATA_HTML,click4());
    }
    @Test
    public void clId4() {
        assertEq(M_DAM,clickId4());
    }
    @Test
    public void init5() {
        assertSizeEq(1,callFightHelpBeanMovesProtAgainstKoGet(bean(db5())));
    }
    @Test
    public void tr5() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrMovesProtAgainstKo(bean(db5()),0));
    }
    @Test
    public void cl5() {
        assertEq(CommonBean.REN_ADD_WEB_HTML_MOVES_DATA_HTML,click5());
    }
    @Test
    public void clId5() {
        assertEq(M_DAM,clickId5());
    }
    @Test
    public void init6() {
        assertSizeEq(1,callFightHelpBeanMovesProtectingTypesGet(bean(db6())));
    }
    @Test
    public void tr6() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrMovesProtectingTypes(bean(db6()),0));
    }
    @Test
    public void cl6() {
        assertEq(CommonBean.REN_ADD_WEB_HTML_MOVES_DATA_HTML,click6());
    }
    @Test
    public void clId6() {
        assertEq(M_DAM,clickId6());
    }
    @Test
    public void init7() {
        assertSizeEq(1,callFightHelpBeanMovesUnprotectingTypesGet(bean(db7())));
    }
    @Test
    public void tr7() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrMovesUnprotectingTypes(bean(db7()),0));
    }
    @Test
    public void cl7() {
        assertEq(CommonBean.REN_ADD_WEB_HTML_MOVES_DATA_HTML,click7());
    }
    @Test
    public void clId7() {
        assertEq(M_DAM,clickId7());
    }
    @Test
    public void init8() {
        assertSizeEq(1,callFightHelpBeanMovesProtectingGet(bean(db8())));
    }
    @Test
    public void tr8() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrMovesProtecting(bean(db8()),0));
    }
    @Test
    public void cl8() {
        assertEq(CommonBean.REN_ADD_WEB_HTML_MOVES_DATA_HTML,click8());
    }
    @Test
    public void clId8() {
        assertEq(M_DAM,clickId8());
    }
    private String click1() {
        FightHelpBean b_ = bean(db1());
        return callFightHelpBeanClickMovesHealingSubstitute(b_,0);
    }
    private String clickId1() {
        FightHelpBean b_ = bean(db1());
        callFightHelpBeanClickMovesHealingSubstitute(b_,0);
        return getValMoveId(b_);
    }
    private static FacadeGame db1() {
        FacadeGame f_ = facade();
        DamagingMoveData t_ = Instances.newDamagingMoveData();
        EffectStatus e_ = Instances.newEffectStatus();
        e_.setKoUserHealSubst(true);
        t_.getEffects().add(e_);
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().setCombos(Instances.newCombos());
        return f_;
    }
    private String click2() {
        FightHelpBean b_ = bean(db2());
        return callFightHelpBeanClickEntryHazard(b_,0);
    }
    private String clickId2() {
        FightHelpBean b_ = bean(db2());
        callFightHelpBeanClickEntryHazard(b_,0);
        return getValMoveId(b_);
    }
    private static FacadeGame db2() {
        FacadeGame f_ = facade();
        DamagingMoveData t_ = Instances.newDamagingMoveData();
        EffectTeamWhileSendFoe e_ = Instances.newEffectTeamWhileSendFoe();
        t_.getEffects().add(e_);
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().setCombos(Instances.newCombos());
        return f_;
    }
    private String click3() {
        FightHelpBean b_ = bean(db3());
        return callFightHelpBeanClickMovesInvoking(b_,0);
    }
    private String clickId3() {
        FightHelpBean b_ = bean(db3());
        callFightHelpBeanClickMovesInvoking(b_,0);
        return getValMoveId(b_);
    }
    private static FacadeGame db3() {
        FacadeGame f_ = facade();
        DamagingMoveData t_ = Instances.newDamagingMoveData();
        EffectInvoke e_ = Instances.newEffectInvoke();
        t_.getEffects().add(e_);
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().setCombos(Instances.newCombos());
        return f_;
    }
    private String click4() {
        FightHelpBean b_ = bean(db4());
        return callFightHelpBeanClickProtectMoves(b_,0);
    }
    private String clickId4() {
        FightHelpBean b_ = bean(db4());
        callFightHelpBeanClickProtectMoves(b_,0);
        return getValMoveId(b_);
    }
    private static FacadeGame db4() {
        FacadeGame f_ = facade();
        DamagingMoveData t_ = Instances.newDamagingMoveData();
        EffectCounterAttack e_ = Instances.newEffectCounterAttack();
        t_.getEffects().add(e_);
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().setCombos(Instances.newCombos());
        return f_;
    }
    private String click5() {
        FightHelpBean b_ = bean(db5());
        return callFightHelpBeanClickMovesProtAgainstKo(b_,0);
    }
    private String clickId5() {
        FightHelpBean b_ = bean(db5());
        callFightHelpBeanClickMovesProtAgainstKo(b_,0);
        return getValMoveId(b_);
    }
    private static FacadeGame db5() {
        FacadeGame f_ = facade();
        DamagingMoveData t_ = Instances.newDamagingMoveData();
        EffectProtection e_ = Instances.newEffectProtection();
        e_.setProtSingleAgainstKo(Rate.one());
        t_.getEffects().add(e_);
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().setCombos(Instances.newCombos());
        return f_;
    }
    private String click6() {
        FightHelpBean b_ = bean(db6());
        return callFightHelpBeanClickMovesProtectingTypes(b_,0);
    }
    private String clickId6() {
        FightHelpBean b_ = bean(db6());
        callFightHelpBeanClickMovesProtectingTypes(b_,0);
        return getValMoveId(b_);
    }
    private static FacadeGame db6() {
        FacadeGame f_ = facade();
        DamagingMoveData t_ = Instances.newDamagingMoveData();
        EffectProtectFromTypes e_ = Instances.newEffectProtectFromTypes();
        t_.getEffects().add(e_);
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().setCombos(Instances.newCombos());
        return f_;
    }
    private String click7() {
        FightHelpBean b_ = bean(db7());
        return callFightHelpBeanClickMovesUnprotectingTypes(b_,0);
    }
    private String clickId7() {
        FightHelpBean b_ = bean(db7());
        callFightHelpBeanClickMovesUnprotectingTypes(b_,0);
        return getValMoveId(b_);
    }
    private static FacadeGame db7() {
        FacadeGame f_ = facade();
        DamagingMoveData t_ = Instances.newDamagingMoveData();
        EffectUnprotectFromTypes e_ = Instances.newEffectUnprotectFromTypes();
        t_.getEffects().add(e_);
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().setCombos(Instances.newCombos());
        return f_;
    }
    private String click8() {
        FightHelpBean b_ = bean(db8());
        return callFightHelpBeanClickMovesProtecting(b_,0);
    }
    private String clickId8() {
        FightHelpBean b_ = bean(db8());
        callFightHelpBeanClickMovesProtecting(b_,0);
        return getValMoveId(b_);
    }
    private static FacadeGame db8() {
        FacadeGame f_ = facade();
        DamagingMoveData t_ = Instances.newDamagingMoveData();
        EffectProtection e_ = Instances.newEffectProtection();
        e_.setProtSingle(true);
        t_.getEffects().add(e_);
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().setCombos(Instances.newCombos());
        return f_;
    }
}
