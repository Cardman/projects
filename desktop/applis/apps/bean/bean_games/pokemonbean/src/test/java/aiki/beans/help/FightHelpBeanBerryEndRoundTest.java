package aiki.beans.help;

import aiki.beans.CommonBean;
import aiki.facade.FacadeGame;
import aiki.fight.items.Berry;
import aiki.instances.Instances;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FightHelpBeanBerryEndRoundTest extends InitDbFightHelp {
    @Test
    public void movesTypesDefWeatherInitTest() {
        StringList ls_ = FightHelpBean.berryEndRoundInit(db().getData());
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }
    @Test
    public void init() {
        assertSizeEq(1,callFightHelpBeanBerryEndRoundGet(bean(db())));
    }
    @Test
    public void tr() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrBerryEndRound(bean(db()),0));
    }
    @Test
    public void cl1() {
        assertEq(CommonBean.REN_ADD_WEB_HTML_ITEMS_BERRY_HTML,click());
    }
    @Test
    public void clId1() {
        assertEq(M_DAM,clickId());
    }
    private String click() {
        FightHelpBean b_ = bean(db());
        return callFightHelpBeanClickBerryEndRound(b_,0);
    }
    private String clickId() {
        FightHelpBean b_ = bean(db());
        callFightHelpBeanClickBerryEndRound(b_,0);
        return getValItemId(b_);
    }
    private static FacadeGame db() {
        FacadeGame f_ = facade();
        Berry t_ = Instances.newBerry();
        t_.setHealPp(1);
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().completeMembers(M_STA, Instances.newBall());
        f_.getData().completeMembers(M_DAM_VAR, Instances.newBerry());
        f_.getData().getTranslatedItems().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedItems().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedItems().getVal(EN).addEntry(M_STA,M_STA_TR);
        f_.getData().getTranslatedItems().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        f_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedAbilities().addEntry(EN,new StringMap<String>());
        return f_;
    }
}
