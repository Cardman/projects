package aiki.beans.help;

import aiki.beans.CommonBean;
import aiki.facade.FacadeGame;
import aiki.fight.abilities.AbilityData;
import aiki.instances.Instances;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FightHelpBeanAbilitiesImmuAlliesDamTest extends InitDbFightHelp {
    @Test
    public void movesTypesDefWeatherInitTest() {
        StringList ls_ = FightHelpBean.abilitiesImmuAlliesDamInit(db().getData());
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }
    @Test
    public void init() {
        assertSizeEq(1,callFightHelpBeanAbilitiesImmuAlliesDamGet(bean(db())));
    }
    @Test
    public void tr() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrAbilitiesImmuAlliesDam(bean(db()),0));
    }
    @Test
    public void cl1() {
        assertEq(CommonBean.REN_ADD_WEB_HTML_ABILITY_DATA_HTML,click());
    }
    @Test
    public void clId1() {
        assertEq(M_DAM,clickId());
    }
    private String click() {
        FightHelpBean b_ = bean(db());
        return callFightHelpBeanClickAbilitiesImmuAlliesDam(b_,0);
    }
    private String clickId() {
        FightHelpBean b_ = bean(db());
        callFightHelpBeanClickAbilitiesImmuAlliesDam(b_,0);
        return getValAbilityId(b_);
    }
    private static FacadeGame db() {
        FacadeGame f_ = facade();
        AbilityData t_ = Instances.newAbilityData();
        t_.setImmuDamageAllyMoves(true);
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().completeMembers(M_STA, Instances.newAbilityData());
        f_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedAbilities().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedAbilities().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedAbilities().getVal(EN).addEntry(M_STA,M_STA_TR);
        return f_;
    }
}
