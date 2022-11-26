package aiki.beans.help;

import aiki.beans.abilities.AikiBeansAbilitiesStd;
import aiki.facade.FacadeGame;
import aiki.fight.abilities.AbilityData;
import aiki.fight.util.TypeDamageBoost;
import aiki.instances.Instances;
import code.expressionlanguage.structs.Struct;
import code.maths.Rate;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FightHelpBeanAbilitiesUserPowerTest extends InitDbFightHelp {
    @Test
    public void movesTypesDefWeatherInitTest() {
        StringList ls_ = FightHelpBean.abilitiesUserPowerInit(db().getData());
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }
    @Test
    public void init() {
        assertSizeEq(1,callFightHelpBeanAbilitiesUserPowerGet(bean(db())));
    }
    @Test
    public void tr() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrAbilitiesUserPower(bean(db()),0));
    }
    @Test
    public void cl1() {
        assertEq(AikiBeansAbilitiesStd.WEB_HTML_ABILITY_DATA_HTML,click());
    }
    @Test
    public void clId1() {
        assertEq(M_DAM,clickId());
    }
    private String click() {
        Struct b_ = bean(db());
        return toStr(callFightHelpBeanClickAbilitiesUserPower(b_,0));
    }
    private String clickId() {
        Struct b_ = bean(db());
        callFightHelpBeanClickAbilitiesUserPower(b_,0);
        return getValAbilityId(b_);
    }
    private static FacadeGame db() {
        FacadeGame f_ = facade();
        AbilityData t_ = Instances.newAbilityData();
        t_.getChangingBoostTypes().addEntry(NULL_REF,new TypeDamageBoost(NULL_REF, Rate.one()));
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().completeMembers(M_STA, Instances.newAbilityData());
        f_.getData().getTranslatedAbilities().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedAbilities().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedAbilities().getVal(EN).addEntry(M_STA,M_STA_TR);
        return f_;
    }
}