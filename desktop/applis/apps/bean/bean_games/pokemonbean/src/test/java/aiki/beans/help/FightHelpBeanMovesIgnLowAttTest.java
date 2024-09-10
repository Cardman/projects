package aiki.beans.help;

import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.effects.EffectDamage;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.scripts.confs.PkScriptPages;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FightHelpBeanMovesIgnLowAttTest extends InitDbFightHelp {
    @Test
    public void movesTypesDefWeatherInitTest() {
        StringList ls_ = FightHelpBean.movesIgnLowAttInit(db().getData());
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }
    @Test
    public void init() {
        assertSizeEq(1,callFightHelpBeanMovesIgnLowAttGet(bean(db())));
    }
    @Test
    public void tr() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrMovesIgnLowAtt(bean(db()),0));
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
        return toStr(callFightHelpBeanClickMovesIgnLowAtt(b_,0));
    }
    private String clickId() {
        NaSt b_ = bean(db());
        callFightHelpBeanClickMovesIgnLowAtt(b_,0);
        return getValMoveId(b_);
    }
    private static FacadeGame db() {
        FacadeGame f_ = facade();
        DamagingMoveData t_ = Instances.newDamagingMoveData();
        EffectDamage e_ = Instances.newEffectDamage();
        e_.getIgnVarStatUserNeg().add(Statistic.ATTACK);
        t_.getEffects().add(e_);
        f_.getData().completeMembers(M_DAM, t_);
        StatusMoveData s_ = Instances.newStatusMoveData();
        s_.getEffects().add(Instances.newEffectStatistic());
        s_.getEffects().add(Instances.newEffectDamage());
        f_.getData().completeMembers(M_STA, s_);
        f_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_STA,M_STA_TR);
        return f_;
    }
}
