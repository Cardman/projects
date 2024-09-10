package aiki.beans.help;

import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.scripts.confs.PkScriptPages;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FightHelpBeanMovesGlobalBreakImmuAbTest extends InitDbFightHelp {
    @Test
    public void movesTypesDefWeatherInitTest() {
        StringList ls_ = FightHelpBean.movesGlobalBreakImmuAbInit(db().getData());
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }
    @Test
    public void movesTypesDefWeatherInitAbTest() {
        DataBase d_ = db().getData();
        StringList ls_ = FightHelpBean.abilitiesBreakableInit(FightHelpBean.movesGlobalBreakImmuAbInit(d_),d_);
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM_VAR));
    }
    @Test
    public void initMv() {
        assertSizeEq(1,callFightHelpBeanMovesGlobalBreakImmuAbGet(bean(db())));
    }
    @Test
    public void trMv() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrMovesGlobalBreakImmuAb(bean(db()),0));
    }
    @Test
    public void clMv1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,clickMv());
    }
    @Test
    public void clIdMv1() {
        assertEq(M_DAM,clickMvId());
    }
    private String clickMv() {
        NaSt b_ = bean(db());
        return toStr(callFightHelpBeanClickMovesGlobalBreakImmuAb(b_,0));
    }
    private String clickMvId() {
        NaSt b_ = bean(db());
        callFightHelpBeanClickMovesGlobalBreakImmuAb(b_,0);
        return getValMoveId(b_);
    }
    @Test
    public void initAb() {
        assertSizeEq(1,callFightHelpBeanAbilitiesBreakableGet(bean(db())));
    }
    @Test
    public void trAb() {
        assertEq(M_DAM_VAR_TR,callFightHelpBeanGetTrAbilitiesBreakable(bean(db()),0));
    }
    @Test
    public void clAb1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ABILITY_DATA_HTML,clickAb());
    }
    @Test
    public void clIdAb1() {
        assertEq(M_DAM_VAR,clickAbId());
    }
    private String clickAb() {
        NaSt b_ = bean(db());
        return toStr(callFightHelpBeanClickAbilitiesBreakable(b_,0));
    }
    private String clickAbId() {
        NaSt b_ = bean(db());
        callFightHelpBeanClickAbilitiesBreakable(b_,0);
        return getValAbilityId(b_);
    }
    private static FacadeGame db() {
        FacadeGame f_ = facade();
        DamagingMoveData t_ = Instances.newDamagingMoveData();
        t_.getEffects().add(Instances.newEffectStatistic());
        EffectGlobal e_ = Instances.newEffectGlobal();
        e_.getCancelProtectingAbilities().add(M_DAM_VAR);
        t_.getEffects().add(e_);
        f_.getData().completeMembers(M_DAM, t_);
        StatusMoveData s_ = Instances.newStatusMoveData();
        s_.getEffects().add(Instances.newEffectStatistic());
        s_.getEffects().add(Instances.newEffectGlobal());
        f_.getData().completeMembers(M_STA, s_);
        f_.getData().completeMembers(M_DAM_VAR, Instances.newAbilityData());
        f_.getData().getTranslatedAbilities().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedAbilities().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        f_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_STA,M_STA_TR);
        return f_;
    }
}
