package aiki.beans.help;

import aiki.beans.CommonBean;
import aiki.facade.FacadeGame;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.effects.EffectDamage;
import aiki.instances.Instances;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FightHelpBeanMovesBoostChTest extends InitDbFightHelp {
    @Test
    public void movesTypesDefWeatherInitTest() {
        StringList ls_ = FightHelpBean.movesBoostChInit(db().getData());
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }
    @Test
    public void init() {
        assertSizeEq(1,callFightHelpBeanMovesBoostChGet(bean(db())));
    }
    @Test
    public void tr() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrMovesBoostCh(bean(db()),0));
    }
    @Test
    public void cl1() {
        assertEq(CommonBean.REN_ADD_WEB_HTML_MOVES_DATA_HTML,click());
    }
    @Test
    public void clId1() {
        assertEq(M_DAM,clickId());
    }
    private String click() {
        FightHelpBean b_ = bean(db());
        return callFightHelpBeanClickMovesBoostCh(b_,0);
    }
    private String clickId() {
        FightHelpBean b_ = bean(db());
        callFightHelpBeanClickMovesBoostCh(b_,0);
        return getValMoveId(b_);
    }
    private static FacadeGame db() {
        FacadeGame f_ = facade();
        DamagingMoveData t_ = Instances.newDamagingMoveData();
        EffectDamage e_ = Instances.newEffectDamage();
        e_.setChRate(1);
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
