package aiki.beans.help;

import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.effects.EffectTeam;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.scripts.confs.PkScriptPages;
import code.util.StringMap;
import org.junit.Test;

public final class FightHelpBeanMovesTeamTest extends InitDbFightHelp {
    @Test
    public void init1() {
        assertSizeEq(1,callFightHelpBeanMovesTeamGet(bean(dbProtectAgainstCh())));
    }
    @Test
    public void init2() {
        assertSizeEq(1,callFightHelpBeanMovesTeamGet(bean(dbProtectAgainstLowStat())));
    }
    @Test
    public void init3() {
        assertSizeEq(1,callFightHelpBeanMovesTeamGet(bean(dbProtectAgainstStatus())));
    }
    @Test
    public void immuStatisTeamMove1() {
        assertFalse(callFightHelpBeanImmuStatisTeamMove(bean(dbProtectAgainstCh()),0));
    }
    @Test
    public void immuStatisTeamMove2() {
        assertTrue(callFightHelpBeanImmuStatisTeamMove(bean(dbProtectAgainstLowStat()),0));
    }
    @Test
    public void immuStatusTeamMove1() {
        assertFalse(callFightHelpBeanImmuStatusTeamMove(bean(dbProtectAgainstCh()),0));
    }
    @Test
    public void immuStatusTeamMove2() {
        assertTrue(callFightHelpBeanImmuStatusTeamMove(bean(dbProtectAgainstStatus()),0));
    }
    @Test
    public void immuChTeamMove1() {
        assertFalse(callFightHelpBeanImmuChTeamMove(bean(dbProtectAgainstStatus()),0));
    }
    @Test
    public void immuChTeamMove2() {
        assertTrue(callFightHelpBeanImmuChTeamMove(bean(dbProtectAgainstCh()),0));
    }
    @Test
    public void immuStatisTeamMoveAny1() {
        assertFalse(callFightHelpBeanImmuStatisTeamMoveAny(bean(dbProtectAgainstCh())));
    }
    @Test
    public void immuStatisTeamMoveAny2() {
        assertTrue(callFightHelpBeanImmuStatisTeamMoveAny(bean(dbProtectAgainstLowStat())));
    }
    @Test
    public void immuStatusTeamMoveAny1() {
        assertFalse(callFightHelpBeanImmuStatusTeamMoveAny(bean(dbProtectAgainstCh())));
    }
    @Test
    public void immuStatusTeamMoveAny2() {
        assertTrue(callFightHelpBeanImmuStatusTeamMoveAny(bean(dbProtectAgainstStatus())));
    }
    @Test
    public void immuChTeamMoveAny1() {
        assertFalse(callFightHelpBeanImmuChTeamMoveAny(bean(dbProtectAgainstStatus())));
    }
    @Test
    public void immuChTeamMoveAny2() {
        assertTrue(callFightHelpBeanImmuChTeamMoveAny(bean(dbProtectAgainstCh())));
    }
    @Test
    public void init() {
        assertSizeEq(1,callFightHelpBeanMovesTeamGet(bean(dbProtectAgainstLowStat())));
    }
    @Test
    public void tr() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrMovesTeam(bean(dbProtectAgainstLowStat()),0));
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
        NaSt b_ = bean(dbProtectAgainstLowStat());
        return toStr(callFightHelpBeanClickMovesTeam(b_,0));
    }
    private String clickId() {
        NaSt b_ = bean(dbProtectAgainstLowStat());
        callFightHelpBeanClickMovesTeam(b_,0);
        return getValMoveId(b_);
    }
    private static FacadeGame dbProtectAgainstLowStat() {
        FacadeGame f_ = facade();
        DamagingMoveData t_ = Instances.newDamagingMoveData();
        t_.getEffects().add(Instances.newEffectStatistic());
        EffectTeam e_ = Instances.newEffectTeam();
        e_.getProtectAgainstLowStat().add(Statistic.CRITICAL_HIT);
        t_.getEffects().add(e_);
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_STA,M_STA_TR);
        f_.getData().setCombos(Instances.newCombos());
        return f_;
    }
    private static FacadeGame dbProtectAgainstStatus() {
        FacadeGame f_ = facade();
        DamagingMoveData t_ = Instances.newDamagingMoveData();
        t_.getEffects().add(Instances.newEffectStatistic());
        EffectTeam e_ = Instances.newEffectTeam();
        e_.getProtectAgainstStatus().add(NULL_REF);
        t_.getEffects().add(e_);
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_STA,M_STA_TR);
        f_.getData().setCombos(Instances.newCombos());
        return f_;
    }
    private static FacadeGame dbProtectAgainstCh() {
        FacadeGame f_ = facade();
        DamagingMoveData t_ = Instances.newDamagingMoveData();
        t_.getEffects().add(Instances.newEffectStatistic());
        EffectTeam e_ = Instances.newEffectTeam();
        e_.setProtectAgainstCh(true);
        t_.getEffects().add(e_);
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_STA,M_STA_TR);
        f_.getData().setCombos(Instances.newCombos());
        return f_;
    }
}
